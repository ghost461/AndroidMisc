package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

public final class TextViewCompat {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface AutoSizeTextType {
    }

    @RequiresApi(value=16) class TextViewCompatApi16Impl extends TextViewCompatBaseImpl {
        TextViewCompatApi16Impl() {
            super();
        }

        public int getMaxLines(TextView arg1) {
            return arg1.getMaxLines();
        }

        public int getMinLines(TextView arg1) {
            return arg1.getMinLines();
        }
    }

    @RequiresApi(value=17) class TextViewCompatApi17Impl extends TextViewCompatApi16Impl {
        TextViewCompatApi17Impl() {
            super();
        }

        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView arg5) {
            int v1 = 1;
            if(arg5.getLayoutDirection() == 1) {
            }
            else {
                v1 = 0;
            }

            Drawable[] v5 = arg5.getCompoundDrawables();
            if(v1 != 0) {
                Drawable v1_1 = v5[2];
                Drawable v3 = v5[0];
                v5[0] = v1_1;
                v5[2] = v3;
            }

            return v5;
        }

        public void setCompoundDrawablesRelative(@NonNull TextView arg3, @Nullable Drawable arg4, @Nullable Drawable arg5, @Nullable Drawable arg6, @Nullable Drawable arg7) {
            int v1 = 1;
            if(arg3.getLayoutDirection() == 1) {
            }
            else {
                v1 = 0;
            }

            Drawable v0 = v1 != 0 ? arg6 : arg4;
            if(v1 != 0) {
            }
            else {
                arg4 = arg6;
            }

            arg3.setCompoundDrawables(v0, arg5, arg4, arg7);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg3, @DrawableRes int arg4, @DrawableRes int arg5, @DrawableRes int arg6, @DrawableRes int arg7) {
            int v1 = 1;
            if(arg3.getLayoutDirection() == 1) {
            }
            else {
                v1 = 0;
            }

            int v0 = v1 != 0 ? arg6 : arg4;
            if(v1 != 0) {
            }
            else {
                arg4 = arg6;
            }

            arg3.setCompoundDrawablesWithIntrinsicBounds(v0, arg5, arg4, arg7);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg3, @Nullable Drawable arg4, @Nullable Drawable arg5, @Nullable Drawable arg6, @Nullable Drawable arg7) {
            int v1 = 1;
            if(arg3.getLayoutDirection() == 1) {
            }
            else {
                v1 = 0;
            }

            Drawable v0 = v1 != 0 ? arg6 : arg4;
            if(v1 != 0) {
            }
            else {
                arg4 = arg6;
            }

            arg3.setCompoundDrawablesWithIntrinsicBounds(v0, arg5, arg4, arg7);
        }
    }

    @RequiresApi(value=18) class TextViewCompatApi18Impl extends TextViewCompatApi17Impl {
        TextViewCompatApi18Impl() {
            super();
        }

        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView arg1) {
            return arg1.getCompoundDrawablesRelative();
        }

        public void setCompoundDrawablesRelative(@NonNull TextView arg1, @Nullable Drawable arg2, @Nullable Drawable arg3, @Nullable Drawable arg4, @Nullable Drawable arg5) {
            arg1.setCompoundDrawablesRelative(arg2, arg3, arg4, arg5);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg1, @DrawableRes int arg2, @DrawableRes int arg3, @DrawableRes int arg4, @DrawableRes int arg5) {
            arg1.setCompoundDrawablesRelativeWithIntrinsicBounds(arg2, arg3, arg4, arg5);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg1, @Nullable Drawable arg2, @Nullable Drawable arg3, @Nullable Drawable arg4, @Nullable Drawable arg5) {
            arg1.setCompoundDrawablesRelativeWithIntrinsicBounds(arg2, arg3, arg4, arg5);
        }
    }

    @RequiresApi(value=23) class TextViewCompatApi23Impl extends TextViewCompatApi18Impl {
        TextViewCompatApi23Impl() {
            super();
        }

        public void setTextAppearance(@NonNull TextView arg1, @StyleRes int arg2) {
            arg1.setTextAppearance(arg2);
        }
    }

    @RequiresApi(value=26) class TextViewCompatApi26Impl extends TextViewCompatApi23Impl {
        TextViewCompatApi26Impl() {
            super();
        }

        public int getAutoSizeMaxTextSize(TextView arg1) {
            return arg1.getAutoSizeMaxTextSize();
        }

        public int getAutoSizeMinTextSize(TextView arg1) {
            return arg1.getAutoSizeMinTextSize();
        }

        public int getAutoSizeStepGranularity(TextView arg1) {
            return arg1.getAutoSizeStepGranularity();
        }

        public int[] getAutoSizeTextAvailableSizes(TextView arg1) {
            return arg1.getAutoSizeTextAvailableSizes();
        }

        public int getAutoSizeTextType(TextView arg1) {
            return arg1.getAutoSizeTextType();
        }

        public void setAutoSizeTextTypeUniformWithConfiguration(TextView arg1, int arg2, int arg3, int arg4, int arg5) throws IllegalArgumentException {
            arg1.setAutoSizeTextTypeUniformWithConfiguration(arg2, arg3, arg4, arg5);
        }

        public void setAutoSizeTextTypeUniformWithPresetSizes(TextView arg1, @NonNull int[] arg2, int arg3) throws IllegalArgumentException {
            arg1.setAutoSizeTextTypeUniformWithPresetSizes(arg2, arg3);
        }

        public void setAutoSizeTextTypeWithDefaults(TextView arg1, int arg2) {
            arg1.setAutoSizeTextTypeWithDefaults(arg2);
        }
    }

    class TextViewCompatBaseImpl {
        private static final int LINES = 1;
        private static final String LOG_TAG = "TextViewCompatBase";
        private static Field sMaxModeField;
        private static boolean sMaxModeFieldFetched;
        private static Field sMaximumField;
        private static boolean sMaximumFieldFetched;
        private static Field sMinModeField;
        private static boolean sMinModeFieldFetched;
        private static Field sMinimumField;
        private static boolean sMinimumFieldFetched;

        TextViewCompatBaseImpl() {
            super();
        }

        public int getAutoSizeMaxTextSize(TextView arg2) {
            if((arg2 instanceof AutoSizeableTextView)) {
                return ((AutoSizeableTextView)arg2).getAutoSizeMaxTextSize();
            }

            return -1;
        }

        public int getAutoSizeMinTextSize(TextView arg2) {
            if((arg2 instanceof AutoSizeableTextView)) {
                return ((AutoSizeableTextView)arg2).getAutoSizeMinTextSize();
            }

            return -1;
        }

        public int getAutoSizeStepGranularity(TextView arg2) {
            if((arg2 instanceof AutoSizeableTextView)) {
                return ((AutoSizeableTextView)arg2).getAutoSizeStepGranularity();
            }

            return -1;
        }

        public int[] getAutoSizeTextAvailableSizes(TextView arg2) {
            if((arg2 instanceof AutoSizeableTextView)) {
                return ((AutoSizeableTextView)arg2).getAutoSizeTextAvailableSizes();
            }

            return new int[0];
        }

        public int getAutoSizeTextType(TextView arg2) {
            if((arg2 instanceof AutoSizeableTextView)) {
                return ((AutoSizeableTextView)arg2).getAutoSizeTextType();
            }

            return 0;
        }

        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView arg1) {
            return arg1.getCompoundDrawables();
        }

        public int getMaxLines(TextView arg3) {
            if(!TextViewCompatBaseImpl.sMaxModeFieldFetched) {
                TextViewCompatBaseImpl.sMaxModeField = TextViewCompatBaseImpl.retrieveField("mMaxMode");
                TextViewCompatBaseImpl.sMaxModeFieldFetched = true;
            }

            if(TextViewCompatBaseImpl.sMaxModeField != null && TextViewCompatBaseImpl.retrieveIntFromField(TextViewCompatBaseImpl.sMaxModeField, arg3) == 1) {
                if(!TextViewCompatBaseImpl.sMaximumFieldFetched) {
                    TextViewCompatBaseImpl.sMaximumField = TextViewCompatBaseImpl.retrieveField("mMaximum");
                    TextViewCompatBaseImpl.sMaximumFieldFetched = true;
                }

                if(TextViewCompatBaseImpl.sMaximumField == null) {
                    return -1;
                }

                return TextViewCompatBaseImpl.retrieveIntFromField(TextViewCompatBaseImpl.sMaximumField, arg3);
            }

            return -1;
        }

        public int getMinLines(TextView arg3) {
            if(!TextViewCompatBaseImpl.sMinModeFieldFetched) {
                TextViewCompatBaseImpl.sMinModeField = TextViewCompatBaseImpl.retrieveField("mMinMode");
                TextViewCompatBaseImpl.sMinModeFieldFetched = true;
            }

            if(TextViewCompatBaseImpl.sMinModeField != null && TextViewCompatBaseImpl.retrieveIntFromField(TextViewCompatBaseImpl.sMinModeField, arg3) == 1) {
                if(!TextViewCompatBaseImpl.sMinimumFieldFetched) {
                    TextViewCompatBaseImpl.sMinimumField = TextViewCompatBaseImpl.retrieveField("mMinimum");
                    TextViewCompatBaseImpl.sMinimumFieldFetched = true;
                }

                if(TextViewCompatBaseImpl.sMinimumField == null) {
                    return -1;
                }

                return TextViewCompatBaseImpl.retrieveIntFromField(TextViewCompatBaseImpl.sMinimumField, arg3);
            }

            return -1;
        }

        private static Field retrieveField(String arg4) {
            Field v0;
            Field v1;
            try {
                v1 = TextView.class.getDeclaredField(arg4);
                v0 = null;
            }
            catch(NoSuchFieldException ) {
                v1 = v0;
                goto label_7;
            }

            try {
                v1.setAccessible(true);
            }
            catch(NoSuchFieldException ) {
            label_7:
                Log.e("TextViewCompatBase", "Could not retrieve " + arg4 + " field.");
            }

            return v1;
        }

        private static int retrieveIntFromField(Field arg2, TextView arg3) {
            try {
                return arg2.getInt(arg3);
            }
            catch(IllegalAccessException ) {
                Log.d("TextViewCompatBase", "Could not retrieve value of " + arg2.getName() + " field.");
                return -1;
            }
        }

        public void setAutoSizeTextTypeUniformWithConfiguration(TextView arg2, int arg3, int arg4, int arg5, int arg6) throws IllegalArgumentException {
            if((arg2 instanceof AutoSizeableTextView)) {
                ((AutoSizeableTextView)arg2).setAutoSizeTextTypeUniformWithConfiguration(arg3, arg4, arg5, arg6);
            }
        }

        public void setAutoSizeTextTypeUniformWithPresetSizes(TextView arg2, @NonNull int[] arg3, int arg4) throws IllegalArgumentException {
            if((arg2 instanceof AutoSizeableTextView)) {
                ((AutoSizeableTextView)arg2).setAutoSizeTextTypeUniformWithPresetSizes(arg3, arg4);
            }
        }

        public void setAutoSizeTextTypeWithDefaults(TextView arg2, int arg3) {
            if((arg2 instanceof AutoSizeableTextView)) {
                ((AutoSizeableTextView)arg2).setAutoSizeTextTypeWithDefaults(arg3);
            }
        }

        public void setCompoundDrawablesRelative(@NonNull TextView arg1, @Nullable Drawable arg2, @Nullable Drawable arg3, @Nullable Drawable arg4, @Nullable Drawable arg5) {
            arg1.setCompoundDrawables(arg2, arg3, arg4, arg5);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg1, @DrawableRes int arg2, @DrawableRes int arg3, @DrawableRes int arg4, @DrawableRes int arg5) {
            arg1.setCompoundDrawablesWithIntrinsicBounds(arg2, arg3, arg4, arg5);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg1, @Nullable Drawable arg2, @Nullable Drawable arg3, @Nullable Drawable arg4, @Nullable Drawable arg5) {
            arg1.setCompoundDrawablesWithIntrinsicBounds(arg2, arg3, arg4, arg5);
        }

        public void setTextAppearance(TextView arg2, @StyleRes int arg3) {
            arg2.setTextAppearance(arg2.getContext(), arg3);
        }
    }

    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    static final TextViewCompatBaseImpl IMPL;

    static {
        if(Build$VERSION.SDK_INT >= 26) {
            TextViewCompat.IMPL = new TextViewCompatApi26Impl();
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            TextViewCompat.IMPL = new TextViewCompatApi23Impl();
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            TextViewCompat.IMPL = new TextViewCompatApi18Impl();
        }
        else if(Build$VERSION.SDK_INT >= 17) {
            TextViewCompat.IMPL = new TextViewCompatApi17Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            TextViewCompat.IMPL = new TextViewCompatApi16Impl();
        }
        else {
            TextViewCompat.IMPL = new TextViewCompatBaseImpl();
        }
    }

    private TextViewCompat() {
        super();
    }

    public static int getAutoSizeMaxTextSize(TextView arg1) {
        return TextViewCompat.IMPL.getAutoSizeMaxTextSize(arg1);
    }

    public static int getAutoSizeMinTextSize(TextView arg1) {
        return TextViewCompat.IMPL.getAutoSizeMinTextSize(arg1);
    }

    public static int getAutoSizeStepGranularity(TextView arg1) {
        return TextViewCompat.IMPL.getAutoSizeStepGranularity(arg1);
    }

    public static int[] getAutoSizeTextAvailableSizes(TextView arg1) {
        return TextViewCompat.IMPL.getAutoSizeTextAvailableSizes(arg1);
    }

    public static int getAutoSizeTextType(TextView arg1) {
        return TextViewCompat.IMPL.getAutoSizeTextType(arg1);
    }

    public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView arg1) {
        return TextViewCompat.IMPL.getCompoundDrawablesRelative(arg1);
    }

    public static int getMaxLines(@NonNull TextView arg1) {
        return TextViewCompat.IMPL.getMaxLines(arg1);
    }

    public static int getMinLines(@NonNull TextView arg1) {
        return TextViewCompat.IMPL.getMinLines(arg1);
    }

    public static void setAutoSizeTextTypeUniformWithConfiguration(TextView arg6, int arg7, int arg8, int arg9, int arg10) throws IllegalArgumentException {
        TextViewCompat.IMPL.setAutoSizeTextTypeUniformWithConfiguration(arg6, arg7, arg8, arg9, arg10);
    }

    public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView arg1, @NonNull int[] arg2, int arg3) throws IllegalArgumentException {
        TextViewCompat.IMPL.setAutoSizeTextTypeUniformWithPresetSizes(arg1, arg2, arg3);
    }

    public static void setAutoSizeTextTypeWithDefaults(TextView arg1, int arg2) {
        TextViewCompat.IMPL.setAutoSizeTextTypeWithDefaults(arg1, arg2);
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView arg6, @Nullable Drawable arg7, @Nullable Drawable arg8, @Nullable Drawable arg9, @Nullable Drawable arg10) {
        TextViewCompat.IMPL.setCompoundDrawablesRelative(arg6, arg7, arg8, arg9, arg10);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg6, @DrawableRes int arg7, @DrawableRes int arg8, @DrawableRes int arg9, @DrawableRes int arg10) {
        TextViewCompat.IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(arg6, arg7, arg8, arg9, arg10);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView arg6, @Nullable Drawable arg7, @Nullable Drawable arg8, @Nullable Drawable arg9, @Nullable Drawable arg10) {
        TextViewCompat.IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(arg6, arg7, arg8, arg9, arg10);
    }

    public static void setTextAppearance(@NonNull TextView arg1, @StyleRes int arg2) {
        TextViewCompat.IMPL.setTextAppearance(arg1, arg2);
    }
}

