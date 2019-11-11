package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(value=17) class AppCompatTextHelperV17 extends AppCompatTextHelper {
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableStartTint;

    AppCompatTextHelperV17(TextView arg1) {
        super(arg1);
    }

    void applyCompoundDrawablesTints() {
        super.applyCompoundDrawablesTints();
        if(this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] v0 = this.mView.getCompoundDrawablesRelative();
            this.applyCompoundDrawableTint(v0[0], this.mDrawableStartTint);
            this.applyCompoundDrawableTint(v0[2], this.mDrawableEndTint);
        }
    }

    void loadFromAttributes(AttributeSet arg5, int arg6) {
        super.loadFromAttributes(arg5, arg6);
        Context v0 = this.mView.getContext();
        AppCompatDrawableManager v1 = AppCompatDrawableManager.get();
        TypedArray v5 = v0.obtainStyledAttributes(arg5, styleable.AppCompatTextHelper, arg6, 0);
        if(v5.hasValue(styleable.AppCompatTextHelper_android_drawableStart)) {
            this.mDrawableStartTint = AppCompatTextHelperV17.createTintInfo(v0, v1, v5.getResourceId(styleable.AppCompatTextHelper_android_drawableStart, 0));
        }

        if(v5.hasValue(styleable.AppCompatTextHelper_android_drawableEnd)) {
            this.mDrawableEndTint = AppCompatTextHelperV17.createTintInfo(v0, v1, v5.getResourceId(styleable.AppCompatTextHelper_android_drawableEnd, 0));
        }

        v5.recycle();
    }
}

