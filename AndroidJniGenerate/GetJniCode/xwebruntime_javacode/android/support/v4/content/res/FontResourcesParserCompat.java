package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.compat.R$styleable;
import android.support.v4.provider.FontRequest;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class FontResourcesParserCompat {
    public interface FamilyResourceEntry {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface FetchStrategy {
    }

    public final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        @NonNull private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(@NonNull FontFileResourceEntry[] arg1) {
            super();
            this.mEntries = arg1;
        }

        @NonNull public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public final class FontFileResourceEntry {
        @NonNull private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mWeight;

        public FontFileResourceEntry(@NonNull String arg1, int arg2, boolean arg3, int arg4) {
            super();
            this.mFileName = arg1;
            this.mWeight = arg2;
            this.mItalic = arg3;
            this.mResourceId = arg4;
        }

        @NonNull public String getFileName() {
            return this.mFileName;
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public final class ProviderResourceEntry implements FamilyResourceEntry {
        @NonNull private final FontRequest mRequest;
        private final int mStrategy;
        private final int mTimeoutMs;

        public ProviderResourceEntry(@NonNull FontRequest arg1, int arg2, int arg3) {
            super();
            this.mRequest = arg1;
            this.mStrategy = arg2;
            this.mTimeoutMs = arg3;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        @NonNull public FontRequest getRequest() {
            return this.mRequest;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    public FontResourcesParserCompat() {
        super();
    }

    @Nullable public static FamilyResourceEntry parse(XmlPullParser arg3, Resources arg4) throws XmlPullParserException, IOException {
        while(true) {
            int v0 = arg3.next();
            int v1 = 2;
            if(v0 != v1 && v0 != 1) {
                continue;
            }

            break;
        }

        if(v0 != v1) {
            throw new XmlPullParserException("No start tag found");
        }

        return FontResourcesParserCompat.readFamilies(arg3, arg4);
    }

    public static List readCerts(Resources arg4, @ArrayRes int arg5) {
        List v0_1;
        ArrayList v0 = null;
        if(arg5 != 0) {
            TypedArray v1 = arg4.obtainTypedArray(arg5);
            if(v1.length() > 0) {
                v0 = new ArrayList();
                int v3 = v1.getResourceId(0, 0) != 0 ? 1 : 0;
                if(v3 != 0) {
                    for(arg5 = 0; true; ++arg5) {
                        if(arg5 >= v1.length()) {
                            goto label_26;
                        }

                        ((List)v0).add(FontResourcesParserCompat.toByteArrayList(arg4.getStringArray(v1.getResourceId(arg5, 0))));
                    }
                }

                ((List)v0).add(FontResourcesParserCompat.toByteArrayList(arg4.getStringArray(arg5)));
            }

        label_26:
            v1.recycle();
        }

        if(v0 != null) {
        }
        else {
            v0_1 = Collections.emptyList();
        }

        return v0_1;
    }

    @Nullable private static FamilyResourceEntry readFamilies(XmlPullParser arg3, Resources arg4) throws XmlPullParserException, IOException {
        String v1 = null;
        arg3.require(2, v1, "font-family");
        if(arg3.getName().equals("font-family")) {
            return FontResourcesParserCompat.readFamily(arg3, arg4);
        }

        FontResourcesParserCompat.skip(arg3);
        return ((FamilyResourceEntry)v1);
    }

    @Nullable private static FamilyResourceEntry readFamily(XmlPullParser arg8, Resources arg9) throws XmlPullParserException, IOException {
        TypedArray v0 = arg9.obtainAttributes(Xml.asAttributeSet(arg8), styleable.FontFamily);
        String v1 = v0.getString(styleable.FontFamily_fontProviderAuthority);
        String v2 = v0.getString(styleable.FontFamily_fontProviderPackage);
        String v3 = v0.getString(styleable.FontFamily_fontProviderQuery);
        int v4 = v0.getResourceId(styleable.FontFamily_fontProviderCerts, 0);
        int v5 = v0.getInteger(styleable.FontFamily_fontProviderFetchStrategy, 1);
        int v6 = v0.getInteger(styleable.FontFamily_fontProviderFetchTimeout, 500);
        v0.recycle();
        int v0_1 = 3;
        if(v1 != null && v2 != null && v3 != null) {
            while(arg8.next() != v0_1) {
                FontResourcesParserCompat.skip(arg8);
            }

            return new ProviderResourceEntry(new FontRequest(v1, v2, v3, FontResourcesParserCompat.readCerts(arg9, v4)), v5, v6);
        }

        ArrayList v1_1 = new ArrayList();
        while(arg8.next() != v0_1) {
            if(arg8.getEventType() != 2) {
                continue;
            }

            if(arg8.getName().equals("font")) {
                ((List)v1_1).add(FontResourcesParserCompat.readFont(arg8, arg9));
                continue;
            }

            FontResourcesParserCompat.skip(arg8);
        }

        if(((List)v1_1).isEmpty()) {
            return null;
        }

        return new FontFamilyFilesResourceEntry(((List)v1_1).toArray(new FontFileResourceEntry[((List)v1_1).size()]));
    }

    private static FontFileResourceEntry readFont(XmlPullParser arg5, Resources arg6) throws XmlPullParserException, IOException {
        TypedArray v6 = arg6.obtainAttributes(Xml.asAttributeSet(arg5), styleable.FontFamilyFont);
        int v0 = v6.getInt(styleable.FontFamilyFont_fontWeight, 400);
        boolean v3 = true;
        if(1 == v6.getInt(styleable.FontFamilyFont_fontStyle, 0)) {
        }
        else {
            v3 = false;
        }

        int v1 = v6.getResourceId(styleable.FontFamilyFont_font, 0);
        String v2 = v6.getString(styleable.FontFamilyFont_font);
        v6.recycle();
        while(arg5.next() != 3) {
            FontResourcesParserCompat.skip(arg5);
        }

        return new FontFileResourceEntry(v2, v0, v3, v1);
    }

    private static void skip(XmlPullParser arg2) throws XmlPullParserException, IOException {
        int v0;
        for(v0 = 1; v0 > 0; ++v0) {
            switch(arg2.next()) {
                case 2: {
                    goto label_7;
                }
                case 3: {
                    goto label_5;
                }
            }

            continue;
        label_5:
            --v0;
            continue;
        label_7:
        }
    }

    private static List toByteArrayList(String[] arg5) {
        ArrayList v0 = new ArrayList();
        int v1 = arg5.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            ((List)v0).add(Base64.decode(arg5[v3], 0));
        }

        return ((List)v0);
    }
}

