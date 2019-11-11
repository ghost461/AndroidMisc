package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.SeekBar;

class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    private Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private PorterDuff$Mode mTickMarkTintMode;
    private final SeekBar mView;

    AppCompatSeekBarHelper(SeekBar arg2) {
        super(((ProgressBar)arg2));
        this.mTickMarkTintList = null;
        this.mTickMarkTintMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkTintMode = false;
        this.mView = arg2;
    }

    private void applyTickMarkTint() {
        if(this.mTickMark != null && ((this.mHasTickMarkTint) || (this.mHasTickMarkTintMode))) {
            this.mTickMark = DrawableCompat.wrap(this.mTickMark.mutate());
            if(this.mHasTickMarkTint) {
                DrawableCompat.setTintList(this.mTickMark, this.mTickMarkTintList);
            }

            if(this.mHasTickMarkTintMode) {
                DrawableCompat.setTintMode(this.mTickMark, this.mTickMarkTintMode);
            }

            if(!this.mTickMark.isStateful()) {
                return;
            }

            this.mTickMark.setState(this.mView.getDrawableState());
        }
    }

    void drawTickMarks(Canvas arg7) {
        if(this.mTickMark != null) {
            int v0 = this.mView.getMax();
            int v1 = 1;
            if(v0 > 1) {
                int v2 = this.mTickMark.getIntrinsicWidth();
                int v3 = this.mTickMark.getIntrinsicHeight();
                if(v2 >= 0) {
                    v2 /= 2;
                }
                else {
                    v2 = 1;
                }

                if(v3 >= 0) {
                    v1 = v3 / 2;
                }

                this.mTickMark.setBounds(-v2, -v1, v2, v1);
                float v1_1 = (((float)(this.mView.getWidth() - this.mView.getPaddingLeft() - this.mView.getPaddingRight()))) / (((float)v0));
                v2 = arg7.save();
                arg7.translate(((float)this.mView.getPaddingLeft()), ((float)(this.mView.getHeight() / 2)));
                for(v3 = 0; v3 <= v0; ++v3) {
                    this.mTickMark.draw(arg7);
                    arg7.translate(v1_1, 0f);
                }

                arg7.restoreToCount(v2);
            }
        }
    }

    void drawableStateChanged() {
        Drawable v0 = this.mTickMark;
        if(v0 != null && (v0.isStateful()) && (v0.setState(this.mView.getDrawableState()))) {
            this.mView.invalidateDrawable(v0);
        }
    }

    @Nullable Drawable getTickMark() {
        return this.mTickMark;
    }

    @Nullable ColorStateList getTickMarkTintList() {
        return this.mTickMarkTintList;
    }

    @Nullable PorterDuff$Mode getTickMarkTintMode() {
        return this.mTickMarkTintMode;
    }

    @RequiresApi(value=11) void jumpDrawablesToCurrentState() {
        if(this.mTickMark != null) {
            this.mTickMark.jumpToCurrentState();
        }
    }

    void loadFromAttributes(AttributeSet arg4, int arg5) {
        super.loadFromAttributes(arg4, arg5);
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), arg4, styleable.AppCompatSeekBar, arg5, 0);
        Drawable v5 = v4.getDrawableIfKnown(styleable.AppCompatSeekBar_android_thumb);
        if(v5 != null) {
            this.mView.setThumb(v5);
        }

        this.setTickMark(v4.getDrawable(styleable.AppCompatSeekBar_tickMark));
        if(v4.hasValue(styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.mTickMarkTintMode = DrawableUtils.parseTintMode(v4.getInt(styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.mTickMarkTintMode);
            this.mHasTickMarkTintMode = true;
        }

        if(v4.hasValue(styleable.AppCompatSeekBar_tickMarkTint)) {
            this.mTickMarkTintList = v4.getColorStateList(styleable.AppCompatSeekBar_tickMarkTint);
            this.mHasTickMarkTint = true;
        }

        v4.recycle();
        this.applyTickMarkTint();
    }

    void setTickMark(@Nullable Drawable arg3) {
        if(this.mTickMark != null) {
            this.mTickMark.setCallback(null);
        }

        this.mTickMark = arg3;
        if(arg3 != null) {
            arg3.setCallback(this.mView);
            DrawableCompat.setLayoutDirection(arg3, ViewCompat.getLayoutDirection(this.mView));
            if(arg3.isStateful()) {
                arg3.setState(this.mView.getDrawableState());
            }

            this.applyTickMarkTint();
        }

        this.mView.invalidate();
    }

    void setTickMarkTintList(@Nullable ColorStateList arg1) {
        this.mTickMarkTintList = arg1;
        this.mHasTickMarkTint = true;
        this.applyTickMarkTint();
    }

    void setTickMarkTintMode(@Nullable PorterDuff$Mode arg1) {
        this.mTickMarkTintMode = arg1;
        this.mHasTickMarkTintMode = true;
        this.applyTickMarkTint();
    }
}

