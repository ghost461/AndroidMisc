package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$styleable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(value=9) class AppCompatTextHelper {
    @NonNull private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mStyle;
    final TextView mView;

    AppCompatTextHelper(TextView arg2) {
        super();
        this.mStyle = 0;
        this.mView = arg2;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(this.mView);
    }

    final void applyCompoundDrawableTint(Drawable arg2, TintInfo arg3) {
        if(arg2 != null && arg3 != null) {
            AppCompatDrawableManager.tintDrawable(arg2, arg3, this.mView.getDrawableState());
        }
    }

    void applyCompoundDrawablesTints() {
        if(this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
            Drawable[] v0 = this.mView.getCompoundDrawables();
            this.applyCompoundDrawableTint(v0[0], this.mDrawableLeftTint);
            this.applyCompoundDrawableTint(v0[1], this.mDrawableTopTint);
            this.applyCompoundDrawableTint(v0[2], this.mDrawableRightTint);
            this.applyCompoundDrawableTint(v0[3], this.mDrawableBottomTint);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    static AppCompatTextHelper create(TextView arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return new AppCompatTextHelperV17(arg2);
        }

        return new AppCompatTextHelper(arg2);
    }

    protected static TintInfo createTintInfo(Context arg0, AppCompatDrawableManager arg1, int arg2) {
        ColorStateList v0 = arg1.getTintList(arg0, arg2);
        if(v0 != null) {
            TintInfo v1 = new TintInfo();
            v1.mHasTintList = true;
            v1.mTintList = v0;
            return v1;
        }

        return null;
    }

    int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    void loadFromAttributes(AttributeSet arg14, int arg15) {
        ColorStateList v10;
        ColorStateList v9;
        boolean v8;
        TintTypedArray v4_1;
        Context v0 = this.mView.getContext();
        AppCompatDrawableManager v1 = AppCompatDrawableManager.get();
        TintTypedArray v2 = TintTypedArray.obtainStyledAttributes(v0, arg14, styleable.AppCompatTextHelper, arg15, 0);
        int v5 = -1;
        int v4 = v2.getResourceId(styleable.AppCompatTextHelper_android_textAppearance, v5);
        if(v2.hasValue(styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.mDrawableLeftTint = AppCompatTextHelper.createTintInfo(v0, v1, v2.getResourceId(styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }

        if(v2.hasValue(styleable.AppCompatTextHelper_android_drawableTop)) {
            this.mDrawableTopTint = AppCompatTextHelper.createTintInfo(v0, v1, v2.getResourceId(styleable.AppCompatTextHelper_android_drawableTop, 0));
        }

        if(v2.hasValue(styleable.AppCompatTextHelper_android_drawableRight)) {
            this.mDrawableRightTint = AppCompatTextHelper.createTintInfo(v0, v1, v2.getResourceId(styleable.AppCompatTextHelper_android_drawableRight, 0));
        }

        if(v2.hasValue(styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.mDrawableBottomTint = AppCompatTextHelper.createTintInfo(v0, v1, v2.getResourceId(styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }

        v2.recycle();
        boolean v1_1 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int v2_1 = 23;
        ColorStateList v7 = null;
        if(v4 != v5) {
            v4_1 = TintTypedArray.obtainStyledAttributes(v0, v4, styleable.TextAppearance);
            if((v1_1) || !v4_1.hasValue(styleable.TextAppearance_textAllCaps)) {
                v5 = 0;
                v8 = false;
            }
            else {
                v8 = v4_1.getBoolean(styleable.TextAppearance_textAllCaps, false);
                v5 = 1;
            }

            this.updateTypefaceAndStyle(v0, v4_1);
            if(Build$VERSION.SDK_INT < v2_1) {
                v9 = v4_1.hasValue(styleable.TextAppearance_android_textColor) ? v4_1.getColorStateList(styleable.TextAppearance_android_textColor) : v7;
                v10 = v4_1.hasValue(styleable.TextAppearance_android_textColorHint) ? v4_1.getColorStateList(styleable.TextAppearance_android_textColorHint) : v7;
                if(v4_1.hasValue(styleable.TextAppearance_android_textColorLink)) {
                    v7 = v4_1.getColorStateList(styleable.TextAppearance_android_textColorLink);
                }

                ColorStateList v12 = v9;
                v9 = v7;
                v7 = v12;
            }
            else {
                v9 = v7;
                v10 = v9;
            }

            v4_1.recycle();
        }
        else {
            v9 = v7;
            v10 = v9;
            v5 = 0;
            v8 = false;
        }

        v4_1 = TintTypedArray.obtainStyledAttributes(v0, arg14, styleable.TextAppearance, arg15, 0);
        if(!v1_1 && (v4_1.hasValue(styleable.TextAppearance_textAllCaps))) {
            v8 = v4_1.getBoolean(styleable.TextAppearance_textAllCaps, false);
            v5 = 1;
        }

        if(Build$VERSION.SDK_INT < v2_1) {
            if(v4_1.hasValue(styleable.TextAppearance_android_textColor)) {
                v7 = v4_1.getColorStateList(styleable.TextAppearance_android_textColor);
            }

            if(v4_1.hasValue(styleable.TextAppearance_android_textColorHint)) {
                v10 = v4_1.getColorStateList(styleable.TextAppearance_android_textColorHint);
            }

            if(!v4_1.hasValue(styleable.TextAppearance_android_textColorLink)) {
                goto label_120;
            }

            v9 = v4_1.getColorStateList(styleable.TextAppearance_android_textColorLink);
        }

    label_120:
        this.updateTypefaceAndStyle(v0, v4_1);
        v4_1.recycle();
        if(v7 != null) {
            this.mView.setTextColor(v7);
        }

        if(v10 != null) {
            this.mView.setHintTextColor(v10);
        }

        if(v9 != null) {
            this.mView.setLinkTextColor(v9);
        }

        if(!v1_1 && v5 != 0) {
            this.setAllCaps(v8);
        }

        if(this.mFontTypeface != null) {
            this.mView.setTypeface(this.mFontTypeface, this.mStyle);
        }

        this.mAutoSizeTextHelper.loadFromAttributes(arg14, arg15);
        if(Build$VERSION.SDK_INT >= 26 && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
            int[] v14 = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
            if(v14.length > 0) {
                if((((float)this.mView.getAutoSizeStepGranularity())) != -1f) {
                    this.mView.setAutoSizeTextTypeUniformWithConfiguration(this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
                }
                else {
                    this.mView.setAutoSizeTextTypeUniformWithPresetSizes(v14, 0);
                }
            }
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        if(Build$VERSION.SDK_INT < 26) {
            this.autoSizeText();
        }
    }

    void onSetTextAppearance(Context arg3, int arg4) {
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(arg3, arg4, styleable.TextAppearance);
        if(v4.hasValue(styleable.TextAppearance_textAllCaps)) {
            this.setAllCaps(v4.getBoolean(styleable.TextAppearance_textAllCaps, false));
        }

        if(Build$VERSION.SDK_INT < 23 && (v4.hasValue(styleable.TextAppearance_android_textColor))) {
            ColorStateList v0 = v4.getColorStateList(styleable.TextAppearance_android_textColor);
            if(v0 != null) {
                this.mView.setTextColor(v0);
            }
        }

        this.updateTypefaceAndStyle(arg3, v4);
        v4.recycle();
        if(this.mFontTypeface != null) {
            this.mView.setTypeface(this.mFontTypeface, this.mStyle);
        }
    }

    void setAllCaps(boolean arg2) {
        this.mView.setAllCaps(arg2);
    }

    void setAutoSizeTextTypeUniformWithConfiguration(int arg2, int arg3, int arg4, int arg5) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(arg2, arg3, arg4, arg5);
    }

    void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] arg2, int arg3) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(arg2, arg3);
    }

    void setAutoSizeTextTypeWithDefaults(int arg2) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(arg2);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) void setTextSize(int arg3, float arg4) {
        if(Build$VERSION.SDK_INT < 26 && !this.isAutoSizeEnabled()) {
            this.setTextSizeInternal(arg3, arg4);
        }
    }

    private void setTextSizeInternal(int arg2, float arg3) {
        this.mAutoSizeTextHelper.setTextSizeInternal(arg2, arg3);
    }

    private void updateTypefaceAndStyle(Context arg3, TintTypedArray arg4) {
        this.mStyle = arg4.getInt(styleable.TextAppearance_android_textStyle, this.mStyle);
        if((arg4.hasValue(styleable.TextAppearance_android_fontFamily)) || (arg4.hasValue(styleable.TextAppearance_fontFamily))) {
            this.mFontTypeface = null;
            int v0 = arg4.hasValue(styleable.TextAppearance_android_fontFamily) ? styleable.TextAppearance_android_fontFamily : styleable.TextAppearance_fontFamily;
            if(!arg3.isRestricted()) {
                try {
                    this.mFontTypeface = arg4.getFont(v0, this.mStyle, this.mView);
                    goto label_24;
                }
                catch(Resources$NotFoundException ) {
                label_24:
                    if(this.mFontTypeface != null) {
                        return;
                    }

                    this.mFontTypeface = Typeface.create(arg4.getString(v0), this.mStyle);
                    return;
                }
            }

            goto label_24;
        }
    }
}

