package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public TypedArrayUtils() {
        super();
    }

    public static int getAttr(Context arg2, int arg3, int arg4) {
        TypedValue v0 = new TypedValue();
        arg2.getTheme().resolveAttribute(arg3, v0, true);
        if(v0.resourceId != 0) {
            return arg3;
        }

        return arg4;
    }

    public static boolean getBoolean(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2, boolean arg3) {
        return arg0.getBoolean(arg1, arg0.getBoolean(arg2, arg3));
    }

    public static Drawable getDrawable(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2) {
        Drawable v1 = arg0.getDrawable(arg1);
        if(v1 == null) {
            v1 = arg0.getDrawable(arg2);
        }

        return v1;
    }

    public static int getInt(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2, int arg3) {
        return arg0.getInt(arg1, arg0.getInt(arg2, arg3));
    }

    public static boolean getNamedBoolean(@NonNull TypedArray arg0, @NonNull XmlPullParser arg1, String arg2, @StyleableRes int arg3, boolean arg4) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return arg4;
        }

        return arg0.getBoolean(arg3, arg4);
    }

    @ColorInt public static int getNamedColor(@NonNull TypedArray arg0, @NonNull XmlPullParser arg1, String arg2, @StyleableRes int arg3, @ColorInt int arg4) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return arg4;
        }

        return arg0.getColor(arg3, arg4);
    }

    public static float getNamedFloat(@NonNull TypedArray arg0, @NonNull XmlPullParser arg1, @NonNull String arg2, @StyleableRes int arg3, float arg4) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return arg4;
        }

        return arg0.getFloat(arg3, arg4);
    }

    public static int getNamedInt(@NonNull TypedArray arg0, @NonNull XmlPullParser arg1, String arg2, @StyleableRes int arg3, int arg4) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return arg4;
        }

        return arg0.getInt(arg3, arg4);
    }

    @AnyRes public static int getNamedResourceId(@NonNull TypedArray arg0, @NonNull XmlPullParser arg1, String arg2, @StyleableRes int arg3, @AnyRes int arg4) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return arg4;
        }

        return arg0.getResourceId(arg3, arg4);
    }

    public static String getNamedString(@NonNull TypedArray arg0, @NonNull XmlPullParser arg1, String arg2, @StyleableRes int arg3) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return null;
        }

        return arg0.getString(arg3);
    }

    @AnyRes public static int getResourceId(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2, @AnyRes int arg3) {
        return arg0.getResourceId(arg1, arg0.getResourceId(arg2, arg3));
    }

    public static String getString(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2) {
        String v1 = arg0.getString(arg1);
        if(v1 == null) {
            v1 = arg0.getString(arg2);
        }

        return v1;
    }

    public static CharSequence getText(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2) {
        CharSequence v1 = arg0.getText(arg1);
        if(v1 == null) {
            v1 = arg0.getText(arg2);
        }

        return v1;
    }

    public static CharSequence[] getTextArray(TypedArray arg0, @StyleableRes int arg1, @StyleableRes int arg2) {
        CharSequence[] v1 = arg0.getTextArray(arg1);
        if(v1 == null) {
            v1 = arg0.getTextArray(arg2);
        }

        return v1;
    }

    public static boolean hasAttribute(@NonNull XmlPullParser arg1, @NonNull String arg2) {
        boolean v1 = arg1.getAttributeValue("http://schemas.android.com/apk/res/android", arg2) != null ? true : false;
        return v1;
    }

    public static TypedArray obtainAttributes(Resources arg0, Resources$Theme arg1, AttributeSet arg2, int[] arg3) {
        if(arg1 == null) {
            return arg0.obtainAttributes(arg2, arg3);
        }

        return arg1.obtainStyledAttributes(arg2, arg3, 0, 0);
    }

    public static TypedValue peekNamedValue(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3) {
        if(!TypedArrayUtils.hasAttribute(arg1, arg2)) {
            return null;
        }

        return arg0.peekValue(arg3);
    }
}

