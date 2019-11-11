package org.chromium.content.browser.input;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.UsedByReflection;

@UsedByReflection(value="ThreadedInputConnectionFactory.java") public class ThreadedInputConnectionProxyView extends View {
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private final View mContainerView;
    private final AtomicBoolean mFocused;
    private final Handler mImeThreadHandler;
    private final AtomicReference mRootView;
    private final AtomicBoolean mWindowFocused;
    private final AtomicReference mWindowToken;

    ThreadedInputConnectionProxyView(Context arg1, Handler arg2, View arg3) {
        super(arg1);
        this.mFocused = new AtomicBoolean();
        this.mWindowFocused = new AtomicBoolean();
        this.mWindowToken = new AtomicReference();
        this.mRootView = new AtomicReference();
        this.mImeThreadHandler = arg2;
        this.mContainerView = arg3;
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setVisibility(0);
        this.mFocused.set(this.mContainerView.hasFocus());
        this.mWindowFocused.set(this.mContainerView.hasWindowFocus());
        this.mWindowToken.set(this.mContainerView.getWindowToken());
        this.mRootView.set(this.mContainerView.getRootView());
    }

    static View access$000(ThreadedInputConnectionProxyView arg0) {
        return arg0.mContainerView;
    }

    public boolean checkInputConnectionProxy(View arg2) {
        boolean v2 = this.mContainerView == arg2 ? true : false;
        return v2;
    }

    public Handler getHandler() {
        return this.mImeThreadHandler;
    }

    public View getRootView() {
        View v0_1;
        if(this.mWindowFocused.get()) {
            Object v0 = this.mRootView.get();
        }
        else {
            v0_1 = null;
        }

        return v0_1;
    }

    public IBinder getWindowToken() {
        return this.mWindowToken.get();
    }

    public boolean hasWindowFocus() {
        return this.mWindowFocused.get();
    }

    public boolean isFocused() {
        return this.mFocused.get();
    }

    public boolean onCheckIsTextEditor() {
        return 1;
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return ThreadUtils.runOnUiThreadBlockingNoException(new Callable(arg2) {
            public InputConnection call() throws Exception {
                return ThreadedInputConnectionProxyView.this.mContainerView.onCreateInputConnection(this.val$outAttrs);
            }

            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public void onOriginalViewAttachedToWindow() {
        this.mWindowToken.set(this.mContainerView.getWindowToken());
        this.mRootView.set(this.mContainerView.getRootView());
    }

    public void onOriginalViewDetachedFromWindow() {
        this.mWindowToken.set(null);
        this.mRootView.set(null);
    }

    public void onOriginalViewFocusChanged(boolean arg2) {
        this.mFocused.set(arg2);
    }

    public void onOriginalViewWindowFocusChanged(boolean arg2) {
        this.mWindowFocused.set(arg2);
    }

    public void onWindowFocusChanged(boolean arg1) {
        super.onWindowFocusChanged(arg1);
    }
}

