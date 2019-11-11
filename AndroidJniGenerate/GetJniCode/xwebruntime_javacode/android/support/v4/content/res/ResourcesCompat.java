package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.TypefaceCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    private ResourcesCompat() {
        super();
    }

    @ColorInt public static int getColor(@NonNull Resources arg2, @ColorRes int arg3, @Nullable Resources$Theme arg4) throws Resources$NotFoundException {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColor(arg3, arg4);
        }

        return arg2.getColor(arg3);
    }

    @Nullable public static ColorStateList getColorStateList(@NonNull Resources arg2, @ColorRes int arg3, @Nullable Resources$Theme arg4) throws Resources$NotFoundException {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColorStateList(arg3, arg4);
        }

        return arg2.getColorStateList(arg3);
    }

    @Nullable public static Drawable getDrawable(@NonNull Resources arg2, @DrawableRes int arg3, @Nullable Resources$Theme arg4) throws Resources$NotFoundException {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getDrawable(arg3, arg4);
        }

        return arg2.getDrawable(arg3);
    }

    @Nullable public static Drawable getDrawableForDensity(@NonNull Resources arg2, @DrawableRes int arg3, int arg4, @Nullable Resources$Theme arg5) throws Resources$NotFoundException {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getDrawableForDensity(arg3, arg4, arg5);
        }

        if(Build$VERSION.SDK_INT >= 15) {
            return arg2.getDrawableForDensity(arg3, arg4);
        }

        return arg2.getDrawable(arg3);
    }

    @Nullable public static Typeface getFont(@NonNull Context arg3, @FontRes int arg4) throws Resources$NotFoundException {
        Typeface v1 = null;
        if(arg3.isRestricted()) {
            return v1;
        }

        return ResourcesCompat.loadFont(arg3, arg4, new TypedValue(), 0, ((TextView)v1));
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static Typeface getFont(@NonNull Context arg1, @FontRes int arg2, TypedValue arg3, int arg4, @Nullable TextView arg5) throws Resources$NotFoundException {
        if(arg1.isRestricted()) {
            return null;
        }

        return ResourcesCompat.loadFont(arg1, arg2, arg3, arg4, arg5);
    }

    private static Typeface loadFont(@NonNull Context arg6, int arg7, TypedValue arg8, int arg9, @Nullable TextView arg10) {
        Resources v1 = arg6.getResources();
        v1.getValue(arg7, arg8, true);
        Typeface v6 = ResourcesCompat.loadFont(arg6, v1, arg8, arg7, arg9, arg10);
        if(v6 != null) {
            return v6;
        }

        StringBuilder v8 = new StringBuilder();
        v8.append("Font resource ID #0x");
        v8.append(Integer.toHexString(arg7));
        throw new Resources$NotFoundException(v8.toString());
    }

    private static Typeface loadFont(@NonNull Context arg8, Resources arg9, TypedValue arg10, int arg11, int arg12, @Nullable TextView arg13) {
        if(arg10.string == null) {
            StringBuilder v12 = new StringBuilder();
            v12.append("Resource \"");
            v12.append(arg9.getResourceName(arg11));
            v12.append("\" (");
            v12.append(Integer.toHexString(arg11));
            v12.append(") is not a Font: ");
            v12.append(arg10);
            throw new Resources$NotFoundException(v12.toString());
        }

        String v10 = arg10.string.toString();
        Typeface v1 = null;
        if(!v10.startsWith("res/")) {
            return v1;
        }

        Typeface v0 = TypefaceCompat.findFromCache(arg9, arg11, arg12);
        if(v0 != null) {
            return v0;
        }

        try {
            if(v10.toLowerCase().endsWith(".xml")) {
                FamilyResourceEntry v3 = FontResourcesParserCompat.parse(arg9.getXml(arg11), arg9);
                if(v3 == null) {
                    Log.e("ResourcesCompat", "Failed to find font-family tag");
                    return v1;
                }

                return TypefaceCompat.createFromResourcesFamilyXml(arg8, v3, arg9, arg11, arg12, arg13);
            }

            return TypefaceCompat.createFromResourcesFontFile(arg8, arg9, arg11, v10, arg12);
        }
        catch(IOException v8) {
            Log.e("ResourcesCompat", "Failed to read xml resource " + v10, ((Throwable)v8));
        }
        catch(XmlPullParserException v8_1) {
            Log.e("ResourcesCompat", "Failed to parse xml resource " + v10, ((Throwable)v8_1));
        }

        return v1;
    }
}

