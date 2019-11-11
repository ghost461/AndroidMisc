package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FontsContractCompat {
    final class android.support.v4.provider.FontsContractCompat$5 implements Comparator {
        android.support.v4.provider.FontsContractCompat$5() {
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((byte[])arg1), ((byte[])arg2));
        }

        public int compare(byte[] arg5, byte[] arg6) {
            if(arg5.length != arg6.length) {
                return arg5.length - arg6.length;
            }

            int v1;
            for(v1 = 0; v1 < arg5.length; ++v1) {
                if(arg5[v1] != arg6[v1]) {
                    return arg5[v1] - arg6[v1];
                }
            }

            return 0;
        }
    }

    public final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

        public Columns() {
            super();
        }
    }

    public class FontFamilyResult {
        @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @interface FontResultStatus {
        }

        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public FontFamilyResult(int arg1, @Nullable FontInfo[] arg2) {
            super();
            this.mStatusCode = arg1;
            this.mFonts = arg2;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }
    }

    public class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public FontInfo(@NonNull Uri arg1, @IntRange(from=0) int arg2, @IntRange(from=1, to=1000) int arg3, boolean arg4, int arg5) {
            super();
            this.mUri = Preconditions.checkNotNull(arg1);
            this.mTtcIndex = arg2;
            this.mWeight = arg3;
            this.mItalic = arg4;
            this.mResultCode = arg5;
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        @IntRange(from=0) public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @NonNull public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from=1, to=1000) public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public class FontRequestCallback {
        @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @interface FontRequestFailReason {
        }

        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        public FontRequestCallback() {
            super();
        }

        public void onTypefaceRequestFailed(int arg1) {
        }

        public void onTypefaceRetrieved(Typeface arg1) {
        }
    }

    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread;
    private static final Comparator sByteArrayComparator;
    private static final Object sLock;
    @GuardedBy(value="sLock") private static final SimpleArrayMap sPendingReplies;
    private static final LruCache sTypefaceCache;

    static {
        FontsContractCompat.sTypefaceCache = new LruCache(16);
        FontsContractCompat.sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
        FontsContractCompat.sLock = new Object();
        FontsContractCompat.sPendingReplies = new SimpleArrayMap();
        FontsContractCompat.sByteArrayComparator = new android.support.v4.provider.FontsContractCompat$5();
    }

    private FontsContractCompat() {
        super();
    }

    static Typeface access$000(Context arg0, FontRequest arg1, int arg2) {
        return FontsContractCompat.getFontInternal(arg0, arg1, arg2);
    }

    static LruCache access$100() {
        return FontsContractCompat.sTypefaceCache;
    }

    static Object access$200() {
        return FontsContractCompat.sLock;
    }

    static SimpleArrayMap access$300() {
        return FontsContractCompat.sPendingReplies;
    }

    public static Typeface buildTypeface(@NonNull Context arg1, @Nullable CancellationSignal arg2, @NonNull FontInfo[] arg3) {
        return TypefaceCompat.createFromFontInfo(arg1, arg2, arg3, 0);
    }

    private static List convertToByteArrayList(Signature[] arg3) {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            ((List)v0).add(arg3[v1].toByteArray());
        }

        return ((List)v0);
    }

    private static boolean equalsByteArrayList(List arg4, List arg5) {
        if(arg4.size() != arg5.size()) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            if(!Arrays.equals(arg4.get(v0), arg5.get(v0))) {
                return 0;
            }
        }

        return 1;
    }

    @NonNull public static FontFamilyResult fetchFonts(@NonNull Context arg2, @Nullable CancellationSignal arg3, @NonNull FontRequest arg4) throws PackageManager$NameNotFoundException {
        ProviderInfo v0 = FontsContractCompat.getProvider(arg2.getPackageManager(), arg4, arg2.getResources());
        if(v0 == null) {
            return new FontFamilyResult(1, null);
        }

        return new FontFamilyResult(0, FontsContractCompat.getFontFromProvider(arg2, arg4, v0.authority, arg3));
    }

    private static List getCertificates(FontRequest arg1, Resources arg2) {
        if(arg1.getCertificates() != null) {
            return arg1.getCertificates();
        }

        return FontResourcesParserCompat.readCerts(arg2, arg1.getCertificatesArrayResId());
    }

    @NonNull @VisibleForTesting static FontInfo[] getFontFromProvider(Context arg22, FontRequest arg23, String arg24, CancellationSignal arg25) {
        ArrayList v4_2;
        Cursor v4_1;
        String[] v12_1;
        ContentResolver v4;
        ArrayList v2 = new ArrayList();
        Uri v3 = new Uri$Builder().scheme("content").authority(arg24).build();
        Uri v1 = new Uri$Builder().scheme("content").authority(arg24).appendPath("file").build();
        Cursor v11 = null;
        try {
            int v6 = 6;
            int v7 = 5;
            int v8 = 4;
            int v9 = 3;
            int v10 = 2;
            int v12 = 7;
            if(Build$VERSION.SDK_INT > 16) {
                v4 = arg22.getContentResolver();
                v12_1 = new String[v12];
                v12_1[0] = "_id";
                v12_1[1] = "file_id";
                v12_1[v10] = "font_ttc_index";
                v12_1[v9] = "font_variation_settings";
                v12_1[v8] = "font_weight";
                v12_1[v7] = "font_italic";
                v12_1[v6] = "result_code";
                v4_1 = v4.query(v3, v12_1, "query = ?", new String[]{arg23.getQuery()}, null, arg25);
            }
            else {
                v4 = arg22.getContentResolver();
                v12_1 = new String[v12];
                v12_1[0] = "_id";
                v12_1[1] = "file_id";
                v12_1[v10] = "font_ttc_index";
                v12_1[v9] = "font_variation_settings";
                v12_1[v8] = "font_weight";
                v12_1[v7] = "font_italic";
                v12_1[v6] = "result_code";
                v4_1 = v4.query(v3, v12_1, "query = ?", new String[]{arg23.getQuery()}, null);
            }

            v11 = v4_1;
            if(v11 != null && v11.getCount() > 0) {
                int v2_1 = v11.getColumnIndex("result_code");
                v4_2 = new ArrayList();
                int v5 = v11.getColumnIndex("_id");
                v6 = v11.getColumnIndex("file_id");
                v7 = v11.getColumnIndex("font_ttc_index");
                v8 = v11.getColumnIndex("font_weight");
                v9 = v11.getColumnIndex("font_italic");
                while(true) {
                    if(v11.moveToNext()) {
                        v10 = -1;
                        int v20 = v2_1 != v10 ? v11.getInt(v2_1) : 0;
                        int v17 = v7 != v10 ? v11.getInt(v7) : 0;
                        Uri v12_2 = v6 == v10 ? ContentUris.withAppendedId(v3, v11.getLong(v5)) : ContentUris.withAppendedId(v1, v11.getLong(v6));
                        Uri v16 = v12_2;
                        int v18 = v8 != v10 ? v11.getInt(v8) : 400;
                        boolean v19 = v9 == v10 || v11.getInt(v9) != 1 ? false : true;
                        v4_2.add(new FontInfo(v16, v17, v18, v19, v20));
                        continue;
                    }
                    else {
                        goto label_138;
                    }
                }
            }

            goto label_139;
        }
        catch(Throwable v0) {
            goto label_146;
        }

    label_138:
        v2 = v4_2;
        goto label_139;
    label_146:
        Throwable v1_1 = v0;
        if(v11 != null) {
            v11.close();
        }

        throw v1_1;
    label_139:
        if(v11 != null) {
            v11.close();
        }

        return v2.toArray(new FontInfo[0]);
    }

    private static Typeface getFontInternal(Context arg2, FontRequest arg3, int arg4) {
        FontFamilyResult v3;
        CancellationSignal v0 = null;
        try {
            v3 = FontsContractCompat.fetchFonts(arg2, v0, arg3);
        }
        catch(PackageManager$NameNotFoundException ) {
            return ((Typeface)v0);
        }

        if(v3.getStatusCode() == 0) {
            return TypefaceCompat.createFromFontInfo(arg2, v0, v3.getFonts(), arg4);
        }

        return ((Typeface)v0);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static Typeface getFontSync(Context arg2, FontRequest arg3, @Nullable TextView arg4, int arg5, int arg6, int arg7) {
        String v0_1 = arg3.getIdentifier() + "-" + arg7;
        Object v1 = FontsContractCompat.sTypefaceCache.get(v0_1);
        if(v1 != null) {
            return ((Typeface)v1);
        }

        arg5 = arg5 == 0 ? 1 : 0;
        if(arg5 != 0 && arg6 == -1) {
            return FontsContractCompat.getFontInternal(arg2, arg3, arg7);
        }

        android.support.v4.provider.FontsContractCompat$1 v1_1 = new Callable(arg2, arg3, arg7, v0_1) {
            public Typeface call() throws Exception {
                Typeface v0 = FontsContractCompat.getFontInternal(this.val$context, this.val$request, this.val$style);
                if(v0 != null) {
                    FontsContractCompat.sTypefaceCache.put(this.val$id, v0);
                }

                return v0;
            }

            public Object call() throws Exception {
                return this.call();
            }
        };
        Typeface v2 = null;
        if(arg5 != 0) {
            try {
                return FontsContractCompat.sBackgroundThread.postAndWait(((Callable)v1_1), arg6);
            }
            catch(InterruptedException ) {
                return v2;
            }
        }

        android.support.v4.provider.FontsContractCompat$2 v5 = new ReplyCallback(new WeakReference(arg4), arg4, arg7) {
            public void onReply(Typeface arg3) {
                if(this.val$textViewWeak.get() != null) {
                    this.val$targetView.setTypeface(arg3, this.val$style);
                }
            }

            public void onReply(Object arg1) {
                this.onReply(((Typeface)arg1));
            }
        };
        Object v3 = FontsContractCompat.sLock;
        __monitor_enter(v3);
        try {
            if(FontsContractCompat.sPendingReplies.containsKey(v0_1)) {
                FontsContractCompat.sPendingReplies.get(v0_1).add(v5);
                __monitor_exit(v3);
                return v2;
            }

            ArrayList v4 = new ArrayList();
            v4.add(v5);
            FontsContractCompat.sPendingReplies.put(v0_1, v4);
            __monitor_exit(v3);
        }
        catch(Throwable v2_1) {
            try {
            label_55:
                __monitor_exit(v3);
            }
            catch(Throwable v2_1) {
                goto label_55;
            }

            throw v2_1;
        }

        FontsContractCompat.sBackgroundThread.postAndReply(((Callable)v1_1), new ReplyCallback(v0_1) {
            public void onReply(Typeface arg5) {
                int v0_1;
                Object v1;
                Object v0 = FontsContractCompat.sLock;
                __monitor_enter(v0);
                try {
                    v1 = FontsContractCompat.sPendingReplies.get(this.val$id);
                    FontsContractCompat.sPendingReplies.remove(this.val$id);
                    __monitor_exit(v0);
                    v0_1 = 0;
                }
                catch(Throwable v5) {
                    try {
                    label_18:
                        __monitor_exit(v0_1);
                    }
                    catch(Throwable v5) {
                        goto label_18;
                    }

                    throw v5;
                }

                while(v0_1 < ((ArrayList)v1).size()) {
                    ((ArrayList)v1).get(v0_1).onReply(arg5);
                    ++v0_1;
                }
            }

            public void onReply(Object arg1) {
                this.onReply(((Typeface)arg1));
            }
        });
        return v2;
    }

    @Nullable @RestrictTo(value={Scope.LIBRARY_GROUP}) @VisibleForTesting public static ProviderInfo getProvider(@NonNull PackageManager arg5, @NonNull FontRequest arg6, @Nullable Resources arg7) throws PackageManager$NameNotFoundException {
        String v0 = arg6.getProviderAuthority();
        int v1 = 0;
        ProviderInfo v2 = arg5.resolveContentProvider(v0, 0);
        if(v2 == null) {
            StringBuilder v6 = new StringBuilder();
            v6.append("No package found for authority: ");
            v6.append(v0);
            throw new PackageManager$NameNotFoundException(v6.toString());
        }

        if(!v2.packageName.equals(arg6.getProviderPackage())) {
            StringBuilder v7 = new StringBuilder();
            v7.append("Found content provider ");
            v7.append(v0);
            v7.append(", but package was not ");
            v7.append(arg6.getProviderPackage());
            throw new PackageManager$NameNotFoundException(v7.toString());
        }

        List v5 = FontsContractCompat.convertToByteArrayList(arg5.getPackageInfo(v2.packageName, 0x40).signatures);
        Collections.sort(v5, FontsContractCompat.sByteArrayComparator);
        List v6_1 = FontsContractCompat.getCertificates(arg6, arg7);
        while(v1 < v6_1.size()) {
            ArrayList v7_1 = new ArrayList(v6_1.get(v1));
            Collections.sort(((List)v7_1), FontsContractCompat.sByteArrayComparator);
            if(FontsContractCompat.equalsByteArrayList(v5, ((List)v7_1))) {
                return v2;
            }

            ++v1;
        }

        return null;
    }

    @RequiresApi(value=19) @RestrictTo(value={Scope.LIBRARY_GROUP}) public static Map prepareFontData(Context arg5, FontInfo[] arg6, CancellationSignal arg7) {
        HashMap v0 = new HashMap();
        int v1 = arg6.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            FontInfo v3 = arg6[v2];
            if(v3.getResultCode() != 0) {
            }
            else {
                Uri v3_1 = v3.getUri();
                if(v0.containsKey(v3_1)) {
                }
                else {
                    v0.put(v3_1, TypefaceCompatUtil.mmap(arg5, arg7, v3_1));
                }
            }
        }

        return Collections.unmodifiableMap(((Map)v0));
    }

    public static void requestFont(@NonNull Context arg2, @NonNull FontRequest arg3, @NonNull FontRequestCallback arg4, @NonNull Handler arg5) {
        arg5.post(new Runnable(arg2, arg3, new Handler(), arg4) {
            public void run() {
                FontFamilyResult v0;
                CancellationSignal v2;
                try {
                    v2 = null;
                    v0 = FontsContractCompat.fetchFonts(this.val$context, v2, this.val$request);
                }
                catch(PackageManager$NameNotFoundException ) {
                    this.val$callerThreadHandler.post(new Runnable() {
                        public void run() {
                            android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(-1);
                        }
                    });
                    return;
                }

                if(v0.getStatusCode() == 0) {
                    goto label_23;
                }

                switch(v0.getStatusCode()) {
                    case 1: {
                        goto label_18;
                    }
                    case 2: {
                        goto label_13;
                    }
                }

                this.val$callerThreadHandler.post(new Runnable() {
                    public void run() {
                        android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(-3);
                    }
                });
                return;
            label_18:
                this.val$callerThreadHandler.post(new Runnable() {
                    public void run() {
                        android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(-2);
                    }
                });
                return;
            label_13:
                this.val$callerThreadHandler.post(new Runnable() {
                    public void run() {
                        android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(-3);
                    }
                });
                return;
            label_23:
                FontInfo[] v0_1 = v0.getFonts();
                if(v0_1 != null) {
                    if(v0_1.length == 0) {
                    }
                    else {
                        int v1 = v0_1.length;
                        int v3 = 0;
                        while(true) {
                            if(v3 < v1) {
                                FontInfo v4 = v0_1[v3];
                                if(v4.getResultCode() != 0) {
                                    int v0_2 = v4.getResultCode();
                                    if(v0_2 < 0) {
                                        this.val$callerThreadHandler.post(new Runnable() {
                                            public void run() {
                                                android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(-3);
                                            }
                                        });
                                    }
                                    else {
                                        this.val$callerThreadHandler.post(new Runnable(v0_2) {
                                            public void run() {
                                                android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(this.val$resultCode);
                                            }
                                        });
                                    }

                                    return;
                                }
                                else {
                                    ++v3;
                                    continue;
                                }
                            }
                            else {
                                break;
                            }

                            goto label_61;
                        }

                        Typeface v0_3 = FontsContractCompat.buildTypeface(this.val$context, v2, v0_1);
                        if(v0_3 == null) {
                            this.val$callerThreadHandler.post(new Runnable() {
                                public void run() {
                                    android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        }

                        this.val$callerThreadHandler.post(new Runnable(v0_3) {
                            public void run() {
                                android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRetrieved(this.val$typeface);
                            }
                        });
                        return;
                    }
                }

            label_61:
                this.val$callerThreadHandler.post(new Runnable() {
                    public void run() {
                        android.support.v4.provider.FontsContractCompat$4.this.val$callback.onTypefaceRequestFailed(1);
                    }
                });
            }
        });
    }
}

