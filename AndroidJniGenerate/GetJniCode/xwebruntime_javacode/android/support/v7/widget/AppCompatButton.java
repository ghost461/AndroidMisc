package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;

public class AppCompatButton extends Button implements TintableBackgroundView, AutoSizeableTextView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.buttonStyle);
    }

    public AppCompatButton(Context arg2) {
        this(arg2, null);
    }

    public AppCompatButton(Context arg1, AttributeSet arg2, int arg3) {
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

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getAutoSizeMaxTextSize() {
        if(Build$VERSION.SDK_INT >= 26) {
            return super.getAutoSizeMaxTextSize();
        }

        if(this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeMaxTextSize();
        }

        return -1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getAutoSizeMinTextSize() {
        if(Build$VERSION.SDK_INT >= 26) {
            return super.getAutoSizeMinTextSize();
        }

        if(this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeMinTextSize();
        }

        return -1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getAutoSizeStepGranularity() {
        if(Build$VERSION.SDK_INT >= 26) {
            return super.getAutoSizeStepGranularity();
        }

        if(this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeStepGranularity();
        }

        return -1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int[] getAutoSizeTextAvailableSizes() {
        if(Build$VERSION.SDK_INT >= 26) {
            return super.getAutoSizeTextAvailableSizes();
        }

        if(this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeTextAvailableSizes();
        }

        return new int[0];
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getAutoSizeTextType() {
        int v1 = 0;
        if(Build$VERSION.SDK_INT >= 26) {
            if(super.getAutoSizeTextType() == 1) {
                v1 = 1;
            }

            return v1;
        }

        if(this.mTextHelper != null) {
            return this.mTextHelper.getAutoSizeTextType();
        }

        return 0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
        return v0;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
        return v0;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
        super.onInitializeAccessibilityEvent(arg2);
        arg2.setClassName(Button.class.getName());
    }

    @RequiresApi(value=14) public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg2) {
        super.onInitializeAccessibilityNodeInfo(arg2);
        arg2.setClassName(Button.class.getName());
    }

    protected void onLayout(boolean arg8, int arg9, int arg10, int arg11, int arg12) {
        super.onLayout(arg8, arg9, arg10, arg11, arg12);
        if(this.mTextHelper != null) {
            this.mTextHelper.onLayout(arg8, arg9, arg10, arg11, arg12);
        }
    }

    protected void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        super.onTextChanged(arg1, arg2, arg3, arg4);
        if(this.mTextHelper != null && Build$VERSION.SDK_INT < 26 && (this.mTextHelper.isAutoSizeEnabled())) {
            this.mTextHelper.autoSizeText();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setAutoSizeTextTypeUniformWithConfiguration(int arg3, int arg4, int arg5, int arg6) throws IllegalArgumentException {
        if(Build$VERSION.SDK_INT >= 26) {
            super.setAutoSizeTextTypeUniformWithConfiguration(arg3, arg4, arg5, arg6);
        }
        else if(this.mTextHelper != null) {
            this.mTextHelper.setAutoSizeTextTypeUniformWithConfiguration(arg3, arg4, arg5, arg6);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] arg3, int arg4) throws IllegalArgumentException {
        if(Build$VERSION.SDK_INT >= 26) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(arg3, arg4);
        }
        else if(this.mTextHelper != null) {
            this.mTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(arg3, arg4);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setAutoSizeTextTypeWithDefaults(int arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            super.setAutoSizeTextTypeWithDefaults(arg3);
        }
        else if(this.mTextHelper != null) {
            this.mTextHelper.setAutoSizeTextTypeWithDefaults(arg3);
        }
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

    public void setSupportAllCaps(boolean arg2) {
        if(this.mTextHelper != null) {
            this.mTextHelper.setAllCaps(arg2);
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

    public void setTextSize(int arg3, float arg4) {
        if(Build$VERSION.SDK_INT >= 26) {
            super.setTextSize(arg3, arg4);
        }
        else if(this.mTextHelper != null) {
            this.mTextHelper.setTextSize(arg3, arg4);
        }
    }
}

