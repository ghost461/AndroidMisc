package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.appcompat.R$attr;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class AppCompatRadioButton extends RadioButton implements TintableCompoundButton {
    private final AppCompatCompoundButtonHelper mCompoundButtonHelper;

    public AppCompatRadioButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.radioButtonStyle);
    }

    public AppCompatRadioButton(Context arg2) {
        this(arg2, null);
    }

    public AppCompatRadioButton(Context arg1, AttributeSet arg2, int arg3) {
        super(TintContextWrapper.wrap(arg1), arg2, arg3);
        this.mCompoundButtonHelper = new AppCompatCompoundButtonHelper(((CompoundButton)this));
        this.mCompoundButtonHelper.loadFromAttributes(arg2, arg3);
    }

    public int getCompoundPaddingLeft() {
        int v0 = super.getCompoundPaddingLeft();
        if(this.mCompoundButtonHelper != null) {
            v0 = this.mCompoundButtonHelper.getCompoundPaddingLeft(v0);
        }

        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public ColorStateList getSupportButtonTintList() {
        ColorStateList v0 = this.mCompoundButtonHelper != null ? this.mCompoundButtonHelper.getSupportButtonTintList() : null;
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public PorterDuff$Mode getSupportButtonTintMode() {
        PorterDuff$Mode v0 = this.mCompoundButtonHelper != null ? this.mCompoundButtonHelper.getSupportButtonTintMode() : null;
        return v0;
    }

    public void setButtonDrawable(@DrawableRes int arg2) {
        this.setButtonDrawable(AppCompatResources.getDrawable(this.getContext(), arg2));
    }

    public void setButtonDrawable(Drawable arg1) {
        super.setButtonDrawable(arg1);
        if(this.mCompoundButtonHelper != null) {
            this.mCompoundButtonHelper.onSetButtonDrawable();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportButtonTintList(@Nullable ColorStateList arg2) {
        if(this.mCompoundButtonHelper != null) {
            this.mCompoundButtonHelper.setSupportButtonTintList(arg2);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportButtonTintMode(@Nullable PorterDuff$Mode arg2) {
        if(this.mCompoundButtonHelper != null) {
            this.mCompoundButtonHelper.setSupportButtonTintMode(arg2);
        }
    }
}

