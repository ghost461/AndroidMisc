package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface$Builder;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat$FontInfo;
import android.support.v4.provider.FontsContractCompat;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(value=26) @RestrictTo(value={Scope.LIBRARY_GROUP}) public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Constructor v6_1;
        Constructor v0;
        Method v2;
        Method v10;
        Method v5;
        Method v4;
        Class v1_1;
        try {
            v1_1 = Class.forName("android.graphics.FontFamily");
            Constructor v3 = v1_1.getConstructor();
            v4 = v1_1.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
            v5 = v1_1.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
            Method v6 = v1_1.getMethod("freeze");
            v10 = v1_1.getMethod("abortCreation");
            v2 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(v1_1, 1).getClass(), Integer.TYPE, Integer.TYPE);
            v2.setAccessible(true);
            v0 = v3;
        }
        catch(NoSuchMethodException v1) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + v1.getClass().getName(), ((Throwable)v1));
            Constructor v1_2 = v0;
            Constructor v2_1 = v1_2;
            Constructor v4_1 = v2_1;
            Constructor v5_1 = v4_1;
            v6_1 = v5_1;
            Constructor v10_1 = v6_1;
        }

        TypefaceCompatApi26Impl.sFontFamilyCtor = v0;
        TypefaceCompatApi26Impl.sFontFamily = v1_1;
        TypefaceCompatApi26Impl.sAddFontFromAssetManager = v4;
        TypefaceCompatApi26Impl.sAddFontFromBuffer = v5;
        TypefaceCompatApi26Impl.sFreeze = ((Method)v6_1);
        TypefaceCompatApi26Impl.sAbortCreation = v10;
        TypefaceCompatApi26Impl.sCreateFromFamiliesWithDefault = v2;
    }

    public TypefaceCompatApi26Impl() {
        super();
    }

    private static boolean abortCreation(Object arg2) {
        try {
            return TypefaceCompatApi26Impl.sAbortCreation.invoke(arg2).booleanValue();
        }
        catch(InvocationTargetException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }

    private static boolean addFontFromAssetManager(Context arg3, Object arg4, String arg5, int arg6, int arg7, int arg8) {
        try {
            return TypefaceCompatApi26Impl.sAddFontFromAssetManager.invoke(arg4, arg3.getAssets(), arg5, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(arg6), Integer.valueOf(arg7), Integer.valueOf(arg8), null).booleanValue();
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    private static boolean addFontFromBuffer(Object arg3, ByteBuffer arg4, int arg5, int arg6, int arg7) {
        try {
            return TypefaceCompatApi26Impl.sAddFontFromBuffer.invoke(arg3, arg4, Integer.valueOf(arg5), null, Integer.valueOf(arg6), Integer.valueOf(arg7)).booleanValue();
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object arg5) {
        try {
            Object v0 = Array.newInstance(TypefaceCompatApi26Impl.sFontFamily, 1);
            Array.set(v0, 0, arg5);
            return TypefaceCompatApi26Impl.sCreateFromFamiliesWithDefault.invoke(null, v0, Integer.valueOf(-1), Integer.valueOf(-1));
        }
        catch(InvocationTargetException v5) {
            throw new RuntimeException(((Throwable)v5));
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context arg9, FontFamilyFilesResourceEntry arg10, Resources arg11, int arg12) {
        Typeface v7;
        if(!TypefaceCompatApi26Impl.isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(arg9, arg10, arg11, arg12);
        }

        Object v11 = TypefaceCompatApi26Impl.newFamily();
        FontFileResourceEntry[] v10 = arg10.getEntries();
        arg12 = v10.length;
        int v6;
        for(v6 = 0; true; ++v6) {
            v7 = null;
            if(v6 >= arg12) {
                break;
            }

            if(!TypefaceCompatApi26Impl.addFontFromAssetManager(arg9, v11, v10[v6].getFileName(), 0, v10[v6].getWeight(), v10[v6].isItalic())) {
                TypefaceCompatApi26Impl.abortCreation(v11);
                return v7;
            }
        }

        if(!TypefaceCompatApi26Impl.freeze(v11)) {
            return v7;
        }

        return TypefaceCompatApi26Impl.createFromFamiliesWithDefault(v11);
    }

    public Typeface createFromFontInfo(Context arg9, @Nullable CancellationSignal arg10, @NonNull FontInfo[] arg11, int arg12) {
        Typeface v10_1;
        ParcelFileDescriptor v9_1;
        Typeface v2 = null;
        if(arg11.length < 1) {
            return v2;
        }

        if(!TypefaceCompatApi26Impl.isFontFamilyPrivateAPIAvailable()) {
            FontInfo v11 = this.findBestInfo(arg11, arg12);
            ContentResolver v9 = arg9.getContentResolver();
            try {
                v9_1 = v9.openFileDescriptor(v11.getUri(), "r", arg10);
            }
            catch(IOException ) {
                return v2;
            }

            try {
                v10_1 = new Typeface$Builder(v9_1.getFileDescriptor()).setWeight(v11.getWeight()).setItalic(v11.isItalic()).build();
                if(v9_1 == null) {
                    return v10_1;
                }

                goto label_21;
            }
            catch(Throwable v10) {
                v11_1 = ((Throwable)v2);
            }
            catch(Throwable v10) {
                try {
                    throw v10;
                }
                catch(Throwable v11_1) {
                    Throwable v7 = v11_1;
                    v11_1 = v10;
                    v10 = v7;
                }
            }

            if(v9_1 == null) {
                goto label_40;
            }
            else if(v11_1 != null) {
                try {
                    v9_1.close();
                    goto label_40;
                }
                catch(IOException ) {
                }
                catch(Throwable v9_2) {
                    try {
                        ThrowableExtension.addSuppressed(v11_1, v9_2);
                        goto label_40;
                    label_39:
                        v9_1.close();
                    label_40:
                        throw v10;
                    label_21:
                        v9_1.close();
                    }
                    catch(IOException ) {
                        return v2;
                    }
                }
            }
            else {
                goto label_39;
            }

            return v10_1;
        }

        Map v9_3 = FontsContractCompat.prepareFontData(arg9, arg11, arg10);
        Object v10_2 = TypefaceCompatApi26Impl.newFamily();
        arg12 = arg11.length;
        int v0 = 0;
        int v3 = 0;
        while(v0 < arg12) {
            FontInfo v4 = arg11[v0];
            Object v5 = v9_3.get(v4.getUri());
            if(v5 == null) {
            }
            else if(!TypefaceCompatApi26Impl.addFontFromBuffer(v10_2, ((ByteBuffer)v5), v4.getTtcIndex(), v4.getWeight(), v4.isItalic())) {
                TypefaceCompatApi26Impl.abortCreation(v10_2);
                return v2;
            }
            else {
                v3 = 1;
            }

            ++v0;
        }

        if(v3 == 0) {
            TypefaceCompatApi26Impl.abortCreation(v10_2);
            return v2;
        }

        if(!TypefaceCompatApi26Impl.freeze(v10_2)) {
            return v2;
        }

        return TypefaceCompatApi26Impl.createFromFamiliesWithDefault(v10_2);
    }

    @Nullable public Typeface createFromResourcesFontFile(Context arg7, Resources arg8, int arg9, String arg10, int arg11) {
        if(!TypefaceCompatApi26Impl.isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(arg7, arg8, arg9, arg10, arg11);
        }

        Object v8 = TypefaceCompatApi26Impl.newFamily();
        Typeface v9 = null;
        if(!TypefaceCompatApi26Impl.addFontFromAssetManager(arg7, v8, arg10, 0, -1, -1)) {
            TypefaceCompatApi26Impl.abortCreation(v8);
            return v9;
        }

        if(!TypefaceCompatApi26Impl.freeze(v8)) {
            return v9;
        }

        return TypefaceCompatApi26Impl.createFromFamiliesWithDefault(v8);
    }

    private static boolean freeze(Object arg2) {
        try {
            return TypefaceCompatApi26Impl.sFreeze.invoke(arg2).booleanValue();
        }
        catch(InvocationTargetException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if(TypefaceCompatApi26Impl.sAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }

        boolean v0 = TypefaceCompatApi26Impl.sAddFontFromAssetManager != null ? true : false;
        return v0;
    }

    private static Object newFamily() {
        try {
            return TypefaceCompatApi26Impl.sFontFamilyCtor.newInstance();
        }
        catch(InvocationTargetException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }
}

