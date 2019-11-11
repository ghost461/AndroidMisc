package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Join;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$styleable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface ArrowDirection {
    }

    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float ARROW_HEAD_ANGLE;
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;

    static {
        DrawerArrowDrawable.ARROW_HEAD_ANGLE = ((float)Math.toRadians(45));
    }

    public DrawerArrowDrawable(Context arg7) {
        super();
        this.mPaint = new Paint();
        this.mPath = new Path();
        this.mVerticalMirror = false;
        this.mDirection = 2;
        this.mPaint.setStyle(Paint$Style.STROKE);
        this.mPaint.setStrokeJoin(Paint$Join.MITER);
        this.mPaint.setStrokeCap(Paint$Cap.BUTT);
        this.mPaint.setAntiAlias(true);
        TypedArray v7 = arg7.getTheme().obtainStyledAttributes(null, styleable.DrawerArrowToggle, attr.drawerArrowStyle, style.Base_Widget_AppCompat_DrawerArrowToggle);
        this.setColor(v7.getColor(styleable.DrawerArrowToggle_color, 0));
        this.setBarThickness(v7.getDimension(styleable.DrawerArrowToggle_thickness, 0f));
        this.setSpinEnabled(v7.getBoolean(styleable.DrawerArrowToggle_spinBars, true));
        this.setGapSize(((float)Math.round(v7.getDimension(styleable.DrawerArrowToggle_gapBetweenBars, 0f))));
        this.mSize = v7.getDimensionPixelSize(styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarLength = ((float)Math.round(v7.getDimension(styleable.DrawerArrowToggle_barLength, 0f)));
        this.mArrowHeadLength = ((float)Math.round(v7.getDimension(styleable.DrawerArrowToggle_arrowHeadLength, 0f)));
        this.mArrowShaftLength = v7.getDimension(styleable.DrawerArrowToggle_arrowShaftLength, 0f);
        v7.recycle();
    }

    public void draw(Canvas arg21) {
        DrawerArrowDrawable v0 = this;
        Canvas v1 = arg21;
        Rect v2 = this.getBounds();
        int v3 = v0.mDirection;
        int v5 = 0;
        int v6 = 1;
        if(v3 != 3) {
            switch(v3) {
                case 0: {
                    goto label_16;
                }
                case 1: {
                    goto label_11;
                }
            }

            if(DrawableCompat.getLayoutDirection(((Drawable)this)) != 1) {
                goto label_16;
            }

            goto label_11;
        }
        else if(DrawableCompat.getLayoutDirection(((Drawable)this)) == 0) {
        label_11:
            v5 = 1;
        }

    label_16:
        float v3_1 = DrawerArrowDrawable.lerp(v0.mBarLength, ((float)Math.sqrt(((double)(v0.mArrowHeadLength * v0.mArrowHeadLength * 2f)))), v0.mProgress);
        float v7 = DrawerArrowDrawable.lerp(v0.mBarLength, v0.mArrowShaftLength, v0.mProgress);
        float v8 = ((float)Math.round(DrawerArrowDrawable.lerp(0f, v0.mMaxCutForBarSize, v0.mProgress)));
        float v9 = DrawerArrowDrawable.lerp(0f, DrawerArrowDrawable.ARROW_HEAD_ANGLE, v0.mProgress);
        float v11 = v5 != 0 ? 0f : -180f;
        float v12 = 180f;
        float v13 = v5 != 0 ? 180f : 0f;
        v11 = DrawerArrowDrawable.lerp(v11, v13, v0.mProgress);
        double v13_1 = ((double)v3_1);
        float v15 = v11;
        double v10 = ((double)v9);
        int v18 = v5;
        v3_1 = ((float)Math.round(v13_1 * Math.cos(v10)));
        float v4 = ((float)Math.round(v13_1 * Math.sin(v10)));
        v0.mPath.rewind();
        float v5_1 = DrawerArrowDrawable.lerp(v0.mBarGap + v0.mPaint.getStrokeWidth(), -v0.mMaxCutForBarSize, v0.mProgress);
        v9 = -v7 / 2f;
        v0.mPath.moveTo(v9 + v8, 0f);
        v0.mPath.rLineTo(v7 - v8 * 2f, 0f);
        v0.mPath.moveTo(v9, v5_1);
        v0.mPath.rLineTo(v3_1, v4);
        v0.mPath.moveTo(v9, -v5_1);
        v0.mPath.rLineTo(v3_1, -v4);
        v0.mPath.close();
        arg21.save();
        v3_1 = v0.mPaint.getStrokeWidth();
        v1.translate(((float)v2.centerX()), (((float)((((int)((((float)v2.height())) - 3f * v3_1 - v0.mBarGap * 2f))) / 4 * 2))) + (v3_1 * 1.5f + v0.mBarGap));
        if(v0.mSpin) {
            if((v0.mVerticalMirror ^ v18) != 0) {
                v6 = -1;
            }

            v1.rotate(v15 * (((float)v6)));
        }
        else {
            if(v18 == 0) {
                goto label_132;
            }

            v1.rotate(v12);
        }

    label_132:
        v1.drawPath(v0.mPath, v0.mPaint);
        arg21.restore();
    }

    public float getArrowHeadLength() {
        return this.mArrowHeadLength;
    }

    public float getArrowShaftLength() {
        return this.mArrowShaftLength;
    }

    public float getBarLength() {
        return this.mBarLength;
    }

    public float getBarThickness() {
        return this.mPaint.getStrokeWidth();
    }

    @ColorInt public int getColor() {
        return this.mPaint.getColor();
    }

    public int getDirection() {
        return this.mDirection;
    }

    public float getGapSize() {
        return this.mBarGap;
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    public int getOpacity() {
        return -3;
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    @FloatRange(from=0, to=1) public float getProgress() {
        return this.mProgress;
    }

    public boolean isSpinEnabled() {
        return this.mSpin;
    }

    private static float lerp(float arg0, float arg1, float arg2) {
        return arg0 + (arg1 - arg0) * arg2;
    }

    public void setAlpha(int arg2) {
        if(arg2 != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(arg2);
            this.invalidateSelf();
        }
    }

    public void setArrowHeadLength(float arg2) {
        if(this.mArrowHeadLength != arg2) {
            this.mArrowHeadLength = arg2;
            this.invalidateSelf();
        }
    }

    public void setArrowShaftLength(float arg2) {
        if(this.mArrowShaftLength != arg2) {
            this.mArrowShaftLength = arg2;
            this.invalidateSelf();
        }
    }

    public void setBarLength(float arg2) {
        if(this.mBarLength != arg2) {
            this.mBarLength = arg2;
            this.invalidateSelf();
        }
    }

    public void setBarThickness(float arg5) {
        if(this.mPaint.getStrokeWidth() != arg5) {
            this.mPaint.setStrokeWidth(arg5);
            this.mMaxCutForBarSize = ((float)((((double)(arg5 / 2f))) * Math.cos(((double)DrawerArrowDrawable.ARROW_HEAD_ANGLE))));
            this.invalidateSelf();
        }
    }

    public void setColor(@ColorInt int arg2) {
        if(arg2 != this.mPaint.getColor()) {
            this.mPaint.setColor(arg2);
            this.invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter arg2) {
        this.mPaint.setColorFilter(arg2);
        this.invalidateSelf();
    }

    public void setDirection(int arg2) {
        if(arg2 != this.mDirection) {
            this.mDirection = arg2;
            this.invalidateSelf();
        }
    }

    public void setGapSize(float arg2) {
        if(arg2 != this.mBarGap) {
            this.mBarGap = arg2;
            this.invalidateSelf();
        }
    }

    public void setProgress(@FloatRange(from=0, to=1) float arg2) {
        if(this.mProgress != arg2) {
            this.mProgress = arg2;
            this.invalidateSelf();
        }
    }

    public void setSpinEnabled(boolean arg2) {
        if(this.mSpin != arg2) {
            this.mSpin = arg2;
            this.invalidateSelf();
        }
    }

    public void setVerticalMirror(boolean arg2) {
        if(this.mVerticalMirror != arg2) {
            this.mVerticalMirror = arg2;
            this.invalidateSelf();
        }
    }
}

