package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat$FontInfo;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(value=14) @RestrictTo(value={Scope.LIBRARY_GROUP}) class TypefaceCompatBaseImpl implements TypefaceCompatImpl {
    interface StyleExtractor {
        int getWeight(Object arg1);

        boolean isItalic(Object arg1);
    }

    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";

    TypefaceCompatBaseImpl() {
        super();
    }

    @Nullable public Typeface createFromFontFamilyFilesResourceEntry(Context arg2, FontFamilyFilesResourceEntry arg3, Resources arg4, int arg5) {
        FontFileResourceEntry v3 = this.findBestEntry(arg3, arg5);
        if(v3 == null) {
            return null;
        }

        return TypefaceCompat.createFromResourcesFontFile(arg2, arg4, v3.getResourceId(), v3.getFileName(), arg5);
    }

    public Typeface createFromFontInfo(Context arg3, @Nullable CancellationSignal arg4, @NonNull FontInfo[] arg5, int arg6) {
        InputStream v0_1;
        Typeface v3_1;
        InputStream v4_1;
        Typeface v0 = null;
        if(arg5.length < 1) {
            return v0;
        }

        FontInfo v4 = this.findBestInfo(arg5, arg6);
        try {
            v4_1 = arg3.getContentResolver().openInputStream(v4.getUri());
        }
        catch(Throwable v3) {
            goto label_16;
        }
        catch(IOException ) {
            Closeable v4_2 = ((Closeable)v0);
            goto label_19;
        }

        try {
            v3_1 = this.createFromInputStream(arg3, v4_1);
            goto label_10;
        }
        catch(Throwable v3) {
            v0_1 = v4_1;
        }
        catch(IOException ) {
        label_19:
            TypefaceCompatUtil.closeQuietly(((Closeable)v4_1));
            return v0;
        }

    label_16:
        TypefaceCompatUtil.closeQuietly(((Closeable)v0_1));
        throw v3;
    label_10:
        TypefaceCompatUtil.closeQuietly(((Closeable)v4_1));
        return v3_1;
    }

    protected Typeface createFromInputStream(Context arg2, InputStream arg3) {
        Typeface v3_1;
        File v2 = TypefaceCompatUtil.getTempFile(arg2);
        Typeface v0 = null;
        if(v2 == null) {
            return v0;
        }

        try {
            if(TypefaceCompatUtil.copyToFile(v2, arg3)) {
                goto label_8;
            }
        }
        catch(Throwable v3) {
            goto label_13;
        }
        catch(RuntimeException ) {
            goto label_15;
        }

        v2.delete();
        return v0;
        try {
        label_8:
            v3_1 = Typeface.createFromFile(v2.getPath());
        }
        catch(Throwable v3) {
        label_13:
            v2.delete();
            throw v3;
        }
        catch(RuntimeException ) {
        label_15:
            v2.delete();
            return v0;
        }

        v2.delete();
        return v3_1;
    }

    @Nullable public Typeface createFromResourcesFontFile(Context arg1, Resources arg2, int arg3, String arg4, int arg5) {
        Typeface v2_1;
        File v1 = TypefaceCompatUtil.getTempFile(arg1);
        Typeface v4 = null;
        if(v1 == null) {
            return v4;
        }

        try {
            if(TypefaceCompatUtil.copyToFile(v1, arg2, arg3)) {
                goto label_8;
            }
        }
        catch(Throwable v2) {
            goto label_13;
        }
        catch(RuntimeException ) {
            goto label_15;
        }

        v1.delete();
        return v4;
        try {
        label_8:
            v2_1 = Typeface.createFromFile(v1.getPath());
        }
        catch(Throwable v2) {
        label_13:
            v1.delete();
            throw v2;
        }
        catch(RuntimeException ) {
        label_15:
            v1.delete();
            return v4;
        }

        v1.delete();
        return v2_1;
    }

    private FontFileResourceEntry findBestEntry(FontFamilyFilesResourceEntry arg2, int arg3) {
        return TypefaceCompatBaseImpl.findBestFont(arg2.getEntries(), arg3, new StyleExtractor() {
            public int getWeight(FontFileResourceEntry arg1) {
                return arg1.getWeight();
            }

            public int getWeight(Object arg1) {
                return this.getWeight(((FontFileResourceEntry)arg1));
            }

            public boolean isItalic(FontFileResourceEntry arg1) {
                return arg1.isItalic();
            }

            public boolean isItalic(Object arg1) {
                return this.isItalic(((FontFileResourceEntry)arg1));
            }
        });
    }

    private static Object findBestFont(Object[] arg10, int arg11, StyleExtractor arg12) {
        int v0 = (arg11 & 1) == 0 ? 400 : 700;
        boolean v11 = (arg11 & 2) != 0 ? true : false;
        int v5 = arg10.length;
        Object v4 = null;
        int v3 = 0;
        int v6 = 0x7FFFFFFF;
        while(v3 < v5) {
            Object v7 = arg10[v3];
            int v8 = Math.abs(arg12.getWeight(v7) - v0) * 2;
            int v9 = arg12.isItalic(v7) == v11 ? 0 : 1;
            v8 += v9;
            if(v4 == null || v6 > v8) {
                v4 = v7;
                v6 = v8;
            }

            ++v3;
        }

        return v4;
    }

    protected FontInfo findBestInfo(FontInfo[] arg2, int arg3) {
        return TypefaceCompatBaseImpl.findBestFont(((Object[])arg2), arg3, new StyleExtractor() {
            public int getWeight(FontInfo arg1) {
                return arg1.getWeight();
            }

            public int getWeight(Object arg1) {
                return this.getWeight(((FontInfo)arg1));
            }

            public boolean isItalic(FontInfo arg1) {
                return arg1.isItalic();
            }

            public boolean isItalic(Object arg1) {
                return this.isItalic(((FontInfo)arg1));
            }
        });
    }
}

