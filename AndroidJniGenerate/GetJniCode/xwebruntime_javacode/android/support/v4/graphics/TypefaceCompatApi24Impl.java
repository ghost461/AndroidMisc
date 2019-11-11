package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat$FontInfo;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi(value=24) @RestrictTo(value={Scope.LIBRARY_GROUP}) class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi24Impl";
    private static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;

    static {
        Constructor v4_1;
        Constructor v2_1;
        Constructor v0;
        Class v1_1;
        try {
            v1_1 = Class.forName("android.graphics.FontFamily");
            Constructor v3 = v1_1.getConstructor();
            Method v4 = v1_1.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            Method v2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(v1_1, 1).getClass());
            v0 = v3;
        }
        catch(NoSuchMethodException v1) {
            Log.e("TypefaceCompatApi24Impl", v1.getClass().getName(), ((Throwable)v1));
            Constructor v1_2 = v0;
            v2_1 = v1_2;
            v4_1 = v2_1;
        }

        TypefaceCompatApi24Impl.sFontFamilyCtor = v0;
        TypefaceCompatApi24Impl.sFontFamily = v1_1;
        TypefaceCompatApi24Impl.sAddFontWeightStyle = ((Method)v4_1);
        TypefaceCompatApi24Impl.sCreateFromFamiliesWithDefault = ((Method)v2_1);
    }

    TypefaceCompatApi24Impl() {
        super();
    }

    private static boolean addFontWeightStyle(Object arg3, ByteBuffer arg4, int arg5, int arg6, boolean arg7) {
        try {
            return TypefaceCompatApi24Impl.sAddFontWeightStyle.invoke(arg3, arg4, Integer.valueOf(arg5), null, Integer.valueOf(arg6), Boolean.valueOf(arg7)).booleanValue();
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object arg4) {
        try {
            Object v0 = Array.newInstance(TypefaceCompatApi24Impl.sFontFamily, 1);
            Array.set(v0, 0, arg4);
            return TypefaceCompatApi24Impl.sCreateFromFamiliesWithDefault.invoke(null, v0);
        }
        catch(InvocationTargetException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context arg7, FontFamilyFilesResourceEntry arg8, Resources arg9, int arg10) {
        Object v10 = TypefaceCompatApi24Impl.newFamily();
        FontFileResourceEntry[] v8 = arg8.getEntries();
        int v0 = v8.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(!TypefaceCompatApi24Impl.addFontWeightStyle(v10, TypefaceCompatUtil.copyToDirectBuffer(arg7, arg9, v8[v2].getResourceId()), 0, v8[v2].getWeight(), v8[v2].isItalic())) {
                return null;
            }
        }

        return TypefaceCompatApi24Impl.createFromFamiliesWithDefault(v10);
    }

    public Typeface createFromFontInfo(Context arg8, @Nullable CancellationSignal arg9, @NonNull FontInfo[] arg10, int arg11) {
        Object v11 = TypefaceCompatApi24Impl.newFamily();
        SimpleArrayMap v0 = new SimpleArrayMap();
        int v1 = arg10.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            FontInfo v3 = arg10[v2];
            Uri v4 = v3.getUri();
            Object v5 = v0.get(v4);
            if(v5 == null) {
                ByteBuffer v5_1 = TypefaceCompatUtil.mmap(arg8, arg9, v4);
                v0.put(v4, v5_1);
            }

            if(!TypefaceCompatApi24Impl.addFontWeightStyle(v11, v5_1, v3.getTtcIndex(), v3.getWeight(), v3.isItalic())) {
                return null;
            }
        }

        return TypefaceCompatApi24Impl.createFromFamiliesWithDefault(v11);
    }

    public static boolean isUsable() {
        if(TypefaceCompatApi24Impl.sAddFontWeightStyle == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }

        boolean v0 = TypefaceCompatApi24Impl.sAddFontWeightStyle != null ? true : false;
        return v0;
    }

    private static Object newFamily() {
        try {
            return TypefaceCompatApi24Impl.sFontFamilyCtor.newInstance();
        }
        catch(InvocationTargetException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }
}

