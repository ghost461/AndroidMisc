package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class AppCompatImageHelper {
    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView arg1) {
        super();
        this.mView = arg1;
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable arg4) {
        if(this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }

        TintInfo v0 = this.mTmpInfo;
        v0.clear();
        ColorStateList v1 = ImageViewCompat.getImageTintList(this.mView);
        if(v1 != null) {
            v0.mHasTintList = true;
            v0.mTintList = v1;
        }

        PorterDuff$Mode v1_1 = ImageViewCompat.getImageTintMode(this.mView);
        if(v1_1 != null) {
            v0.mHasTintMode = true;
            v0.mTintMode = v1_1;
        }

        if(!v0.mHasTintList) {
            if(v0.mHasTintMode) {
            }
            else {
                return 0;
            }
        }

        AppCompatDrawableManager.tintDrawable(arg4, v0, this.mView.getDrawableState());
        return 1;
    }

    void applySupportImageTint() {
        Drawable v0 = this.mView.getDrawable();
        if(v0 != null) {
            DrawableUtils.fixDrawable(v0);
        }

        if(v0 != null) {
            if((this.shouldApplyFrameworkTintUsingColorFilter()) && (this.applyFrameworkTintUsingColorFilter(v0))) {
                return;
            }

            if(this.mImageTint != null) {
                AppCompatDrawableManager.tintDrawable(v0, this.mImageTint, this.mView.getDrawableState());
                return;
            }

            if(this.mInternalImageTint == null) {
                return;
            }

            AppCompatDrawableManager.tintDrawable(v0, this.mInternalImageTint, this.mView.getDrawableState());
        }
    }

    ColorStateList getSupportImageTintList() {
        ColorStateList v0 = this.mImageTint != null ? this.mImageTint.mTintList : null;
        return v0;
    }

    PorterDuff$Mode getSupportImageTintMode() {
        PorterDuff$Mode v0 = this.mImageTint != null ? this.mImageTint.mTintMode : null;
        return v0;
    }

    boolean hasOverlappingRendering() {
        Drawable v0 = this.mView.getBackground();
        if(Build$VERSION.SDK_INT >= 21 && ((v0 instanceof RippleDrawable))) {
            return 0;
        }

        return 1;
    }

    public void loadFromAttributes(AttributeSet arg4, int arg5) {
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), arg4, styleable.AppCompatImageView, arg5, 0);
        try {
            Drawable v5_1 = this.mView.getDrawable();
            int v0 = -1;
            if(v5_1 == null) {
                int v1 = v4.getResourceId(styleable.AppCompatImageView_srcCompat, v0);
                if(v1 != v0) {
                    v5_1 = AppCompatResources.getDrawable(this.mView.getContext(), v1);
                    if(v5_1 != null) {
                        this.mView.setImageDrawable(v5_1);
                    }
                }
            }

            if(v5_1 != null) {
                DrawableUtils.fixDrawable(v5_1);
            }

            if(v4.hasValue(styleable.AppCompatImageView_tint)) {
                ImageViewCompat.setImageTintList(this.mView, v4.getColorStateList(styleable.AppCompatImageView_tint));
            }

            if(v4.hasValue(styleable.AppCompatImageView_tintMode)) {
                ImageViewCompat.setImageTintMode(this.mView, DrawableUtils.parseTintMode(v4.getInt(styleable.AppCompatImageView_tintMode, v0), null));
            }
        }
        catch(Throwable v5) {
            v4.recycle();
            throw v5;
        }

        v4.recycle();
    }

    public void setImageResource(int arg2) {
        if(arg2 != 0) {
            Drawable v2 = AppCompatResources.getDrawable(this.mView.getContext(), arg2);
            if(v2 != null) {
                DrawableUtils.fixDrawable(v2);
            }

            this.mView.setImageDrawable(v2);
        }
        else {
            this.mView.setImageDrawable(null);
        }

        this.applySupportImageTint();
    }

    void setInternalImageTint(ColorStateList arg2) {
        if(arg2 != null) {
            if(this.mInternalImageTint == null) {
                this.mInternalImageTint = new TintInfo();
            }

            this.mInternalImageTint.mTintList = arg2;
            this.mInternalImageTint.mHasTintList = true;
        }
        else {
            this.mInternalImageTint = null;
        }

        this.applySupportImageTint();
    }

    void setSupportImageTintList(ColorStateList arg2) {
        if(this.mImageTint == null) {
            this.mImageTint = new TintInfo();
        }

        this.mImageTint.mTintList = arg2;
        this.mImageTint.mHasTintList = true;
        this.applySupportImageTint();
    }

    void setSupportImageTintMode(PorterDuff$Mode arg2) {
        if(this.mImageTint == null) {
            this.mImageTint = new TintInfo();
        }

        this.mImageTint.mTintMode = arg2;
        this.mImageTint.mHasTintMode = true;
        this.applySupportImageTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        int v0 = Build$VERSION.SDK_INT;
        boolean v1 = false;
        int v3 = 21;
        if(v0 > v3) {
            if(this.mInternalImageTint != null) {
                v1 = true;
            }

            return v1;
        }

        if(v0 == v3) {
            return 1;
        }

        return 0;
    }
}

