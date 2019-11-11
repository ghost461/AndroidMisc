package android.support.v4.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path$FillType;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable extends Drawable implements Animatable {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface ProgressDrawableSize {
    }

    class Ring {
        int mAlpha;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint;
        float mArrowScale;
        int mArrowWidth;
        final Paint mCirclePaint;
        int mColorIndex;
        int[] mColors;
        int mCurrentColor;
        float mEndTrim;
        final Paint mPaint;
        float mRingCenterRadius;
        float mRotation;
        boolean mShowArrow;
        float mStartTrim;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeWidth;
        final RectF mTempBounds;

        Ring() {
            super();
            this.mTempBounds = new RectF();
            this.mPaint = new Paint();
            this.mArrowPaint = new Paint();
            this.mCirclePaint = new Paint();
            this.mStartTrim = 0f;
            this.mEndTrim = 0f;
            this.mRotation = 0f;
            this.mStrokeWidth = 5f;
            this.mArrowScale = 1f;
            this.mAlpha = 0xFF;
            this.mPaint.setStrokeCap(Paint$Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint$Style.STROKE);
            this.mArrowPaint.setStyle(Paint$Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
            this.mCirclePaint.setColor(0);
        }

        void draw(Canvas arg9, Rect arg10) {
            RectF v6 = this.mTempBounds;
            float v2 = 2f;
            float v0 = this.mRingCenterRadius + this.mStrokeWidth / v2;
            if(this.mRingCenterRadius <= 0f) {
                v0 = (((float)Math.min(arg10.width(), arg10.height()))) / v2 - Math.max((((float)this.mArrowWidth)) * this.mArrowScale / v2, this.mStrokeWidth / v2);
            }

            v6.set((((float)arg10.centerX())) - v0, (((float)arg10.centerY())) - v0, (((float)arg10.centerX())) + v0, (((float)arg10.centerY())) + v0);
            float v10 = (this.mStartTrim + this.mRotation) * 360f;
            float v7 = (this.mEndTrim + this.mRotation) * 360f - v10;
            this.mPaint.setColor(this.mCurrentColor);
            this.mPaint.setAlpha(this.mAlpha);
            v0 = this.mStrokeWidth / v2;
            v6.inset(v0, v0);
            arg9.drawCircle(v6.centerX(), v6.centerY(), v6.width() / v2, this.mCirclePaint);
            v0 = -v0;
            v6.inset(v0, v0);
            arg9.drawArc(v6, v10, v7, false, this.mPaint);
            this.drawTriangle(arg9, v10, v7, v6);
        }

        void drawTriangle(Canvas arg8, float arg9, float arg10, RectF arg11) {
            if(this.mShowArrow) {
                if(this.mArrow == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(Path$FillType.EVEN_ODD);
                }
                else {
                    this.mArrow.reset();
                }

                float v0 = Math.min(arg11.width(), arg11.height()) / 2f;
                float v2 = (((float)this.mArrowWidth)) * this.mArrowScale / 2f;
                this.mArrow.moveTo(0f, 0f);
                this.mArrow.lineTo((((float)this.mArrowWidth)) * this.mArrowScale, 0f);
                this.mArrow.lineTo((((float)this.mArrowWidth)) * this.mArrowScale / 2f, (((float)this.mArrowHeight)) * this.mArrowScale);
                this.mArrow.offset(v0 + arg11.centerX() - v2, arg11.centerY() + this.mStrokeWidth / 2f);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                this.mArrowPaint.setAlpha(this.mAlpha);
                arg8.save();
                arg8.rotate(arg9 + arg10, arg11.centerX(), arg11.centerY());
                arg8.drawPath(this.mArrow, this.mArrowPaint);
                arg8.restore();
            }
        }

        int getAlpha() {
            return this.mAlpha;
        }

        float getArrowHeight() {
            return ((float)this.mArrowHeight);
        }

        float getArrowScale() {
            return this.mArrowScale;
        }

        float getArrowWidth() {
            return ((float)this.mArrowWidth);
        }

        int getBackgroundColor() {
            return this.mCirclePaint.getColor();
        }

        float getCenterRadius() {
            return this.mRingCenterRadius;
        }

        int[] getColors() {
            return this.mColors;
        }

        float getEndTrim() {
            return this.mEndTrim;
        }

        int getNextColor() {
            return this.mColors[this.getNextColorIndex()];
        }

        int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        float getRotation() {
            return this.mRotation;
        }

        boolean getShowArrow() {
            return this.mShowArrow;
        }

        float getStartTrim() {
            return this.mStartTrim;
        }

        int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        float getStartingRotation() {
            return this.mStartingRotation;
        }

        float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        Paint$Cap getStrokeCap() {
            return this.mPaint.getStrokeCap();
        }

        float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        void goToNextColor() {
            this.setColorIndex(this.getNextColorIndex());
        }

        void resetOriginals() {
            this.mStartingStartTrim = 0f;
            this.mStartingEndTrim = 0f;
            this.mStartingRotation = 0f;
            this.setStartTrim(0f);
            this.setEndTrim(0f);
            this.setRotation(0f);
        }

        void setAlpha(int arg1) {
            this.mAlpha = arg1;
        }

        void setArrowDimensions(float arg1, float arg2) {
            this.mArrowWidth = ((int)arg1);
            this.mArrowHeight = ((int)arg2);
        }

        void setArrowScale(float arg2) {
            if(arg2 != this.mArrowScale) {
                this.mArrowScale = arg2;
            }
        }

        void setBackgroundColor(int arg2) {
            this.mCirclePaint.setColor(arg2);
        }

        void setCenterRadius(float arg1) {
            this.mRingCenterRadius = arg1;
        }

        void setColor(int arg1) {
            this.mCurrentColor = arg1;
        }

        void setColorFilter(ColorFilter arg2) {
            this.mPaint.setColorFilter(arg2);
        }

        void setColorIndex(int arg2) {
            this.mColorIndex = arg2;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        void setColors(@NonNull int[] arg1) {
            this.mColors = arg1;
            this.setColorIndex(0);
        }

        void setEndTrim(float arg1) {
            this.mEndTrim = arg1;
        }

        void setRotation(float arg1) {
            this.mRotation = arg1;
        }

        void setShowArrow(boolean arg2) {
            if(this.mShowArrow != arg2) {
                this.mShowArrow = arg2;
            }
        }

        void setStartTrim(float arg1) {
            this.mStartTrim = arg1;
        }

        void setStrokeCap(Paint$Cap arg2) {
            this.mPaint.setStrokeCap(arg2);
        }

        void setStrokeWidth(float arg2) {
            this.mStrokeWidth = arg2;
            this.mPaint.setStrokeWidth(arg2);
        }

        void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }
    }

    private static final int ANIMATION_DURATION = 0x534;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 7.5f;
    private static final float CENTER_RADIUS_LARGE = 11f;
    private static final int[] COLORS = null;
    private static final float COLOR_CHANGE_OFFSET = 0.75f;
    public static final int DEFAULT = 1;
    private static final float GROUP_FULL_ROTATION = 216f;
    public static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = null;
    private static final Interpolator MATERIAL_INTERPOLATOR = null;
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float MIN_PROGRESS_ARC = 0.01f;
    private static final float RING_ROTATION = 0.21f;
    private static final float SHRINK_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3f;
    private Animator mAnimator;
    private boolean mFinishing;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private float mRotationCount;

    static {
        CircularProgressDrawable.LINEAR_INTERPOLATOR = new LinearInterpolator();
        CircularProgressDrawable.MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
        CircularProgressDrawable.COLORS = new int[]{0xFF000000};
    }

    public CircularProgressDrawable(Context arg2) {
        super();
        this.mResources = Preconditions.checkNotNull(arg2).getResources();
        this.mRing = new Ring();
        this.mRing.setColors(CircularProgressDrawable.COLORS);
        this.setStrokeWidth(2.5f);
        this.setupAnimators();
    }

    static void access$000(CircularProgressDrawable arg0, float arg1, Ring arg2) {
        arg0.updateRingColor(arg1, arg2);
    }

    static void access$100(CircularProgressDrawable arg0, float arg1, Ring arg2, boolean arg3) {
        arg0.applyTransformation(arg1, arg2, arg3);
    }

    static float access$200(CircularProgressDrawable arg0) {
        return arg0.mRotationCount;
    }

    static float access$202(CircularProgressDrawable arg0, float arg1) {
        arg0.mRotationCount = arg1;
        return arg1;
    }

    static boolean access$300(CircularProgressDrawable arg0) {
        return arg0.mFinishing;
    }

    static boolean access$302(CircularProgressDrawable arg0, boolean arg1) {
        arg0.mFinishing = arg1;
        return arg1;
    }

    private void applyFinishTranslation(float arg5, Ring arg6) {
        this.updateRingColor(arg5, arg6);
        float v0 = ((float)(Math.floor(((double)(arg6.getStartingRotation() / 0.8f))) + 1));
        arg6.setStartTrim(arg6.getStartingStartTrim() + (arg6.getStartingEndTrim() - 0.01f - arg6.getStartingStartTrim()) * arg5);
        arg6.setEndTrim(arg6.getStartingEndTrim());
        arg6.setRotation(arg6.getStartingRotation() + (v0 - arg6.getStartingRotation()) * arg5);
    }

    private void applyTransformation(float arg8, Ring arg9, boolean arg10) {
        if(this.mFinishing) {
            this.applyFinishTranslation(arg8, arg9);
        }
        else {
            float v0 = 1f;
            if(arg8 == v0 && !arg10) {
                return;
            }

            float v10 = arg9.getStartingRotation();
            float v1 = 0.5f;
            float v3 = 0.01f;
            float v4 = 0.79f;
            if(Float.compare(arg8, v1) < 0) {
                v0 = arg8 / v1;
                v1 = arg9.getStartingStartTrim();
                float v6 = v1;
                v1 = CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(v0) * v4 + v3 + v1;
                v0 = v6;
            }
            else {
                float v2 = (arg8 - v1) / v1;
                v1 = arg9.getStartingStartTrim() + v4;
                v0 = v1 - ((v0 - CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(v2)) * v4 + v3);
            }

            v10 += 0.21f * arg8;
            arg8 = (arg8 + this.mRotationCount) * 216f;
            arg9.setStartTrim(v0);
            arg9.setEndTrim(v1);
            arg9.setRotation(v10);
            this.setRotation(arg8);
        }
    }

    public void draw(Canvas arg5) {
        Rect v0 = this.getBounds();
        arg5.save();
        arg5.rotate(this.mRotation, v0.exactCenterX(), v0.exactCenterY());
        this.mRing.draw(arg5, v0);
        arg5.restore();
    }

    private int evaluateColorChange(float arg7, int arg8, int arg9) {
        int v0 = arg8 >> 24 & 0xFF;
        int v1 = arg8 >> 16 & 0xFF;
        int v2 = arg8 >> 8 & 0xFF;
        arg8 &= 0xFF;
        return v0 + (((int)((((float)((arg9 >> 24 & 0xFF) - v0))) * arg7))) << 24 | v1 + (((int)((((float)((arg9 >> 16 & 0xFF) - v1))) * arg7))) << 16 | v2 + (((int)((((float)((arg9 >> 8 & 0xFF) - v2))) * arg7))) << 8 | arg8 + (((int)(arg7 * (((float)((arg9 & 0xFF) - arg8))))));
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public boolean getArrowEnabled() {
        return this.mRing.getShowArrow();
    }

    public float getArrowHeight() {
        return this.mRing.getArrowHeight();
    }

    public float getArrowScale() {
        return this.mRing.getArrowScale();
    }

    public float getArrowWidth() {
        return this.mRing.getArrowWidth();
    }

    public int getBackgroundColor() {
        return this.mRing.getBackgroundColor();
    }

    public float getCenterRadius() {
        return this.mRing.getCenterRadius();
    }

    public int[] getColorSchemeColors() {
        return this.mRing.getColors();
    }

    public float getEndTrim() {
        return this.mRing.getEndTrim();
    }

    public int getOpacity() {
        return -3;
    }

    public float getProgressRotation() {
        return this.mRing.getRotation();
    }

    private float getRotation() {
        return this.mRotation;
    }

    public float getStartTrim() {
        return this.mRing.getStartTrim();
    }

    public Paint$Cap getStrokeCap() {
        return this.mRing.getStrokeCap();
    }

    public float getStrokeWidth() {
        return this.mRing.getStrokeWidth();
    }

    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    public void setAlpha(int arg2) {
        this.mRing.setAlpha(arg2);
        this.invalidateSelf();
    }

    public void setArrowDimensions(float arg2, float arg3) {
        this.mRing.setArrowDimensions(arg2, arg3);
        this.invalidateSelf();
    }

    public void setArrowEnabled(boolean arg2) {
        this.mRing.setShowArrow(arg2);
        this.invalidateSelf();
    }

    public void setArrowScale(float arg2) {
        this.mRing.setArrowScale(arg2);
        this.invalidateSelf();
    }

    public void setBackgroundColor(int arg2) {
        this.mRing.setBackgroundColor(arg2);
        this.invalidateSelf();
    }

    public void setCenterRadius(float arg2) {
        this.mRing.setCenterRadius(arg2);
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter arg2) {
        this.mRing.setColorFilter(arg2);
        this.invalidateSelf();
    }

    public void setColorSchemeColors(int[] arg2) {
        this.mRing.setColors(arg2);
        this.mRing.setColorIndex(0);
        this.invalidateSelf();
    }

    public void setProgressRotation(float arg2) {
        this.mRing.setRotation(arg2);
        this.invalidateSelf();
    }

    private void setRotation(float arg1) {
        this.mRotation = arg1;
    }

    private void setSizeParameters(float arg3, float arg4, float arg5, float arg6) {
        Ring v0 = this.mRing;
        float v1 = this.mResources.getDisplayMetrics().density;
        v0.setStrokeWidth(arg4 * v1);
        v0.setCenterRadius(arg3 * v1);
        v0.setColorIndex(0);
        v0.setArrowDimensions(arg5 * v1, arg6 * v1);
    }

    public void setStartEndTrim(float arg2, float arg3) {
        this.mRing.setStartTrim(arg2);
        this.mRing.setEndTrim(arg3);
        this.invalidateSelf();
    }

    public void setStrokeCap(Paint$Cap arg2) {
        this.mRing.setStrokeCap(arg2);
        this.invalidateSelf();
    }

    public void setStrokeWidth(float arg2) {
        this.mRing.setStrokeWidth(arg2);
        this.invalidateSelf();
    }

    public void setStyle(int arg4) {
        if(arg4 == 0) {
            this.setSizeParameters(11f, 3f, 12f, 6f);
        }
        else {
            this.setSizeParameters(7.5f, 2.5f, 10f, 5f);
        }

        this.invalidateSelf();
    }

    private void setupAnimators() {
        Ring v0 = this.mRing;
        ValueAnimator v1 = ValueAnimator.ofFloat(new float[]{0f, 1f});
        v1.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(v0) {
            public void onAnimationUpdate(ValueAnimator arg4) {
                float v4 = arg4.getAnimatedValue().floatValue();
                CircularProgressDrawable.this.updateRingColor(v4, this.val$ring);
                CircularProgressDrawable.this.applyTransformation(v4, this.val$ring, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        v1.setRepeatCount(-1);
        v1.setRepeatMode(1);
        v1.setInterpolator(CircularProgressDrawable.LINEAR_INTERPOLATOR);
        v1.addListener(new Animator$AnimatorListener(v0) {
            public void onAnimationCancel(Animator arg1) {
            }

            public void onAnimationEnd(Animator arg1) {
            }

            public void onAnimationRepeat(Animator arg5) {
                float v2 = 1f;
                CircularProgressDrawable.this.applyTransformation(v2, this.val$ring, true);
                this.val$ring.storeOriginals();
                this.val$ring.goToNextColor();
                if(CircularProgressDrawable.this.mFinishing) {
                    CircularProgressDrawable.this.mFinishing = false;
                    arg5.cancel();
                    arg5.setDuration(0x534);
                    arg5.start();
                    this.val$ring.setShowArrow(false);
                }
                else {
                    CircularProgressDrawable.this.mRotationCount += v2;
                }
            }

            public void onAnimationStart(Animator arg2) {
                CircularProgressDrawable.this.mRotationCount = 0f;
            }
        });
        this.mAnimator = ((Animator)v1);
    }

    public void start() {
        this.mAnimator.cancel();
        this.mRing.storeOriginals();
        if(this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimator.setDuration(666);
            this.mAnimator.start();
        }
        else {
            this.mRing.setColorIndex(0);
            this.mRing.resetOriginals();
            this.mAnimator.setDuration(0x534);
            this.mAnimator.start();
        }
    }

    public void stop() {
        this.mAnimator.cancel();
        this.setRotation(0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.invalidateSelf();
    }

    private void updateRingColor(float arg3, Ring arg4) {
        float v0 = 0.75f;
        if(arg3 > v0) {
            arg4.setColor(this.evaluateColorChange((arg3 - v0) / 0.25f, arg4.getStartingColor(), arg4.getNextColor()));
        }
        else {
            arg4.setColor(arg4.getStartingColor());
        }
    }
}

