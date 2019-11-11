package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat$ProviderResourceEntry;
import android.support.v4.provider.FontsContractCompat$FontInfo;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.LruCache;
import android.widget.TextView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class TypefaceCompat {
    interface TypefaceCompatImpl {
        Typeface createFromFontFamilyFilesResourceEntry(Context arg1, FontFamilyFilesResourceEntry arg2, Resources arg3, int arg4);

        Typeface createFromFontInfo(Context arg1, @Nullable CancellationSignal arg2, @NonNull FontInfo[] arg3, int arg4);

        Typeface createFromResourcesFontFile(Context arg1, Resources arg2, int arg3, String arg4, int arg5);
    }

    private static final String TAG = "TypefaceCompat";
    private static final LruCache sTypefaceCache;
    private static final TypefaceCompatImpl sTypefaceCompatImpl;

    static {
        if(Build$VERSION.SDK_INT >= 26) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        }
        else {
            if(Build$VERSION.SDK_INT >= 24 && (TypefaceCompatApi24Impl.isUsable())) {
                TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
                goto label_26;
            }

            if(Build$VERSION.SDK_INT >= 21) {
                TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
                goto label_26;
            }

            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
        }

    label_26:
        TypefaceCompat.sTypefaceCache = new LruCache(16);
    }

    private TypefaceCompat() {
        super();
    }

    public static Typeface createFromFontInfo(Context arg1, @Nullable CancellationSignal arg2, @NonNull FontInfo[] arg3, int arg4) {
        return TypefaceCompat.sTypefaceCompatImpl.createFromFontInfo(arg1, arg2, arg3, arg4);
    }

    public static Typeface createFromResourcesFamilyXml(Context arg6, FamilyResourceEntry arg7, Resources arg8, int arg9, int arg10, @Nullable TextView arg11) {
        Typeface v6 = (arg7 instanceof ProviderResourceEntry) ? FontsContractCompat.getFontSync(arg6, ((ProviderResourceEntry)arg7).getRequest(), arg11, ((ProviderResourceEntry)arg7).getFetchStrategy(), ((ProviderResourceEntry)arg7).getTimeout(), arg10) : TypefaceCompat.sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(arg6, ((FontFamilyFilesResourceEntry)arg7), arg8, arg10);
        if(v6 != null) {
            TypefaceCompat.sTypefaceCache.put(TypefaceCompat.createResourceUid(arg8, arg9, arg10), v6);
        }

        return v6;
    }

    @Nullable public static Typeface createFromResourcesFontFile(Context arg6, Resources arg7, int arg8, String arg9, int arg10) {
        Typeface v6 = TypefaceCompat.sTypefaceCompatImpl.createFromResourcesFontFile(arg6, arg7, arg8, arg9, arg10);
        if(v6 != null) {
            TypefaceCompat.sTypefaceCache.put(TypefaceCompat.createResourceUid(arg7, arg8, arg10), v6);
        }

        return v6;
    }

    private static String createResourceUid(Resources arg1, int arg2, int arg3) {
        return arg1.getResourcePackageName(arg2) + "-" + arg2 + "-" + arg3;
    }

    public static Typeface findFromCache(Resources arg1, int arg2, int arg3) {
        return TypefaceCompat.sTypefaceCache.get(TypefaceCompat.createResourceUid(arg1, arg2, arg3));
    }
}

