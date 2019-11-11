package org.chromium.components.variations.firstrun;

import android.util.Base64;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;

public class VariationsSeedBridge {
    protected static final String VARIATIONS_FIRST_RUN_SEED_BASE64 = "variations_seed_base64";
    protected static final String VARIATIONS_FIRST_RUN_SEED_COUNTRY = "variations_seed_country";
    protected static final String VARIATIONS_FIRST_RUN_SEED_DATE = "variations_seed_date";
    protected static final String VARIATIONS_FIRST_RUN_SEED_IS_GZIP_COMPRESSED = "variations_seed_is_gzip_compressed";
    protected static final String VARIATIONS_FIRST_RUN_SEED_NATIVE_STORED = "variations_seed_native_stored";
    protected static final String VARIATIONS_FIRST_RUN_SEED_SIGNATURE = "variations_seed_signature";

    public VariationsSeedBridge() {
        super();
    }

    @CalledByNative private static void clearFirstRunPrefs() {
        ContextUtils.getAppSharedPreferences().edit().remove("variations_seed_base64").remove("variations_seed_signature").remove("variations_seed_country").remove("variations_seed_date").remove("variations_seed_is_gzip_compressed").apply();
    }

    @CalledByNative private static String getVariationsFirstRunSeedCountry() {
        return VariationsSeedBridge.getVariationsFirstRunSeedPref("variations_seed_country");
    }

    @CalledByNative private static byte[] getVariationsFirstRunSeedData() {
        return Base64.decode(VariationsSeedBridge.getVariationsFirstRunSeedPref("variations_seed_base64"), 2);
    }

    @CalledByNative private static String getVariationsFirstRunSeedDate() {
        return VariationsSeedBridge.getVariationsFirstRunSeedPref("variations_seed_date");
    }

    @CalledByNative private static boolean getVariationsFirstRunSeedIsGzipCompressed() {
        return ContextUtils.getAppSharedPreferences().getBoolean("variations_seed_is_gzip_compressed", false);
    }

    protected static String getVariationsFirstRunSeedPref(String arg2) {
        return ContextUtils.getAppSharedPreferences().getString(arg2, "");
    }

    @CalledByNative private static String getVariationsFirstRunSeedSignature() {
        return VariationsSeedBridge.getVariationsFirstRunSeedPref("variations_seed_signature");
    }

    public static boolean hasJavaPref() {
        return ContextUtils.getAppSharedPreferences().getString("variations_seed_base64", "").isEmpty() ^ 1;
    }

    public static boolean hasNativePref() {
        return ContextUtils.getAppSharedPreferences().getBoolean("variations_seed_native_stored", false);
    }

    @CalledByNative private static void markVariationsSeedAsStored() {
        ContextUtils.getAppSharedPreferences().edit().putBoolean("variations_seed_native_stored", true).apply();
    }

    @CalledByNative public static void setVariationsFirstRunSeed(byte[] arg3, String arg4, String arg5, String arg6, boolean arg7) {
        ContextUtils.getAppSharedPreferences().edit().putString("variations_seed_base64", Base64.encodeToString(arg3, 2)).putString("variations_seed_signature", arg4).putString("variations_seed_country", arg5).putString("variations_seed_date", arg6).putBoolean("variations_seed_is_gzip_compressed", arg7).apply();
    }
}

