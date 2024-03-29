package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;

@RequiresApi(value=9) public abstract class RoundedBitmapDrawable extends Drawable {
    private static final int DEFAULT_PAINT_FLAGS = 3;
    private boolean mApplyGravity;
    final Bitmap mBitmap;
    private int mBitmapHeight;
    private final BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect;
    private final RectF mDstRectF;
    private int mGravity;
    private boolean mIsCircular;
    private final Paint mPaint;
    private final Matrix mShaderMatrix;
    private int mTargetDensity;

    RoundedBitmapDrawable(Resources arg3, Bitmap arg4) {
        super();
        this.mTargetDensity = 0xA0;
        this.mGravity = 0x77;
        this.mPaint = new Paint(3);
        this.mShaderMatrix = new Matrix();
        this.mDstRect = new Rect();
        this.mDstRectF = new RectF();
        this.mApplyGravity = true;
        if(arg3 != null) {
            this.mTargetDensity = arg3.getDisplayMetrics().densityDpi;
        }

        this.mBitmap = arg4;
        if(this.mBitmap != null) {
            this.computeBitmapSize();
            this.mBitmapShader = new BitmapShader(this.mBitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
        }
        else {
            this.mBitmapHeight = -1;
            this.mBitmapWidth = -1;
            this.mBitmapShader = null;
        }
    }

    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    public void draw(Canvas arg5) {
        Bitmap v0 = this.mBitmap;
        if(v0 == null) {
            return;
        }

        this.updateDstRect();
        if(this.mPaint.getShader() == null) {
            arg5.drawBitmap(v0, null, this.mDstRect, this.mPaint);
        }
        else {
            arg5.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, this.mPaint);
        }
    }

    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    public final Bitmap getBitmap() {
        return this.mBitmap;
    }

    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public int getOpacity() {
        int v1 = -3;
        if(this.mGravity == 0x77) {
            if(this.mIsCircular) {
            }
            else {
                Bitmap v0 = this.mBitmap;
                if(v0 != null && !v0.hasAlpha() && this.mPaint.getAlpha() >= 0xFF) {
                    if(RoundedBitmapDrawable.isGreaterThanZero(this.mCornerRadius)) {
                    }
                    else {
                        v1 = -1;
                    }
                }

                return v1;
            }
        }

        return v1;
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    void gravityCompatApply(int arg1, int arg2, int arg3, Rect arg4, Rect arg5) {
        throw new UnsupportedOperationException();
    }

    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }

    public boolean hasMipMap() {
        throw new UnsupportedOperationException();
    }

    public boolean isCircular() {
        return this.mIsCircular;
    }

    private static boolean isGreaterThanZero(float arg1) {
        boolean v1 = arg1 > 0.05f ? true : false;
        return v1;
    }

    protected void onBoundsChange(Rect arg1) {
        super.onBoundsChange(arg1);
        if(this.mIsCircular) {
            this.updateCircularCornerRadius();
        }

        this.mApplyGravity = true;
    }

    public void setAlpha(int arg2) {
        if(arg2 != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(arg2);
            this.invalidateSelf();
        }
    }

    public void setAntiAlias(boolean arg2) {
        this.mPaint.setAntiAlias(arg2);
        this.invalidateSelf();
    }

    public void setCircular(boolean arg2) {
        this.mIsCircular = arg2;
        this.mApplyGravity = true;
        if(arg2) {
            this.updateCircularCornerRadius();
            this.mPaint.setShader(this.mBitmapShader);
            this.invalidateSelf();
        }
        else {
            this.setCornerRadius(0f);
        }
    }

    public void setColorFilter(ColorFilter arg2) {
        this.mPaint.setColorFilter(arg2);
        this.invalidateSelf();
    }

    public void setCornerRadius(float arg3) {
        if(this.mCornerRadius == arg3) {
            return;
        }

        this.mIsCircular = false;
        if(RoundedBitmapDrawable.isGreaterThanZero(arg3)) {
            this.mPaint.setShader(this.mBitmapShader);
        }
        else {
            this.mPaint.setShader(null);
        }

        this.mCornerRadius = arg3;
        this.invalidateSelf();
    }

    public void setDither(boolean arg2) {
        this.mPaint.setDither(arg2);
        this.invalidateSelf();
    }

    public void setFilterBitmap(boolean arg2) {
        this.mPaint.setFilterBitmap(arg2);
        this.invalidateSelf();
    }

    public void setGravity(int arg2) {
        if(this.mGravity != arg2) {
            this.mGravity = arg2;
            this.mApplyGravity = true;
            this.invalidateSelf();
        }
    }

    public void setMipMap(boolean arg1) {
        throw new UnsupportedOperationException();
    }

    public void setTargetDensity(int arg2) {
        if(this.mTargetDensity != arg2) {
            if(arg2 == 0) {
                arg2 = 0xA0;
            }

            this.mTargetDensity = arg2;
            if(this.mBitmap != null) {
                this.computeBitmapSize();
            }

            this.invalidateSelf();
        }
    }

    public void setTargetDensity(Canvas arg1) {
        this.setTargetDensity(arg1.getDensity());
    }

    public void setTargetDensity(DisplayMetrics arg1) {
        this.setTargetDensity(arg1.densityDpi);
    }

    private void updateCircularCornerRadius() {
        this.mCornerRadius = ((float)(Math.min(this.mBitmapHeight, this.mBitmapWidth) / 2));
    }

    void updateDstRect() {
        if(this.mApplyGravity) {
            if(this.mIsCircular) {
                int v6 = Math.min(this.mBitmapWidth, this.mBitmapHeight);
                this.gravityCompatApply(this.mGravity, v6, v6, this.getBounds(), this.mDstRect);
                int v0 = Math.min(this.mDstRect.width(), this.mDstRect.height());
                this.mDstRect.inset(Math.max(0, (this.mDstRect.width() - v0) / 2), Math.max(0, (this.mDstRect.height() - v0) / 2));
                this.mCornerRadius = (((float)v0)) * 0.5f;
            }
            else {
                this.gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, this.getBounds(), this.mDstRect);
            }

            this.mDstRectF.set(this.mDstRect);
            if(this.mBitmapShader != null) {
                this.mShaderMatrix.setTranslate(this.mDstRectF.left, this.mDstRectF.top);
                this.mShaderMatrix.preScale(this.mDstRectF.width() / (((float)this.mBitmap.getWidth())), this.mDstRectF.height() / (((float)this.mBitmap.getHeight())));
                this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
                this.mPaint.setShader(this.mBitmapShader);
            }

            this.mApplyGravity = false;
        }
    }
}

