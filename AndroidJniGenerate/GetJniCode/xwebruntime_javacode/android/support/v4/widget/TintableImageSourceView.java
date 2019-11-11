package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public interface TintableImageSourceView {
    @Nullable ColorStateList getSupportImageTintList();

    @Nullable PorterDuff$Mode getSupportImageTintMode();

    void setSupportImageTintList(@Nullable ColorStateList arg1);

    void setSupportImageTintMode(@Nullable PorterDuff$Mode arg1);
}

