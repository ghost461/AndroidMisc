package org.chromium.content.browser.input;

import android.os.Build$VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.content_public.browser.InputMethodManagerWrapper;

public class ThreadedInputConnectionFactory implements Factory {
    class CheckInvalidator {
        private boolean mInvalid;

        CheckInvalidator(org.chromium.content.browser.input.ThreadedInputConnectionFactory$1 arg1) {
            this();
        }

        private CheckInvalidator() {
            super();
        }

        public void invalidate() {
            ImeUtils.checkOnUiThread();
            this.mInvalid = true;
        }

        public boolean isInvalid() {
            ImeUtils.checkOnUiThread();
            return this.mInvalid;
        }
    }

    class LazyHandlerHolder {
        private static final Handler sHandler;

        static {
            HandlerThread v0 = new HandlerThread("InputConnectionHandlerThread", 5);
            v0.start();
            LazyHandlerHolder.sHandler = new Handler(v0.getLooper());
        }

        private LazyHandlerHolder() {
            super();
        }

        static Handler access$000() {
            return LazyHandlerHolder.sHandler;
        }
    }

    private static final int CHECK_REGISTER_RETRY = 1;
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private CheckInvalidator mCheckInvalidator;
    private final InputMethodManagerWrapper mInputMethodManagerWrapper;
    private final InputMethodUma mInputMethodUma;
    private ThreadedInputConnectionProxyView mProxyView;
    private boolean mReentrantTriggering;
    private ThreadedInputConnection mThreadedInputConnection;

    ThreadedInputConnectionFactory(InputMethodManagerWrapper arg1) {
        super();
        this.mInputMethodManagerWrapper = arg1;
        this.mInputMethodUma = this.createInputMethodUma();
    }

    static ThreadedInputConnectionProxyView access$200(ThreadedInputConnectionFactory arg0) {
        return arg0.mProxyView;
    }

    static InputMethodManagerWrapper access$300(ThreadedInputConnectionFactory arg0) {
        return arg0.mInputMethodManagerWrapper;
    }

    static CheckInvalidator access$400(ThreadedInputConnectionFactory arg0) {
        return arg0.mCheckInvalidator;
    }

    static void access$500(ThreadedInputConnectionFactory arg0, View arg1, CheckInvalidator arg2, int arg3) {
        arg0.postCheckRegisterResultOnUiThread(arg1, arg2, arg3);
    }

    static void access$600(ThreadedInputConnectionFactory arg0, View arg1, CheckInvalidator arg2, int arg3) {
        arg0.checkRegisterResult(arg1, arg2, arg3);
    }

    private void checkRegisterResult(View arg3, CheckInvalidator arg4, int arg5) {
        if(this.mInputMethodManagerWrapper.isActive(this.mProxyView)) {
            this.onRegisterProxyViewSuccess();
            return;
        }

        if(arg5 > 0) {
            this.postCheckRegisterResultOnUiThread(arg3, arg4, arg5 - 1);
            return;
        }

        if(arg4.isInvalid()) {
            return;
        }

        this.onRegisterProxyViewFailure();
    }

    @VisibleForTesting protected InputMethodUma createInputMethodUma() {
        return new InputMethodUma();
    }

    @VisibleForTesting protected ThreadedInputConnectionProxyView createProxyView(Handler arg3, View arg4) {
        return new ThreadedInputConnectionProxyView(arg4.getContext(), arg3, arg4);
    }

    public Handler getHandler() {
        return LazyHandlerHolder.access$000();
    }

    public ChromiumBaseInputConnection initializeAndGet(View arg1, ImeAdapterImpl arg2, int arg3, int arg4, int arg5, int arg6, int arg7, EditorInfo arg8) {
        return this.initializeAndGet(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    public ThreadedInputConnection initializeAndGet(View arg1, ImeAdapterImpl arg2, int arg3, int arg4, int arg5, int arg6, int arg7, EditorInfo arg8) {
        ImeUtils.checkOnUiThread();
        ImeUtils.computeEditorInfo(arg3, arg4, arg5, arg6, arg7, arg8);
        if(Build$VERSION.SDK_INT < 24 || ("com.htc.android.mail".equals(arg1.getContext().getPackageName()))) {
            if(this.mCheckInvalidator != null) {
                this.mCheckInvalidator.invalidate();
            }

            if(!this.shouldTriggerDelayedOnCreateInputConnection()) {
                goto label_19;
            }

            this.triggerDelayedOnCreateInputConnection(arg1);
            return null;
        }

    label_19:
        if(this.mThreadedInputConnection == null) {
            this.mThreadedInputConnection = new ThreadedInputConnection(arg1, arg2, this.getHandler());
        }
        else {
            this.mThreadedInputConnection.resetOnUiThread();
        }

        return this.mThreadedInputConnection;
    }

    @VisibleForTesting protected void onRegisterProxyViewFailure() {
        Log.w("cr_Ime", "onRegisterProxyViewFailure", new Object[0]);
        this.mInputMethodUma.recordProxyViewFailure();
    }

    @VisibleForTesting protected void onRegisterProxyViewSuccess() {
        Log.d("cr_Ime", "onRegisterProxyViewSuccess");
        this.mInputMethodUma.recordProxyViewSuccess();
    }

    public void onViewAttachedToWindow() {
        if(this.mProxyView != null) {
            this.mProxyView.onOriginalViewAttachedToWindow();
        }
    }

    public void onViewDetachedFromWindow() {
        if(this.mCheckInvalidator != null) {
            this.mCheckInvalidator.invalidate();
        }

        if(this.mProxyView != null) {
            this.mProxyView.onOriginalViewDetachedFromWindow();
        }
    }

    public void onViewFocusChanged(boolean arg2) {
        if(!arg2 && this.mCheckInvalidator != null) {
            this.mCheckInvalidator.invalidate();
        }

        if(this.mProxyView != null) {
            this.mProxyView.onOriginalViewFocusChanged(arg2);
        }
    }

    public void onWindowFocusChanged(boolean arg2) {
        if(!arg2 && this.mCheckInvalidator != null) {
            this.mCheckInvalidator.invalidate();
        }

        if(this.mProxyView != null) {
            this.mProxyView.onOriginalViewWindowFocusChanged(arg2);
        }
    }

    private void postCheckRegisterResultOnUiThread(View arg3, CheckInvalidator arg4, int arg5) {
        Handler v0 = arg3.getHandler();
        if(v0 == null) {
            return;
        }

        v0.post(new Runnable(arg3, arg4, arg5) {
            public void run() {
                ThreadedInputConnectionFactory.this.checkRegisterResult(this.val$view, this.val$checkInvalidator, this.val$retry);
            }
        });
    }

    private boolean shouldTriggerDelayedOnCreateInputConnection() {
        StackTraceElement[] v0 = Thread.currentThread().getStackTrace();
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            String v4 = v0[v3].getClassName();
            if(v4 != null && ((v4.contains(ThreadedInputConnectionProxyView.class.getName())) || (v4.contains("TestInputMethodManagerWrapper")))) {
                return 0;
            }
        }

        return 1;
    }

    private void triggerDelayedOnCreateInputConnection(View arg3) {
        if(this.mReentrantTriggering) {
            return;
        }

        if(!arg3.hasFocus()) {
            return;
        }

        this.mCheckInvalidator = new CheckInvalidator(null);
        if(!arg3.hasWindowFocus()) {
            this.mCheckInvalidator.invalidate();
        }

        this.mProxyView = this.createProxyView(this.getHandler(), arg3);
        this.mReentrantTriggering = true;
        this.mProxyView.requestFocus();
        this.mReentrantTriggering = false;
        arg3.getHandler().post(new Runnable(arg3) {
            public void run() {
                ThreadedInputConnectionFactory.this.mProxyView.onWindowFocusChanged(true);
                ThreadedInputConnectionFactory.this.mInputMethodManagerWrapper.isActive(this.val$view);
                ThreadedInputConnectionFactory.this.getHandler().post(new Runnable() {
                    public void run() {
                        this.this$1.this$0.postCheckRegisterResultOnUiThread(this.this$1.val$view, this.this$1.this$0.mCheckInvalidator, 1);
                    }
                });
            }
        });
    }
}

