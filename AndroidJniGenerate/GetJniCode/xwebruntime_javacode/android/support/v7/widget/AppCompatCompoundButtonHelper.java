package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;

class AppCompatCompoundButtonHelper {
    interface DirectSetButtonDrawableInterface {
        void setButtonDrawable(Drawable arg1);
    }

    private ColorStateList mButtonTintList;
    private PorterDuff$Mode mButtonTintMode;
    private boolean mHasButtonTint;
    private boolean mHasButtonTintMode;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    AppCompatCompoundButtonHelper(CompoundButton arg2) {
        super();
        this.mButtonTintList = null;
        this.mButtonTintMode = null;
        this.mHasButtonTint = false;
        this.mHasButtonTintMode = false;
        this.mView = arg2;
    }

    void applyButtonTint() {
        Drawable v0 = CompoundButtonCompat.getButtonDrawable(this.mView);
        if(v0 != null && ((this.mHasButtonTint) || (this.mHasButtonTintMode))) {
            v0 = DrawableCompat.wrap(v0).mutate();
            if(this.mHasButtonTint) {
                DrawableCompat.setTintList(v0, this.mButtonTintList);
            }

            if(this.mHasButtonTintMode) {
                DrawableCompat.setTintMode(v0, this.mButtonTintMode);
            }

            if(v0.isStateful()) {
                v0.setState(this.mView.getDrawableState());
            }

            this.mView.setButtonDrawable(v0);
        }
    }

    int getCompoundPaddingLeft(int arg3) {
        if(Build$VERSION.SDK_INT < 17) {
            Drawable v0 = CompoundButtonCompat.getButtonDrawable(this.mView);
            if(v0 != null) {
                arg3 += v0.getIntrinsicWidth();
            }
        }

        return arg3;
    }

    ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    PorterDuff$Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    void loadFromAttributes(AttributeSet arg4, int arg5) {
        TypedArray v4 = this.mView.getContext().obtainStyledAttributes(arg4, styleable.CompoundButton, arg5, 0);
        try {
            if(v4.hasValue(styleable.CompoundButton_android_button)) {
                arg5 = v4.getResourceId(styleable.CompoundButton_android_button, 0);
                if(arg5 != 0) {
                    this.mView.setButtonDrawable(AppCompatResources.getDrawable(this.mView.getContext(), arg5));
                }
            }

            if(v4.hasValue(styleable.CompoundButton_buttonTint)) {
                CompoundButtonCompat.setButtonTintList(this.mView, v4.getColorStateList(styleable.CompoundButton_buttonTint));
            }

            if(v4.hasValue(styleable.CompoundButton_buttonTintMode)) {
                CompoundButtonCompat.setButtonTintMode(this.mView, DrawableUtils.parseTintMode(v4.getInt(styleable.CompoundButton_buttonTintMode, -1), null));
            }
        }
        catch(Throwable v5) {
            v4.recycle();
            throw v5;
        }

        v4.recycle();
    }

    void onSetButtonDrawable() {
        if(this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }

        this.mSkipNextApply = true;
        this.applyButtonTint();
    }

    void setSupportButtonTintList(ColorStateList arg1) {
        this.mButtonTintList = arg1;
        this.mHasButtonTint = true;
        this.applyButtonTint();
    }

    void setSupportButtonTintMode(@Nullable PorterDuff$Mode arg1) {
        this.mButtonTintMode = arg1;
        this.mHasButtonTintMode = true;
        this.applyButtonTint();
    }
}

