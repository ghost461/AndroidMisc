package org.chromium.components.variations.firstrun;

import android.content.SharedPreferences;
import android.os.SystemClock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.metrics.CachedMetrics$SparseHistogramSample;
import org.chromium.base.metrics.CachedMetrics$TimesHistogramSample;

public class VariationsSeedFetcher {
    class org.chromium.components.variations.firstrun.VariationsSeedFetcher$1 {
        static {
            org.chromium.components.variations.firstrun.VariationsSeedFetcher$1.$SwitchMap$org$chromium$components$variations$firstrun$VariationsSeedFetcher$VariationsPlatform = new int[VariationsPlatform.values().length];
            try {
                org.chromium.components.variations.firstrun.VariationsSeedFetcher$1.$SwitchMap$org$chromium$components$variations$firstrun$VariationsSeedFetcher$VariationsPlatform[VariationsPlatform.ANDROID.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.chromium.components.variations.firstrun.VariationsSeedFetcher$1.$SwitchMap$org$chromium$components$variations$firstrun$VariationsSeedFetcher$VariationsPlatform[VariationsPlatform.ANDROID_WEBVIEW.ordinal()] = 2;
                    return;
                }
                catch(NoSuchFieldError ) {
                    return;
                }
            }
        }
    }

    public class SeedInfo {
        public String country;
        public String date;
        public boolean isGzipCompressed;
        public byte[] seedData;
        public String signature;

        public SeedInfo() {
            super();
        }

        public Date parseDate() throws ParseException {
            return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).parse(this.date);
        }
    }

    public enum VariationsPlatform {
        public static final enum VariationsPlatform ANDROID;
        public static final enum VariationsPlatform ANDROID_WEBVIEW;

        static {
            VariationsPlatform.ANDROID = new VariationsPlatform("ANDROID", 0);
            VariationsPlatform.ANDROID_WEBVIEW = new VariationsPlatform("ANDROID_WEBVIEW", 1);
            VariationsPlatform.$VALUES = new VariationsPlatform[]{VariationsPlatform.ANDROID, VariationsPlatform.ANDROID_WEBVIEW};
        }

        private VariationsPlatform(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static VariationsPlatform valueOf(String arg1) {
            return Enum.valueOf(VariationsPlatform.class, arg1);
        }

        public static VariationsPlatform[] values() {
            return VariationsPlatform.$VALUES.clone();
        }
    }

    private static final int BUFFER_SIZE = 0x1000;
    private static final int READ_TIMEOUT = 3000;
    private static final int REQUEST_TIMEOUT = 1000;
    private static final int SEED_FETCH_RESULT_IOEXCEPTION = -1;
    private static final int SEED_FETCH_RESULT_TIMEOUT = -2;
    private static final int SEED_FETCH_RESULT_UNKNOWN_HOST_EXCEPTION = -3;
    private static final String TAG = "VariationsSeedFetch";
    @VisibleForTesting static final String VARIATIONS_INITIALIZED_PREF = "variations_initialized";
    private static final String VARIATIONS_SERVER_URL = "https://clientservices.googleapis.com/chrome-variations/seed?osname=";
    private static VariationsSeedFetcher sInstance;
    private static final Object sLock;

    static {
        VariationsSeedFetcher.sLock = new Object();
    }

    @VisibleForTesting VariationsSeedFetcher() {
        super();
    }

    public static byte[] convertInputStreamToByteArray(InputStream arg4) throws IOException {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        byte[] v1 = new byte[0x1000];
        while(true) {
            int v2 = arg4.read(v1);
            if(v2 == -1) {
                break;
            }

            v0.write(v1, 0, v2);
        }

        return v0.toByteArray();
    }

    public SeedInfo downloadContent(VariationsPlatform arg8, String arg9, String arg10, String arg11) throws SocketTimeoutException, UnknownHostException, IOException {
        SeedInfo v9_5;
        int v9_4;
        HttpURLConnection v8;
        long v3;
        HttpURLConnection v2 = null;
        try {
            v3 = SystemClock.elapsedRealtime();
            v8 = this.getServerConnection(arg8, arg9, arg10, arg11);
            v9_4 = 3000;
            goto label_6;
        }
        catch(Throwable v9) {
        }
        catch(IOException v9_1) {
            goto label_74;
            try {
            label_6:
                v8.setReadTimeout(v9_4);
                v8.setConnectTimeout(1000);
                v8.setDoInput(true);
                v8.setRequestProperty("A-IM", "gzip");
                v8.connect();
                v9_4 = v8.getResponseCode();
                this.recordFetchResultOrCode(v9_4);
                if(v9_4 != 200) {
                    arg9 = "Non-OK response code = " + v9_4;
                    Log.w("VariationsSeedFetch", arg9, new Object[0]);
                    throw new IOException(arg9);
                }

                this.recordSeedConnectTime(SystemClock.elapsedRealtime() - v3);
                v9_5 = new SeedInfo();
                v9_5.seedData = this.getRawSeed(v8);
                v9_5.signature = this.getHeaderFieldOrEmpty(v8, "X-Seed-Signature");
                v9_5.country = this.getHeaderFieldOrEmpty(v8, "X-Country");
                v9_5.date = this.getHeaderFieldOrEmpty(v8, "Date");
                v9_5.isGzipCompressed = this.getHeaderFieldOrEmpty(v8, "IM").equals("gzip");
                this.recordSeedFetchTime(SystemClock.elapsedRealtime() - v3);
                if(v8 == null) {
                    return v9_5;
                }

                goto label_57;
            }
            catch(Throwable v9) {
            }
            catch(IOException v9_1) {
                v2 = v8;
            label_74:
                int v8_1 = -1;
                try {
                    this.recordFetchResultOrCode(v8_1);
                    Log.w("VariationsSeedFetch", "IOException when fetching variations seed.", new Object[]{v9_1});
                    throw v9_1;
                }
                catch(Throwable v9) {
                label_71:
                    v8 = v2;
                }
            }
            catch(UnknownHostException v9_2) {
                v2 = v8;
                try {
                label_83:
                    this.recordFetchResultOrCode(-3);
                    Log.w("VariationsSeedFetch", "UnknownHostException unknown host when fetching variations seed.", new Object[]{v9_2});
                    throw v9_2;
                }
                catch(Throwable v9) {
                    goto label_71;
                }
            }
            catch(SocketTimeoutException v9_3) {
                v2 = v8;
                try {
                label_92:
                    this.recordFetchResultOrCode(-2);
                    Log.w("VariationsSeedFetch", "SocketTimeoutException timeout when fetching variations seed.", new Object[]{v9_3});
                    throw v9_3;
                }
                catch(Throwable v9) {
                    goto label_71;
                }
            }
        }
        catch(UnknownHostException v9_2) {
            goto label_83;
        }
        catch(SocketTimeoutException v9_3) {
            goto label_92;
        }

        if(v8 != null) {
            v8.disconnect();
        }

        throw v9;
    label_57:
        v8.disconnect();
        return v9_5;
    }

    public void fetchSeed(String arg7, String arg8, String arg9) {
        SharedPreferences v1;
        Object v0 = VariationsSeedFetcher.sLock;
        __monitor_enter(v0);
        try {
            v1 = ContextUtils.getAppSharedPreferences();
            if(!v1.getBoolean("variations_initialized", false)) {
                if(VariationsSeedBridge.hasNativePref()) {
                    goto label_9;
                }
                else {
                    goto label_11;
                }
            }

            goto label_32;
        }
        catch(Throwable v7) {
            goto label_35;
        }

    label_9:
        goto label_32;
        try {
        label_11:
            SeedInfo v7_2 = this.downloadContent(VariationsPlatform.ANDROID, arg7, arg8, arg9);
            VariationsSeedBridge.setVariationsFirstRunSeed(v7_2.seedData, v7_2.signature, v7_2.country, v7_2.date, v7_2.isGzipCompressed);
            goto label_26;
        }
        catch(Throwable v7) {
        label_36:
            throw v7;
        }
        catch(IOException v7_1) {
            try {
                Log.e("VariationsSeedFetch", "IOException when fetching variations seed.", new Object[]{v7_1});
            label_26:
                v1.edit().putBoolean("variations_initialized", true).apply();
                __monitor_exit(v0);
                return;
            label_32:
                __monitor_exit(v0);
                return;
            label_35:
                __monitor_exit(v0);
                goto label_36;
            }
            catch(Throwable v7) {
                goto label_35;
            }
        }
    }

    public static VariationsSeedFetcher get() {
        Object v0 = VariationsSeedFetcher.sLock;
        __monitor_enter(v0);
        try {
            if(VariationsSeedFetcher.sInstance == null) {
                VariationsSeedFetcher.sInstance = new VariationsSeedFetcher();
            }

            __monitor_exit(v0);
            return VariationsSeedFetcher.sInstance;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    @VisibleForTesting protected String getConnectionString(VariationsPlatform arg3, String arg4, String arg5, String arg6) {
        String v0 = "https://clientservices.googleapis.com/chrome-variations/seed?osname=";
        switch(org.chromium.components.variations.firstrun.VariationsSeedFetcher$1.$SwitchMap$org$chromium$components$variations$firstrun$VariationsSeedFetcher$VariationsPlatform[arg3.ordinal()]) {
            case 1: {
                v0 = v0 + "android";
                break;
            }
            case 2: {
                v0 = v0 + "android_webview";
                break;
            }
            default: {
                break;
            }
        }

        if(arg4 != null && !arg4.isEmpty()) {
            v0 = v0 + "&restrict=" + arg4;
        }

        if(arg5 != null && !arg5.isEmpty()) {
            v0 = v0 + "&milestone=" + arg5;
        }

        if(arg6 != null && !arg6.isEmpty()) {
            v0 = v0 + "&channel=" + arg6;
        }

        return v0;
    }

    private String getHeaderFieldOrEmpty(HttpURLConnection arg1, String arg2) {
        String v1 = arg1.getHeaderField(arg2);
        if(v1 == null) {
            return "";
        }

        return v1.trim();
    }

    private byte[] getRawSeed(HttpURLConnection arg2) throws IOException {
        byte[] v0_1;
        InputStream v2;
        try {
            v2 = arg2.getInputStream();
        }
        catch(Throwable v0) {
            v2 = null;
            goto label_9;
        }

        try {
            v0_1 = VariationsSeedFetcher.convertInputStreamToByteArray(v2);
            if(v2 == null) {
                return v0_1;
            }

            goto label_3;
        }
        catch(Throwable v0) {
        }

    label_9:
        if(v2 != null) {
            v2.close();
        }

        throw v0;
    label_3:
        v2.close();
        return v0_1;
    }

    @VisibleForTesting protected HttpURLConnection getServerConnection(VariationsPlatform arg1, String arg2, String arg3, String arg4) throws MalformedURLException, IOException {
        return new URL(this.getConnectionString(arg1, arg2, arg3, arg4)).openConnection();
    }

    private void recordFetchResultOrCode(int arg3) {
        new SparseHistogramSample("Variations.FirstRun.SeedFetchResult").record(arg3);
    }

    private void recordSeedConnectTime(long arg4) {
        new TimesHistogramSample("Variations.FirstRun.SeedConnectTime", TimeUnit.MILLISECONDS).record(arg4);
    }

    private void recordSeedFetchTime(long arg4) {
        Log.i("VariationsSeedFetch", "Fetched first run seed in " + arg4 + " ms", new Object[0]);
        new TimesHistogramSample("Variations.FirstRun.SeedFetchTime", TimeUnit.MILLISECONDS).record(arg4);
    }

    @VisibleForTesting public static void setVariationsSeedFetcherForTesting(VariationsSeedFetcher arg0) {
        VariationsSeedFetcher.sInstance = arg0;
    }
}

