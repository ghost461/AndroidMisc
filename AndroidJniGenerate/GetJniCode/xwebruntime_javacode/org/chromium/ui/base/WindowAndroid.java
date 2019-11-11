package org.chromium.ui.base;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener;
import android.view.accessibility.AccessibilityManager;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ObserverList;
import org.chromium.base.StrictModeContext;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.VSyncMonitor$Listener;
import org.chromium.ui.VSyncMonitor;
import org.chromium.ui.display.DisplayAndroid;
import org.chromium.ui.widget.Toast;

@JNINamespace(value="ui") public class WindowAndroid {
    class org.chromium.ui.base.WindowAndroid$1 implements Listener {
        org.chromium.ui.base.WindowAndroid$1(WindowAndroid arg1) {
            WindowAndroid.this = arg1;
            super();
        }

        public void onVSync(VSyncMonitor arg8, long arg9) {
            if(WindowAndroid.this.mVSyncPaused) {
                WindowAndroid.this.mPendingVSyncRequest = true;
                return;
            }

            if(WindowAndroid.this.mNativeWindowAndroid != 0) {
                WindowAndroid.this.nativeOnVSync(WindowAndroid.this.mNativeWindowAndroid, arg9, WindowAndroid.this.mVSyncMonitor.getVSyncPeriodInMicroseconds());
            }
        }
    }

    public interface IntentCallback {
        void onIntentCompleted(WindowAndroid arg1, int arg2, Intent arg3);
    }

    public interface KeyboardVisibilityListener {
        void keyboardVisibilityChanged(boolean arg1);
    }

    public interface OnCloseContextMenuListener {
        void onContextMenuClosed();
    }

    @TargetApi(value=19) class TouchExplorationMonitor {
        private AccessibilityManager$TouchExplorationStateChangeListener mTouchExplorationListener;

        TouchExplorationMonitor(WindowAndroid arg2) {
            WindowAndroid.this = arg2;
            super();
            this.mTouchExplorationListener = new AccessibilityManager$TouchExplorationStateChangeListener(arg2) {
                public void onTouchExplorationStateChanged(boolean arg2) {
                    this.this$1.this$0.mIsTouchExplorationEnabled = this.this$1.this$0.mAccessibilityManager.isTouchExplorationEnabled();
                    this.this$1.this$0.refreshWillNotDraw();
                }
            };
            arg2.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationListener);
        }

        void destroy() {
            WindowAndroid.this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationListener);
        }
    }

    public static final int START_INTENT_FAILURE = -1;
    private static final String TAG = "WindowAndroid";
    static final String WINDOW_CALLBACK_ERRORS = "window_callback_errors";
    private final AccessibilityManager mAccessibilityManager;
    private View mAnimationPlaceholderView;
    private HashSet mAnimationsOverContent;
    protected Context mApplicationContext;
    private final ObserverList mContextMenuCloseListeners;
    private WeakReference mContextRef;
    private final DisplayAndroid mDisplayAndroid;
    protected HashMap mIntentErrors;
    protected boolean mIsKeyboardShowing;
    private boolean mIsTouchExplorationEnabled;
    private ViewGroup mKeyboardAccessoryView;
    private ObserverList mKeyboardVisibilityListeners;
    private long mNativeWindowAndroid;
    protected SparseArray mOutstandingIntents;
    private boolean mPendingVSyncRequest;
    private AndroidPermissionDelegate mPermissionDelegate;
    private TouchExplorationMonitor mTouchExplorationMonitor;
    private final Listener mVSyncListener;
    private final VSyncMonitor mVSyncMonitor;
    private boolean mVSyncPaused;
    ValueCallback mValueCallBack;
    protected FrameLayout mWebviewUI;

    static {
    }

    public WindowAndroid(Context arg2) {
        this(arg2, DisplayAndroid.getNonMultiDisplay(arg2));
    }

    @SuppressLint(value={"UseSparseArrays"}) protected WindowAndroid(Context arg5, DisplayAndroid arg6) {
        super();
        this.mAnimationsOverContent = new HashSet();
        this.mKeyboardVisibilityListeners = new ObserverList();
        this.mContextMenuCloseListeners = new ObserverList();
        this.mVSyncListener = new org.chromium.ui.base.WindowAndroid$1(this);
        this.mApplicationContext = arg5.getApplicationContext();
        this.mContextRef = new WeakReference(arg5);
        this.mOutstandingIntents = new SparseArray();
        this.mIntentErrors = new HashMap();
        StrictModeContext v0 = StrictModeContext.allowDiskReads();
        Throwable v1 = null;
        try {
            this.mVSyncMonitor = new VSyncMonitor(arg5, this.mVSyncListener);
            this.mAccessibilityManager = this.mApplicationContext.getSystemService("accessibility");
            if(v0 == null) {
                goto label_36;
            }
        }
        catch(Throwable v5) {
        }
        catch(Throwable v5) {
            v1 = v5;
            try {
                throw v1;
            }
            catch(Throwable v5) {
                if(v0 != null) {
                    if(v1 != null) {
                        try {
                            v0.close();
                        }
                        catch(Throwable v6) {
                            ThrowableExtension.addSuppressed(v1, v6);
                        }
                    }
                    else {
                        v0.close();
                    }

                    goto label_65;
                }
                else {
                label_65:
                    throw v5;
                }

                goto label_36;
            }
        }

        v0.close();
    label_36:
        this.mDisplayAndroid = arg6;
        if(Build$VERSION.SDK_INT >= 26 && !Build$VERSION.RELEASE.equals("8.0.0") && WindowAndroid.activityFromContext(arg5) != null) {
            arg6.updateIsDisplayServerWideColorGamut(Boolean.valueOf(arg5.getResources().getConfiguration().isScreenWideColorGamut()));
        }
    }

    static boolean access$002(WindowAndroid arg0, boolean arg1) {
        arg0.mIsTouchExplorationEnabled = arg1;
        return arg1;
    }

    static AccessibilityManager access$100(WindowAndroid arg0) {
        return arg0.mAccessibilityManager;
    }

    static void access$200(WindowAndroid arg0) {
        arg0.refreshWillNotDraw();
    }

    static boolean access$300(WindowAndroid arg0) {
        return arg0.mVSyncPaused;
    }

    static boolean access$402(WindowAndroid arg0, boolean arg1) {
        arg0.mPendingVSyncRequest = arg1;
        return arg1;
    }

    static long access$500(WindowAndroid arg2) {
        return arg2.mNativeWindowAndroid;
    }

    static VSyncMonitor access$600(WindowAndroid arg0) {
        return arg0.mVSyncMonitor;
    }

    static void access$700(WindowAndroid arg0, long arg1, long arg3, long arg5) {
        arg0.nativeOnVSync(arg1, arg3, arg5);
    }

    static HashSet access$800(WindowAndroid arg0) {
        return arg0.mAnimationsOverContent;
    }

    public static Activity activityFromContext(Context arg1) {
        if((arg1 instanceof Activity)) {
            return arg1;
        }

        if((arg1 instanceof ContextWrapper)) {
            return WindowAndroid.activityFromContext(((ContextWrapper)arg1).getBaseContext());
        }

        return null;
    }

    public void addContextMenuCloseListener(OnCloseContextMenuListener arg2) {
        this.mContextMenuCloseListeners.addObserver(arg2);
    }

    public void addKeyboardVisibilityListener(KeyboardVisibilityListener arg2) {
        if(this.mKeyboardVisibilityListeners.isEmpty()) {
            this.registerKeyboardVisibilityCallbacks();
        }

        this.mKeyboardVisibilityListeners.addObserver(arg2);
    }

    @CalledByNative public final boolean canRequestPermission(String arg4) {
        if(this.mPermissionDelegate != null) {
            return this.mPermissionDelegate.canRequestPermission(arg4);
        }

        Log.w("WindowAndroid", "Cannot determine the request permission state as the context is not an Activity", new Object[0]);
        return 0;
    }

    public boolean canResolveActivity(Intent arg3) {
        boolean v1 = false;
        if(this.mApplicationContext.getPackageManager().queryIntentActivities(arg3, 0).size() > 0) {
            v1 = true;
        }

        return v1;
    }

    public void cancelIntent(int arg4) {
        Log.d("WindowAndroid", "Can\'t cancel intent as context is not an Activity: " + arg4);
    }

    @CalledByNative private void clearNativePointer() {
        this.mNativeWindowAndroid = 0;
    }

    @CalledByNative private static long createForTesting() {
        return new WindowAndroid(ContextUtils.getApplicationContext()).getNativePointer();
    }

    public static View createView(Context arg1, int arg2) {
        return LayoutInflater.from(arg1).inflate(arg2, null);
    }

    public static View createView(Context arg0, int arg1, ViewGroup arg2, boolean arg3) {
        return LayoutInflater.from(arg0).inflate(arg1, arg2, arg3);
    }

    public void destroy() {
        if(this.mNativeWindowAndroid != 0) {
            this.nativeDestroy(this.mNativeWindowAndroid);
        }

        if(Build$VERSION.SDK_INT >= 19 && this.mTouchExplorationMonitor != null) {
            this.mTouchExplorationMonitor.destroy();
        }
    }

    public static void dumpContexs(Context arg2, StringBuilder arg3) {
        if((arg2 instanceof Activity)) {
            arg3.append(",Activity:" + arg2);
        }
        else if((arg2 instanceof ContextWrapper)) {
            arg3.append(",ContextWrapper:" + arg2);
            Context v0_1 = arg2.getBaseContext();
            if(v0_1 != arg2) {
                WindowAndroid.dumpContexs(v0_1, arg3);
            }
            else {
                arg3.append(",got null");
            }
        }
        else {
            arg3.append(",got null");
        }
    }

    public WeakReference getActivity() {
        return new WeakReference(null);
    }

    protected AndroidPermissionDelegate getAndroidPermissionDelegate() {
        return this.mPermissionDelegate;
    }

    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public WeakReference getContext() {
        return new WeakReference(this.mContextRef.get());
    }

    public DisplayAndroid getDisplay() {
        return this.mDisplayAndroid;
    }

    public ViewGroup getKeyboardAccessoryView() {
        return this.mKeyboardAccessoryView;
    }

    private float getMouseWheelScrollFactor() {
        TypedValue v0 = new TypedValue();
        Object v1 = this.getContext().get();
        if(v1 != null && (((Context)v1).getTheme().resolveAttribute(0x101004D, v0, true))) {
            return v0.getDimension(((Context)v1).getResources().getDisplayMetrics());
        }

        return 0;
    }

    @CalledByNative private long getNativePointer() {
        if(this.mNativeWindowAndroid == 0) {
            this.mNativeWindowAndroid = this.nativeInit(this.mDisplayAndroid.getDisplayId(), this.getMouseWheelScrollFactor());
            this.nativeSetVSyncPaused(this.mNativeWindowAndroid, this.mVSyncPaused);
        }

        return this.mNativeWindowAndroid;
    }

    public View getReadbackView() {
        return null;
    }

    public long getVsyncPeriodInMillis() {
        return this.mVSyncMonitor.getVSyncPeriodInMicroseconds() / 1000;
    }

    @CalledByNative private IBinder getWindowToken() {
        Activity v0 = WindowAndroid.activityFromContext(this.mContextRef.get());
        IBinder v1 = null;
        if(v0 == null) {
            return v1;
        }

        Window v0_1 = v0.getWindow();
        if(v0_1 == null) {
            return v1;
        }

        View v0_2 = v0_1.peekDecorView();
        if(v0_2 == null) {
            return v1;
        }

        return v0_2.getWindowToken();
    }

    @CalledByNative public final boolean hasPermission(String arg4) {
        if(this.mPermissionDelegate != null) {
            return this.mPermissionDelegate.hasPermission(arg4);
        }

        boolean v4 = ApiCompatibilityUtils.checkPermission(this.mApplicationContext, arg4, Process.myPid(), Process.myUid()) == 0 ? true : false;
        return v4;
    }

    public boolean isInsideVSync() {
        return this.mVSyncMonitor.isInsideVSync();
    }

    public final boolean isPermissionRevokedByPolicy(String arg4) {
        if(this.mPermissionDelegate != null) {
            return this.mPermissionDelegate.isPermissionRevokedByPolicy(arg4);
        }

        Log.w("WindowAndroid", "Cannot determine the policy permission state as the context is not an Activity", new Object[0]);
        return 0;
    }

    protected void keyboardVisibilityPossiblyChanged(boolean arg3) {
        if(this.mIsKeyboardShowing == arg3) {
            return;
        }

        this.mIsKeyboardShowing = arg3;
        Iterator v0 = this.mKeyboardVisibilityListeners.iterator();
        while(v0.hasNext()) {
            v0.next().keyboardVisibilityChanged(arg3);
        }
    }

    private native void nativeDestroy(long arg1) {
    }

    private native long nativeInit(int arg1, float arg2) {
    }

    private native void nativeOnActivityStarted(long arg1) {
    }

    private native void nativeOnActivityStopped(long arg1) {
    }

    private native void nativeOnVSync(long arg1, long arg2, long arg3) {
    }

    private native void nativeOnVisibilityChanged(long arg1, boolean arg2) {
    }

    private native void nativeSetVSyncPaused(long arg1, boolean arg2) {
    }

    protected void onActivityStarted() {
        if(this.mNativeWindowAndroid == 0) {
            return;
        }

        android.util.Log.i("WindowAndroid", "onActivityStarted: " + this.mNativeWindowAndroid);
        this.nativeOnActivityStarted(this.mNativeWindowAndroid);
    }

    protected void onActivityStopped() {
        if(this.mNativeWindowAndroid == 0) {
            return;
        }

        android.util.Log.i("WindowAndroid", "nativeOnActivityStopped:" + this.mNativeWindowAndroid);
        this.nativeOnActivityStopped(this.mNativeWindowAndroid);
    }

    public void onContextMenuClosed() {
        Iterator v0 = this.mContextMenuCloseListeners.iterator();
        while(v0.hasNext()) {
            v0.next().onContextMenuClosed();
        }
    }

    public void onVisibilityChanged(boolean arg6) {
        if(this.mNativeWindowAndroid == 0) {
            return;
        }

        this.nativeOnVisibilityChanged(this.mNativeWindowAndroid, arg6);
    }

    private void refreshWillNotDraw() {
        boolean v0 = (this.mIsTouchExplorationEnabled) || !this.mAnimationsOverContent.isEmpty() ? false : true;
        if(this.mAnimationPlaceholderView.willNotDraw() != v0) {
            this.mAnimationPlaceholderView.setWillNotDraw(v0);
        }
    }

    protected void registerKeyboardVisibilityCallbacks() {
    }

    public void removeContextMenuCloseListener(OnCloseContextMenuListener arg2) {
        this.mContextMenuCloseListeners.removeObserver(arg2);
    }

    public boolean removeIntentCallback(IntentCallback arg2) {
        int v2 = this.mOutstandingIntents.indexOfValue(arg2);
        if(v2 < 0) {
            return 0;
        }

        this.mOutstandingIntents.remove(v2);
        this.mIntentErrors.remove(Integer.valueOf(v2));
        return 1;
    }

    public void removeKeyboardVisibilityListener(KeyboardVisibilityListener arg2) {
        this.mKeyboardVisibilityListeners.removeObserver(arg2);
        if(this.mKeyboardVisibilityListeners.isEmpty()) {
            this.unregisterKeyboardVisibilityCallbacks();
        }
    }

    public final void requestPermissions(String[] arg2, PermissionCallback arg3) {
        if(this.mPermissionDelegate != null) {
            this.mPermissionDelegate.requestPermissions(arg2, arg3);
            return;
        }

        Log.w("WindowAndroid", "Cannot request permissions as the context is not an Activity", new Object[0]);
    }

    @CalledByNative private void requestVSyncUpdate() {
        if(this.mVSyncPaused) {
            this.mPendingVSyncRequest = true;
            return;
        }

        this.mVSyncMonitor.requestUpdate();
    }

    public void restoreInstanceState(Bundle arg2) {
        if(arg2 == null) {
            return;
        }

        Serializable v2 = arg2.getSerializable("window_callback_errors");
        if((v2 instanceof HashMap)) {
            this.mIntentErrors = ((HashMap)v2);
        }
    }

    public void saveInstanceState(Bundle arg3) {
        arg3.putSerializable("window_callback_errors", this.mIntentErrors);
    }

    public void sendBroadcast(Intent arg2) {
        this.mApplicationContext.sendBroadcast(arg2);
    }

    public void setActivityChangedCallBack(ValueCallback arg1) {
        this.mValueCallBack = arg1;
    }

    @VisibleForTesting public void setAndroidPermissionDelegate(AndroidPermissionDelegate arg1) {
        this.mPermissionDelegate = arg1;
    }

    public void setAnimationPlaceholderView(View arg2) {
        this.mAnimationPlaceholderView = arg2;
        this.mIsTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        this.refreshWillNotDraw();
        if(Build$VERSION.SDK_INT >= 19) {
            this.mTouchExplorationMonitor = new TouchExplorationMonitor(this);
        }
    }

    public void setKeyboardAccessoryView(ViewGroup arg1) {
        this.mKeyboardAccessoryView = arg1;
    }

    public void setVSyncPaused(boolean arg6) {
        if(this.mVSyncPaused == arg6) {
            return;
        }

        this.mVSyncPaused = arg6;
        if(!this.mVSyncPaused && (this.mPendingVSyncRequest)) {
            this.requestVSyncUpdate();
        }

        if(this.mNativeWindowAndroid != 0) {
            this.nativeSetVSyncPaused(this.mNativeWindowAndroid, arg6);
        }
    }

    public void setWebviewUI(FrameLayout arg1) {
        this.mWebviewUI = arg1;
    }

    protected void showCallbackNonExistentError(String arg1) {
        this.showError(arg1);
    }

    public int showCancelableIntent(PendingIntent arg2, IntentCallback arg3, Integer arg4) {
        Log.d("WindowAndroid", "Can\'t show intent as context is not an Activity: " + arg2);
        return -1;
    }

    public int showCancelableIntent(Intent arg2, IntentCallback arg3, Integer arg4) {
        Log.d("WindowAndroid", "Can\'t show intent as context is not an Activity: " + arg2);
        return -1;
    }

    public int showCancelableIntent(Callback arg1, IntentCallback arg2, Integer arg3) {
        Log.d("WindowAndroid", "Can\'t show intent as context is not an Activity");
        return -1;
    }

    public void showError(String arg3) {
        if(arg3 != null) {
            Toast.makeText(this.mApplicationContext, ((CharSequence)arg3), 0).show();
        }
    }

    public void showError(int arg2) {
        this.showError(this.mApplicationContext.getString(arg2));
    }

    public boolean showIntent(PendingIntent arg1, IntentCallback arg2, Integer arg3) {
        boolean v1 = this.showCancelableIntent(arg1, arg2, arg3) >= 0 ? true : false;
        return v1;
    }

    public boolean showIntent(Intent arg1, IntentCallback arg2, Integer arg3) {
        boolean v1 = this.showCancelableIntent(arg1, arg2, arg3) >= 0 ? true : false;
        return v1;
    }

    public void startAnimationOverContent(Animator arg2) {
        if(this.mAnimationPlaceholderView == null) {
            return;
        }

        if(arg2.isStarted()) {
            throw new IllegalArgumentException("Already started.");
        }

        if(!this.mAnimationsOverContent.add(arg2)) {
            throw new IllegalArgumentException("Already Added.");
        }

        arg2.start();
        this.refreshWillNotDraw();
        arg2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                arg2.removeListener(((Animator$AnimatorListener)this));
                WindowAndroid.this.mAnimationsOverContent.remove(arg2);
                WindowAndroid.this.refreshWillNotDraw();
            }
        });
    }

    protected void unregisterKeyboardVisibilityCallbacks() {
    }
}

