package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

final class SwipeProgressBar {
    private static final int ANIMATION_DURATION_MS = 2000;
    private static final int COLOR1 = 0xB3000000;
    private static final int COLOR2 = 0x80000000;
    private static final int COLOR3 = 0x4D000000;
    private static final int COLOR4 = 0x1A000000;
    private static final int FINISH_ANIMATION_DURATION_MS = 1000;
    private static final Interpolator INTERPOLATOR;
    private Rect mBounds;
    private final RectF mClipRect;
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private int mColor4;
    private long mFinishTime;
    private final Paint mPaint;
    private View mParent;
    private boolean mRunning;
    private long mStartTime;
    private float mTriggerPercentage;

    static {
        SwipeProgressBar.INTERPOLATOR = new FastOutSlowInInterpolator();
    }

    SwipeProgressBar(View arg2) {
        super();
        this.mPaint = new Paint();
        this.mClipRect = new RectF();
        this.mBounds = new Rect();
        this.mParent = arg2;
        this.mColor1 = 0xB3000000;
        this.mColor2 = 0x80000000;
        this.mColor3 = 0x4D000000;
        this.mColor4 = 0x1A000000;
    }

    void draw(Canvas arg24) {
        int v14_1;
        long v0_2;
        SwipeProgressBar v6 = this;
        Canvas v7 = arg24;
        int v0 = v6.mBounds.width();
        int v1 = v6.mBounds.height();
        int v8 = v0 / 2;
        int v9 = v1 / 2;
        int v10 = arg24.save();
        v7.clipRect(v6.mBounds);
        long v2 = 0;
        if((v6.mRunning) || v6.mFinishTime > v2) {
            long v4 = AnimationUtils.currentAnimationTimeMillis();
            long v14 = (v4 - v6.mStartTime) % 2000;
            long v18 = (v4 - v6.mStartTime) / 2000;
            float v12 = (((float)v14)) / 20f;
            float v13 = 100f;
            if(!v6.mRunning) {
                v14 = 1000;
                if(v4 - v6.mFinishTime >= v14) {
                    v6.mFinishTime = 0;
                    return;
                }
                else {
                    float v2_1 = ((float)v8);
                    float v0_1 = SwipeProgressBar.INTERPOLATOR.getInterpolation((((float)((v4 - v6.mFinishTime) % v14))) / 10f / v13) * v2_1;
                    v6.mClipRect.set(v2_1 - v0_1, 0f, v2_1 + v0_1, ((float)v1));
                    v7.saveLayerAlpha(v6.mClipRect, 0, 0);
                    v0_2 = 0;
                    v14_1 = 1;
                }
            }
            else {
                v0_2 = 0;
                v14_1 = 0;
            }

            float v15 = 75f;
            float v16 = 50f;
            float v17 = 25f;
            if(Long.compare(v18, v0_2) == 0) {
                v7.drawColor(v6.mColor1);
            }
            else {
                if(v12 >= 0f && v12 < v17) {
                    v7.drawColor(v6.mColor4);
                    goto label_98;
                }

                if(v12 >= v17 && v12 < v16) {
                    v7.drawColor(v6.mColor1);
                    goto label_98;
                }

                if(v12 >= v16 && v12 < v15) {
                    v7.drawColor(v6.mColor2);
                    goto label_98;
                }

                v7.drawColor(v6.mColor3);
            }

        label_98:
            float v18_1 = 2f;
            if(Float.compare(v12, 0f) >= 0 && v12 <= v17) {
                v6.drawCircle(v7, ((float)v8), ((float)v9), v6.mColor1, (v12 + v17) * v18_1 / v13);
            }

            if(v12 >= 0f && v12 <= v16) {
                v6.drawCircle(v7, ((float)v8), ((float)v9), v6.mColor2, v12 * v18_1 / v13);
            }

            if(v12 >= v17 && v12 <= v15) {
                v6.drawCircle(v7, ((float)v8), ((float)v9), v6.mColor3, (v12 - v17) * v18_1 / v13);
            }

            if(v12 >= v16 && v12 <= v13) {
                v6.drawCircle(v7, ((float)v8), ((float)v9), v6.mColor4, (v12 - v16) * v18_1 / v13);
            }

            if(v12 >= v15 && v12 <= v13) {
                v6.drawCircle(v7, ((float)v8), ((float)v9), v6.mColor1, (v12 - v15) * v18_1 / v13);
            }

            if(v6.mTriggerPercentage > 0f && v14_1 != 0) {
                v7.restoreToCount(v10);
                v0 = arg24.save();
                v7.clipRect(v6.mBounds);
                v6.drawTrigger(v7, v8, v9);
                v10 = v0;
            }

            ViewCompat.postInvalidateOnAnimation(v6.mParent, v6.mBounds.left, v6.mBounds.top, v6.mBounds.right, v6.mBounds.bottom);
        }
        else if(v6.mTriggerPercentage > 0f && (((double)v6.mTriggerPercentage)) <= 1) {
            v6.drawTrigger(v7, v8, v9);
        }

        v7.restoreToCount(v10);
    }

    private void drawCircle(Canvas arg2, float arg3, float arg4, int arg5, float arg6) {
        this.mPaint.setColor(arg5);
        arg2.save();
        arg2.translate(arg3, arg4);
        arg4 = SwipeProgressBar.INTERPOLATOR.getInterpolation(arg6);
        arg2.scale(arg4, arg4);
        arg2.drawCircle(0f, 0f, arg3, this.mPaint);
        arg2.restore();
    }

    private void drawTrigger(Canvas arg3, int arg4, int arg5) {
        this.mPaint.setColor(this.mColor1);
        float v4 = ((float)arg4);
        arg3.drawCircle(v4, ((float)arg5), this.mTriggerPercentage * v4, this.mPaint);
    }

    boolean isRunning() {
        boolean v0 = (this.mRunning) || this.mFinishTime > 0 ? true : false;
        return v0;
    }

    void setBounds(int arg2, int arg3, int arg4, int arg5) {
        this.mBounds.left = arg2;
        this.mBounds.top = arg3;
        this.mBounds.right = arg4;
        this.mBounds.bottom = arg5;
    }

    void setColorScheme(int arg1, int arg2, int arg3, int arg4) {
        this.mColor1 = arg1;
        this.mColor2 = arg2;
        this.mColor3 = arg3;
        this.mColor4 = arg4;
    }

    void setTriggerPercentage(float arg5) {
        this.mTriggerPercentage = arg5;
        this.mStartTime = 0;
        ViewCompat.postInvalidateOnAnimation(this.mParent, this.mBounds.left, this.mBounds.top, this.mBounds.right, this.mBounds.bottom);
    }

    void start() {
        if(!this.mRunning) {
            this.mTriggerPercentage = 0f;
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mRunning = true;
            this.mParent.postInvalidate();
        }
    }

    void stop() {
        if(this.mRunning) {
            this.mTriggerPercentage = 0f;
            this.mFinishTime = AnimationUtils.currentAnimationTimeMillis();
            this.mRunning = false;
            this.mParent.postInvalidate();
        }
    }
}

