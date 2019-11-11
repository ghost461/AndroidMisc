package org.chromium.ui.display;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager$DisplayListener;
import android.hardware.display.DisplayManager;
import android.os.Build$VERSION;
import android.util.SparseArray;
import android.view.Display;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="ui") @MainDex public class DisplayAndroidManager {
    class org.chromium.ui.display.DisplayAndroidManager$1 {
    }

    class DisplayListenerAPI16 implements ComponentCallbacks, DisplayListenerBackend {
        private static final long POLLING_DELAY = 500;
        private int mAccurateCount;

        static {
        }

        DisplayListenerAPI16(DisplayAndroidManager arg1, org.chromium.ui.display.DisplayAndroidManager$1 arg2) {
            this(arg1);
        }

        private DisplayListenerAPI16(DisplayAndroidManager arg1) {
            DisplayAndroidManager.this = arg1;
            super();
        }

        static int access$100(DisplayListenerAPI16 arg0) {
            return arg0.mAccurateCount;
        }

        public void onConfigurationChanged(Configuration arg2) {
            DisplayAndroidManager.this.mIdMap.get(DisplayAndroidManager.this.mMainSdkDisplayId).updateFromDisplay(DisplayAndroidManager.getDefaultDisplayForContext(DisplayAndroidManager.getContext()));
        }

        public void onLowMemory() {
        }

        public void startAccurateListening() {
            ++this.mAccurateCount;
            if(this.mAccurateCount > 1) {
                return;
            }

            ThreadUtils.postOnUiThreadDelayed(new Runnable(this) {
                public void run() {
                    this.val$self.onConfigurationChanged(null);
                    if(this.val$self.mAccurateCount < 1) {
                        return;
                    }

                    ThreadUtils.postOnUiThreadDelayed(((Runnable)this), 500);
                }
            }, 500);
        }

        public void startListening() {
            DisplayAndroidManager.getContext().registerComponentCallbacks(((ComponentCallbacks)this));
        }

        public void stopAccurateListening() {
            --this.mAccurateCount;
        }
    }

    interface DisplayListenerBackend {
        void startAccurateListening();

        void startListening();

        void stopAccurateListening();
    }

    @SuppressLint(value={"NewApi"}) class DisplayListenerBackendImpl implements DisplayManager$DisplayListener, DisplayListenerBackend {
        DisplayListenerBackendImpl(DisplayAndroidManager arg1, org.chromium.ui.display.DisplayAndroidManager$1 arg2) {
            this(arg1);
        }

        private DisplayListenerBackendImpl(DisplayAndroidManager arg1) {
            DisplayAndroidManager.this = arg1;
            super();
        }

        public void onDisplayAdded(int arg1) {
        }

        public void onDisplayChanged(int arg3) {
            Object v0 = DisplayAndroidManager.this.mIdMap.get(arg3);
            Display v3 = DisplayAndroidManager.getDisplayManager().getDisplay(arg3);
            if(v0 != null && v3 != null) {
                ((PhysicalDisplayAndroid)v0).updateFromDisplay(v3);
            }
        }

        public void onDisplayRemoved(int arg6) {
            if(arg6 == DisplayAndroidManager.this.mMainSdkDisplayId) {
                return;
            }

            if(DisplayAndroidManager.this.mIdMap.get(arg6) == null) {
                return;
            }

            if(DisplayAndroidManager.this.mNativePointer != 0) {
                DisplayAndroidManager.this.nativeRemoveDisplay(DisplayAndroidManager.this.mNativePointer, arg6);
            }

            DisplayAndroidManager.this.mIdMap.remove(arg6);
        }

        public void startAccurateListening() {
        }

        public void startListening() {
            DisplayAndroidManager.getDisplayManager().registerDisplayListener(((DisplayManager$DisplayListener)this), null);
        }

        public void stopAccurateListening() {
        }
    }

    private static final int VIRTUAL_DISPLAY_ID_BEGIN = 0x3FFFFFFF;
    private DisplayListenerBackend mBackend;
    private final SparseArray mIdMap;
    private int mMainSdkDisplayId;
    private long mNativePointer;
    private int mNextVirtualDisplayId;
    private static DisplayAndroidManager sDisplayAndroidManager;

    static {
    }

    private DisplayAndroidManager() {
        super();
        this.mIdMap = new SparseArray();
        this.mNextVirtualDisplayId = 0x3FFFFFFF;
    }

    static Context access$000() {
        return DisplayAndroidManager.getContext();
    }

    static int access$200(DisplayAndroidManager arg0) {
        return arg0.mMainSdkDisplayId;
    }

    static SparseArray access$300(DisplayAndroidManager arg0) {
        return arg0.mIdMap;
    }

    static DisplayManager access$400() {
        return DisplayAndroidManager.getDisplayManager();
    }

    static long access$500(DisplayAndroidManager arg2) {
        return arg2.mNativePointer;
    }

    static void access$600(DisplayAndroidManager arg0, long arg1, int arg3) {
        arg0.nativeRemoveDisplay(arg1, arg3);
    }

    private DisplayAndroid addDisplay(Display arg4) {
        int v0 = arg4.getDisplayId();
        PhysicalDisplayAndroid v1 = new PhysicalDisplayAndroid(arg4);
        this.mIdMap.put(v0, v1);
        v1.updateFromDisplay(arg4);
        return ((DisplayAndroid)v1);
    }

    VirtualDisplayAndroid addVirtualDisplay() {
        VirtualDisplayAndroid v0 = new VirtualDisplayAndroid(this.getNextVirtualDisplayId());
        this.mIdMap.put(v0.getDisplayId(), v0);
        this.updateDisplayOnNativeSide(((DisplayAndroid)v0));
        return v0;
    }

    private static Context getContext() {
        return ContextUtils.getApplicationContext();
    }

    public static Display getDefaultDisplayForContext(Context arg1) {
        return arg1.getSystemService("window").getDefaultDisplay();
    }

    DisplayAndroid getDisplayAndroid(Display arg3) {
        DisplayAndroid v0_1;
        Object v0 = this.mIdMap.get(arg3.getDisplayId());
        if(v0 == null) {
            v0_1 = this.addDisplay(arg3);
        }

        return v0_1;
    }

    private static DisplayManager getDisplayManager() {
        return DisplayAndroidManager.getContext().getSystemService("display");
    }

    static DisplayAndroidManager getInstance() {
        ThreadUtils.assertOnUiThread();
        if(DisplayAndroidManager.sDisplayAndroidManager == null) {
            DisplayAndroidManager.sDisplayAndroidManager = new DisplayAndroidManager();
            DisplayAndroidManager.sDisplayAndroidManager.initialize();
        }

        return DisplayAndroidManager.sDisplayAndroidManager;
    }

    private int getNextVirtualDisplayId() {
        int v0 = this.mNextVirtualDisplayId;
        this.mNextVirtualDisplayId = v0 + 1;
        return v0;
    }

    private void initialize() {
        Display v0;
        org.chromium.ui.display.DisplayAndroidManager$1 v1 = null;
        if(Build$VERSION.SDK_INT >= 17) {
            this.mBackend = new DisplayListenerBackendImpl(this, v1);
            v0 = DisplayAndroidManager.getDisplayManager().getDisplay(0);
            if(v0 == null) {
                v0 = DisplayAndroidManager.getDefaultDisplayForContext(DisplayAndroidManager.getContext());
            }
        }
        else {
            this.mBackend = new DisplayListenerAPI16(this, v1);
            v0 = DisplayAndroidManager.getDefaultDisplayForContext(DisplayAndroidManager.getContext());
        }

        this.mMainSdkDisplayId = v0.getDisplayId();
        this.addDisplay(v0);
        this.mBackend.startListening();
    }

    private native void nativeRemoveDisplay(long arg1, int arg2) {
    }

    private native void nativeSetPrimaryDisplayId(long arg1, int arg2) {
    }

    private native void nativeUpdateDisplay(long arg1, int arg2, int arg3, int arg4, float arg5, int arg6, int arg7, int arg8, boolean arg9) {
    }

    @CalledByNative private static void onNativeSideCreated(long arg1) {
        DisplayAndroidManager.getInstance().setNativePointer(arg1);
    }

    void removeVirtualDisplay(VirtualDisplayAndroid arg6) {
        this.mIdMap.get(arg6.getDisplayId());
        if(this.mNativePointer != 0) {
            this.nativeRemoveDisplay(this.mNativePointer, arg6.getDisplayId());
        }

        this.mIdMap.remove(arg6.getDisplayId());
    }

    private void setNativePointer(long arg2) {
        this.mNativePointer = arg2;
        this.nativeSetPrimaryDisplayId(this.mNativePointer, this.mMainSdkDisplayId);
        int v2;
        for(v2 = 0; v2 < this.mIdMap.size(); ++v2) {
            this.updateDisplayOnNativeSide(this.mIdMap.valueAt(v2));
        }
    }

    public static void setUsingCustomContext(boolean arg0) {
        PhysicalDisplayAndroid.sUsingCustomContext = arg0;
    }

    void startAccurateListening() {
        this.mBackend.startAccurateListening();
    }

    void stopAccurateListening() {
        this.mBackend.stopAccurateListening();
    }

    void updateDisplayOnNativeSide(DisplayAndroid arg13) {
        if(this.mNativePointer == 0) {
            return;
        }

        this.nativeUpdateDisplay(this.mNativePointer, arg13.getDisplayId(), arg13.getDisplayWidth(), arg13.getDisplayHeight(), arg13.getDipScale(), arg13.getRotationDegrees(), arg13.getBitsPerPixel(), arg13.getBitsPerComponent(), arg13.getIsWideColorGamut());
    }
}

