package org.chromium.base;

public class BuildConfig {
    public static String[] COMPRESSED_LOCALES = null;
    public static boolean DCHECK_IS_ON = false;
    public static String FIREBASE_APP_ID = "";
    public static boolean IS_MULTIDEX_ENABLED = false;
    public static boolean IS_UBSAN = false;
    public static int R_STRING_PRODUCT_VERSION;
    public static String[] UNCOMPRESSED_LOCALES;

    static {
        BuildConfig.COMPRESSED_LOCALES = new String[0];
        BuildConfig.UNCOMPRESSED_LOCALES = new String[0];
    }

    public BuildConfig() {
        super();
    }
}

