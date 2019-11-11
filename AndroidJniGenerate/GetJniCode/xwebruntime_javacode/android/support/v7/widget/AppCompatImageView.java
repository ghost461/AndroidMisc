package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.TintableImageSourceView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class AppCompatImageView extends ImageView implements TintableBackgroundView, TintableImageSourceView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatImageHelper mImageHelper;

    public AppCompatImageView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public AppCompatImageView(Context arg2) {
        this(arg2, null);
    }

    public AppCompatImageView(Context arg1, AttributeSet arg2, int arg3) {
        super(TintContextWrapper.wrap(arg1), arg2, arg3);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(((View)this));
        this.mBackgroundTintHelper.loadFromAttributes(arg2, arg3);
        this.mImageHelper = new AppCompatImageHelper(((ImageView)this));
        this.mImageHelper.loadFromAttributes(arg2, arg3);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }

        if(this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public ColorStateList getSupportImageTintList() {
        ColorStateList v0 = this.mImageHelper != null ? this.mImageHelper.getSupportImageTintList() : null;
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public PorterDuff$Mode getSupportImageTintMode() {
        PorterDuff$Mode v0 = this.mImageHelper != null ? this.mImageHelper.getSupportImageTintMode() : null;
        return v0;
    }

    public boolean hasOverlappingRendering() {
        boolean v0 = !this.mImageHelper.hasOverlappingRendering() || !super.hasOverlappingRendering() ? false : true;
        return v0;
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(arg2);
        }
    }

    public void setBackgroundResource(@DrawableRes int arg2) {
        super.setBackgroundResource(arg2);
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(arg2);
        }
    }

    public void setImageBitmap(Bitmap arg1) {
        super.setImageBitmap(arg1);
        if(this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    public void setImageDrawable(@Nullable Drawable arg1) {
        super.setImageDrawable(arg1);
        if(this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    public void setImageIcon(@Nullable Icon arg1) {
        super.setImageIcon(arg1);
        if(this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    public void setImageResource(@DrawableRes int arg2) {
        if(this.mImageHelper != null) {
            this.mImageHelper.setImageResource(arg2);
        }
    }

    public void setImageURI(@Nullable Uri arg1) {
        super.setImageURI(arg1);
        if(this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportBackgroundTintList(@Nullable ColorStateList arg2) {
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportBackgroundTintMode(@Nullable PorterDuff$Mode arg2) {
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportImageTintList(@Nullable ColorStateList arg2) {
        if(this.mImageHelper != null) {
            this.mImageHelper.setSupportImageTintList(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportImageTintMode(@Nullable PorterDuff$Mode arg2) {
        if(this.mImageHelper != null) {
            this.mImageHelper.setSupportImageTintMode(arg2);
        }
    }
}

