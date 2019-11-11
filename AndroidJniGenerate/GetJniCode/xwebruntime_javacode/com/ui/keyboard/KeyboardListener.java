package com.ui.keyboard;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.View;
import com.util.RuntimeEnviroment;

public final class KeyboardListener implements OnViewLayoutListener {
    interface KeyboardChangedCallBack {
        int getHeight();

        void onKeyboardStateChanged(boolean arg1);

        void refreshHeight(int arg1);
    }

    private static Integer LAST_SAVE_KEYBOARD_HEIGHT_PX = null;
    private static final String TAG = "MicroMsg.AppBrandKeyboardListener";
    private View mAttachedView;
    private KeyboardChangedCallBack mKeyboardChangedCallBack;
    private boolean mLastKeyboardShowing;
    private int mPreviousDisplayHeight;
    private final int[] mWindowLocation;
    private final Rect mWindowVisibleDisplayFrame;

    static {
    }

    public KeyboardListener() {
        super();
        this.mPreviousDisplayHeight = 0;
        this.mWindowLocation = new int[2];
        this.mWindowVisibleDisplayFrame = new Rect();
        this.mLastKeyboardShowing = false;
    }

    private void calculateKeyboardHeight(int arg2) {
        if(this.mPreviousDisplayHeight == 0) {
            this.mPreviousDisplayHeight = arg2;
            return;
        }

        int v0 = this.getFrameHeight() - arg2;
        if(v0 <= 0) {
            return;
        }

        if(KeyboardListener.saveKeyboardHeight(this.getContext(), v0)) {
            if(this.mKeyboardChangedCallBack == null) {
                return;
            }
            else if(this.mKeyboardChangedCallBack.getHeight() != v0) {
                this.mKeyboardChangedCallBack.refreshHeight(v0);
            }
        }
    }

    private void calculateKeyboardShowing(int arg4) {
        int v1 = 0;
        boolean v4 = this.getFrameHeight() > arg4 ? true : false;
        if(this.mLastKeyboardShowing != v4) {
            v1 = 1;
        }

        if(v1 != 0) {
            this.onKeyboardShown(v4);
        }

        this.mLastKeyboardShowing = v4;
    }

    private Context getContext() {
        Context v0 = this.mAttachedView == null ? RuntimeEnviroment.getContext() : this.mAttachedView.getContext();
        return v0;
    }

    private int getFrameHeight() {
        if(this.getRootView() == null) {
            return 0;
        }

        Rect v0 = this.mWindowVisibleDisplayFrame;
        this.getWindowVisibleDisplayFrame(v0);
        int v1 = this.isLaidOut(this.mAttachedView) ? this.mAttachedView.getMeasuredHeight() : this.getContext().getResources().getDisplayMetrics().heightPixels;
        return v1 - v0.top;
    }

    private View getRootView() {
        View v0 = this.mAttachedView == null ? null : this.mAttachedView.getRootView();
        return v0;
    }

    private void getWindowVisibleDisplayFrame(Rect arg3) {
        if(this.mAttachedView != null) {
            this.mAttachedView.getWindowVisibleDisplayFrame(arg3);
            this.mAttachedView.getLocationInWindow(this.mWindowLocation);
            arg3.top = this.mWindowLocation[1];
        }
    }

    private boolean isLaidOut(View arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg3.isLaidOut();
        }

        boolean v3 = arg3.getWidth() <= 0 || arg3.getHeight() <= 0 ? false : true;
        return v3;
    }

    public boolean isShowingKeyboard() {
        return this.mLastKeyboardShowing;
    }

    private void onKeyboardShown(boolean arg2) {
        if(this.mKeyboardChangedCallBack != null) {
            this.mKeyboardChangedCallBack.onKeyboardStateChanged(arg2);
        }
    }

    public Rect onViewLayout(View arg1, boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        this.mAttachedView = arg1;
        Rect v1 = this.mWindowVisibleDisplayFrame;
        this.getWindowVisibleDisplayFrame(v1);
        int v2 = v1.height();
        this.calculateKeyboardHeight(v2);
        this.calculateKeyboardShowing(v2);
        this.mPreviousDisplayHeight = v2;
        this.mAttachedView = null;
        return v1;
    }

    private static boolean saveKeyboardHeight(Context arg2, int arg3) {
        if(arg3 <= 0) {
            return 0;
        }

        if(KeyboardListener.LAST_SAVE_KEYBOARD_HEIGHT_PX == null) {
            KeyboardListener.LAST_SAVE_KEYBOARD_HEIGHT_PX = Integer.valueOf(KeyBoardUtil.getValidPanelHeight(arg2));
        }

        if(KeyboardListener.LAST_SAVE_KEYBOARD_HEIGHT_PX.intValue() != arg3) {
            KeyboardListener.LAST_SAVE_KEYBOARD_HEIGHT_PX = Integer.valueOf(arg3);
            return 1;
        }

        return 0;
    }

    void setBottomPanelImpl(KeyboardChangedCallBack arg1) {
        this.mKeyboardChangedCallBack = arg1;
    }
}

