package com.tencent.xweb.screenshot;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class LongScreenshotView extends FrameLayout {
    public interface LongScreenshotViewCallback {
    }

    private ImageView mScreenshotContainer;

    public LongScreenshotView(Context arg1) {
        super(arg1);
        this.initView();
    }

    public void animateToLast() {
    }

    private void initView() {
        this.mScreenshotContainer = new ImageView(this.getContext());
        this.addView(this.mScreenshotContainer, new FrameLayout$LayoutParams(-1, -1));
        this.mScreenshotContainer.setColorFilter(Color.argb(100, 0xFF, 0, 0));
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        return 1;
    }
}

