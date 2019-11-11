package org.chromium.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class DropdownDividerDrawable extends Drawable {
    private final Integer mBackgroundColor;
    private final Rect mDividerRect;
    private final Paint mPaint;

    public DropdownDividerDrawable(Integer arg2) {
        super();
        this.mPaint = new Paint();
        this.mDividerRect = new Rect();
        this.mBackgroundColor = arg2;
    }

    public void draw(Canvas arg3) {
        if(this.mBackgroundColor != null) {
            arg3.drawColor(this.mBackgroundColor.intValue());
        }

        arg3.drawRect(this.mDividerRect, this.mPaint);
    }

    public int getOpacity() {
        return -2;
    }

    public void onBoundsChange(Rect arg4) {
        this.mDividerRect.set(0, 0, arg4.width(), this.mDividerRect.height());
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void setDividerColor(int arg2) {
        this.mPaint.setColor(arg2);
    }

    public void setHeight(int arg4) {
        this.mDividerRect.set(0, 0, this.mDividerRect.right, arg4);
    }
}

