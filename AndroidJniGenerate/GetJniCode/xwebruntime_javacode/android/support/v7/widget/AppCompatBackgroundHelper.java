package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper {
    private int mBackgroundResId;
    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View arg2) {
        super();
        this.mBackgroundResId = -1;
        this.mView = arg2;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable arg4) {
        if(this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }

        TintInfo v0 = this.mTmpInfo;
        v0.clear();
        ColorStateList v1 = ViewCompat.getBackgroundTintList(this.mView);
        if(v1 != null) {
            v0.mHasTintList = true;
            v0.mTintList = v1;
        }

        PorterDuff$Mode v1_1 = ViewCompat.getBackgroundTintMode(this.mView);
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

    void applySupportBackgroundTint() {
        Drawable v0 = this.mView.getBackground();
        if(v0 != null) {
            if((this.shouldApplyFrameworkTintUsingColorFilter()) && (this.applyFrameworkTintUsingColorFilter(v0))) {
                return;
            }

            if(this.mBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(v0, this.mBackgroundTint, this.mView.getDrawableState());
                return;
            }

            if(this.mInternalBackgroundTint == null) {
                return;
            }

            AppCompatDrawableManager.tintDrawable(v0, this.mInternalBackgroundTint, this.mView.getDrawableState());
        }
    }

    ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.mBackgroundTint != null ? this.mBackgroundTint.mTintList : null;
        return v0;
    }

    PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.mBackgroundTint != null ? this.mBackgroundTint.mTintMode : null;
        return v0;
    }

    void loadFromAttributes(AttributeSet arg4, int arg5) {
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), arg4, styleable.ViewBackgroundHelper, arg5, 0);
        try {
            int v0 = -1;
            if(v4.hasValue(styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = v4.getResourceId(styleable.ViewBackgroundHelper_android_background, v0);
                ColorStateList v5_1 = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if(v5_1 != null) {
                    this.setInternalBackgroundTint(v5_1);
                }
            }

            if(v4.hasValue(styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, v4.getColorStateList(styleable.ViewBackgroundHelper_backgroundTint));
            }

            if(v4.hasValue(styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(v4.getInt(styleable.ViewBackgroundHelper_backgroundTintMode, v0), null));
            }
        }
        catch(Throwable v5) {
            v4.recycle();
            throw v5;
        }

        v4.recycle();
    }

    void onSetBackgroundDrawable(Drawable arg1) {
        this.mBackgroundResId = -1;
        this.setInternalBackgroundTint(null);
        this.applySupportBackgroundTint();
    }

    void onSetBackgroundResource(int arg3) {
        this.mBackgroundResId = arg3;
        ColorStateList v3 = this.mDrawableManager != null ? this.mDrawableManager.getTintList(this.mView.getContext(), arg3) : null;
        this.setInternalBackgroundTint(v3);
        this.applySupportBackgroundTint();
    }

    void setInternalBackgroundTint(ColorStateList arg2) {
        if(arg2 != null) {
            if(this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }

            this.mInternalBackgroundTint.mTintList = arg2;
            this.mInternalBackgroundTint.mHasTintList = true;
        }
        else {
            this.mInternalBackgroundTint = null;
        }

        this.applySupportBackgroundTint();
    }

    void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }

        this.mBackgroundTint.mTintList = arg2;
        this.mBackgroundTint.mHasTintList = true;
        this.applySupportBackgroundTint();
    }

    void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }

        this.mBackgroundTint.mTintMode = arg2;
        this.mBackgroundTint.mHasTintMode = true;
        this.applySupportBackgroundTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        int v0 = Build$VERSION.SDK_INT;
        boolean v1 = false;
        int v3 = 21;
        if(v0 > v3) {
            if(this.mInternalBackgroundTint != null) {
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

