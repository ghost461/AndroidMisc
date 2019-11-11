package org.xwalk.core.internal;

import android.annotation.TargetApi;
import android.app.Presentation;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.Display;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager$DisplayListener;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager;

@JNINamespace(value="xwalk") class XWalkPresentationHost implements DisplayListener {
    @TargetApi(value=17) final class PresentationScreen extends Presentation {
        private XWalkViewInternal mContentView;
        private Display mDisplay;
        private PresentationSession mSession;

        public PresentationScreen(XWalkPresentationHost arg1, PresentationSession arg2, Display arg3) {
            XWalkPresentationHost.this = arg1;
            super(arg2.context, arg3);
            this.mSession = arg2;
            this.mDisplay = arg3;
        }

        public void loadUrl(String arg2) {
            this.mContentView.loadUrl(arg2);
        }

        protected void onCreate(Bundle arg3) {
            super.onCreate(arg3);
            if(this.mContentView == null) {
                this.mContentView = new XWalkViewInternal(this.getContext());
                this.mContentView.setUIClient(new XWalkUIClientInternal(this.mContentView));
            }

            this.setContentView(this.mContentView);
        }

        protected void onStop() {
            super.onStop();
            XWalkPresentationHost.onPresentationScreenClose(this.mSession);
        }
    }

    final class PresentationSession {
        public Context context;
        public PresentationScreen presentationScreen;
        public int renderFrameID;
        public int renderProcessID;

        public PresentationSession(XWalkPresentationHost arg1, Context arg2, int arg3, int arg4) {
            XWalkPresentationHost.this = arg1;
            super();
            this.context = arg2;
            this.renderProcessID = arg3;
            this.renderFrameID = arg4;
            this.presentationScreen = null;
        }
    }

    public class RenderFrameHostId {
        public int renderFrameID;
        public int renderProcessID;

        public RenderFrameHostId(int arg1, int arg2) {
            super();
            this.renderProcessID = arg1;
            this.renderFrameID = arg2;
        }

        public boolean equals(Object arg5) {
            if((arg5 instanceof RenderFrameHostId)) {
                boolean v0 = true;
                if((((RenderFrameHostId)arg5)) == this) {
                    return 1;
                }

                if(this.renderProcessID != ((RenderFrameHostId)arg5).renderProcessID || this.renderFrameID != ((RenderFrameHostId)arg5).renderFrameID) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }

            return 0;
        }

        public int hashCode() {
            return (this.renderProcessID + 17) * 0x1F + this.renderFrameID;
        }
    }

    private static String TAG = "XWalkPresentationHost";
    private Context mApplicationContext;
    private XWalkDisplayManager mDisplayManager;
    private HashMap mExistingSessions;
    private long mNativePresentationHost;
    private static XWalkPresentationHost sInstance;

    static {
    }

    private XWalkPresentationHost(Context arg3) {
        super();
        this.mApplicationContext = arg3.getApplicationContext();
        this.mExistingSessions = new HashMap();
        this.mDisplayManager = XWalkDisplayManager.getInstance(this.mApplicationContext);
        this.setNativeObject(this.nativeInit());
        this.listenToSystemDisplayChange();
    }

    @CalledByNative public void closePresentation(int arg1, int arg2) {
        this.closeSession(arg1, arg2);
    }

    private void closeSession(int arg4, int arg5) {
        Object v0 = this.mExistingSessions.get(new RenderFrameHostId(arg4, arg5));
        if(v0 != null && Build$VERSION.SDK_INT >= 17) {
            if(((PresentationSession)v0).presentationScreen != null) {
                ((PresentationSession)v0).presentationScreen.dismiss();
                ((PresentationSession)v0).presentationScreen = null;
                this.nativeOnPresentationClosed(this.mNativePresentationHost, arg4, arg5);
            }

            this.removeContextActivity(arg4, arg5);
        }
    }

    public static XWalkPresentationHost createInstanceOnce(Context arg1) {
        if(XWalkPresentationHost.sInstance == null) {
            XWalkPresentationHost.sInstance = new XWalkPresentationHost(arg1);
        }

        return XWalkPresentationHost.sInstance;
    }

    private PresentationSession createNewSession(RenderFrameHostId arg5) {
        PresentationSession v0 = new PresentationSession(this, this.mApplicationContext, arg5.renderProcessID, arg5.renderFrameID);
        this.mExistingSessions.put(arg5, v0);
        return v0;
    }

    @CalledByNative public Display[] getAndroidDisplayInfo() {
        Display[] v0 = new Display[0];
        if(Build$VERSION.SDK_INT >= 17) {
            return this.mDisplayManager.getDisplays();
        }

        return v0;
    }

    public static XWalkPresentationHost getInstance() {
        return XWalkPresentationHost.sInstance;
    }

    public void listenToSystemDisplayChange() {
        this.mDisplayManager.registerDisplayListener(((DisplayListener)this));
    }

    private static native void nativeDestroy(long arg0) {
    }

    private native long nativeInit() {
    }

    private native void nativeOnDisplayAdded(long arg1, int arg2) {
    }

    private native void nativeOnDisplayChanged(long arg1, int arg2) {
    }

    private native void nativeOnDisplayRemoved(long arg1, int arg2) {
    }

    private native void nativeOnPresentationClosed(long arg1, int arg2, int arg3) {
    }

    private native void nativeSetupJavaPeer(long arg1) {
    }

    public void onDisplayAdded(int arg3) {
        this.nativeOnDisplayAdded(this.mNativePresentationHost, arg3);
    }

    public void onDisplayChanged(int arg3) {
        this.nativeOnDisplayChanged(this.mNativePresentationHost, arg3);
    }

    public void onDisplayRemoved(int arg3) {
        this.nativeOnDisplayRemoved(this.mNativePresentationHost, arg3);
    }

    public static void onPresentationScreenClose(PresentationSession arg4) {
        Object v4 = XWalkPresentationHost.getInstance().mExistingSessions.get(new RenderFrameHostId(arg4.renderProcessID, arg4.renderFrameID));
        if(v4 != null && Build$VERSION.SDK_INT >= 17) {
            if(((PresentationSession)v4).presentationScreen != null) {
                ((PresentationSession)v4).presentationScreen = null;
            }

            int v0 = ((PresentationSession)v4).renderProcessID;
            int v4_1 = ((PresentationSession)v4).renderFrameID;
            XWalkPresentationHost.getInstance().nativeOnPresentationClosed(XWalkPresentationHost.getInstance().mNativePresentationHost, v0, v4_1);
            XWalkPresentationHost.getInstance().removeContextActivity(v0, v4_1);
        }
    }

    private void removeContextActivity(int arg2, int arg3) {
        this.mExistingSessions.remove(new RenderFrameHostId(arg2, arg3));
    }

    private void setNativeObject(long arg1) {
        this.mNativePresentationHost = arg1;
        this.nativeSetupJavaPeer(this.mNativePresentationHost);
    }

    @CalledByNative public boolean showPresentation(int arg2, int arg3, int arg4, String arg5) {
        RenderFrameHostId v0 = new RenderFrameHostId(arg2, arg3);
        Object v2 = this.mExistingSessions.get(v0);
        if(v2 == null) {
            PresentationSession v2_1 = this.createNewSession(v0);
        }

        return this.startNewSession(((PresentationSession)v2), arg4, arg5);
    }

    private boolean startNewSession(PresentationSession arg9, int arg10, String arg11) {
        if(arg9 != null) {
            Display[] v1 = new Display[0];
            int v3 = 17;
            if(Build$VERSION.SDK_INT >= v3) {
                v1 = this.mDisplayManager.getDisplays("android.hardware.display.category.PRESENTATION");
            }

            if(v1.length <= 0) {
                goto label_46;
            }

            int v4 = v1.length;
            Display v5 = null;
            int v2;
            for(v2 = 0; v2 < v4; ++v2) {
                Display v6 = v1[v2];
                if(v6.getDisplayId() == arg10) {
                    v5 = v6;
                }
            }

            if(v5 != null && Build$VERSION.SDK_INT >= v3) {
                arg9.presentationScreen = new PresentationScreen(this, arg9, v5);
                arg9.presentationScreen.getWindow().setType(2003);
                arg9.presentationScreen.show();
                arg9.presentationScreen.loadUrl(arg11);
                return 1;
            }

            String v9 = XWalkPresentationHost.TAG;
            Log.e(v9, "Can\'t find specified display with id " + arg10);
        }

    label_46:
        Log.e(XWalkPresentationHost.TAG, "startNewSession falied!");
        return 0;
    }

    public void stopListenToSystemDisplayChange() {
        this.mDisplayManager.unregisterDisplayListener(((DisplayListener)this));
    }
}

