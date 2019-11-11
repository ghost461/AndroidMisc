package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AppCompatEditText extends EditText implements TintableBackgroundView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatEditText(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.editTextStyle);
    }

    public AppCompatEditText(Context arg2) {
        this(arg2, null);
    }

    public AppCompatEditText(Context arg1, AttributeSet arg2, int arg3) {
        super(TintContextWrapper.wrap(arg1), arg2, arg3);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(((View)this));
        this.mBackgroundTintHelper.loadFromAttributes(arg2, arg3);
        this.mTextHelper = AppCompatTextHelper.create(((TextView)this));
        this.mTextHelper.loadFromAttributes(arg2, arg3);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }

        if(this.mTextHelper != null) {
            this.mTextHelper.applyCompoundDrawablesTints();
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

    public void setTextAppearance(Context arg2, int arg3) {
        super.setTextAppearance(arg2, arg3);
        if(this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(arg2, arg3);
        }
    }
}

