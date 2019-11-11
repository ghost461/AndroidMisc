package org.xwalk.core.internal.videofullscreen;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public class ClickableFrameLayout extends FrameLayout {
    private GestureDetector mGestureDetector;
    private View$OnClickListener mOnClickListener;

    public ClickableFrameLayout(Context arg1) {
        super(arg1);
    }

    public ClickableFrameLayout(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    public ClickableFrameLayout(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
    }

    @TargetApi(value=21) public ClickableFrameLayout(Context arg1, AttributeSet arg2, int arg3, int arg4) {
        super(arg1, arg2, arg3, arg4);
    }

    public boolean onInterceptTouchEvent(MotionEvent arg1) {
        boolean v1 = this.mOnClickListener != null ? true : false;
        return v1;
    }

    public void setGestureDetector(GestureDetector arg1) {
        this.mGestureDetector = arg1;
    }

    public void setOnClickListener(View$OnClickListener arg1) {
        super.setOnClickListener(arg1);
        this.mOnClickListener = arg1;
    }
}

