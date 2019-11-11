package org.chromium.components.web_contents_delegate_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class ColorPickerMoreButton extends Button {
    private Paint mBorderPaint;

    public ColorPickerMoreButton(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.init();
    }

    public ColorPickerMoreButton(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.init();
    }

    public void init() {
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStyle(Paint$Style.STROKE);
        this.mBorderPaint.setColor(-1);
        this.mBorderPaint.setStrokeWidth(1f);
        this.mBorderPaint.setAntiAlias(false);
    }

    protected void onDraw(Canvas arg9) {
        arg9.drawRect(0.5f, 0.5f, (((float)this.getWidth())) - 1.5f, (((float)this.getHeight())) - 1.5f, this.mBorderPaint);
        super.onDraw(arg9);
    }
}

