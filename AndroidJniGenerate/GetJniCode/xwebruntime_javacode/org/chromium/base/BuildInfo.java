package org.chromium.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Build$VERSION;
import android.os.Build;
import android.text.TextUtils;
import org.chromium.base.annotations.CalledByNative;

public class BuildInfo {
    class org.chromium.base.BuildInfo$1 {
    }

    class Holder {
        private static BuildInfo sInstance;

        static {
            Holder.sInstance = new BuildInfo(null);
        }

        private Holder() {
            super();
        }

        static BuildInfo access$100() {
            return Holder.sInstance;
        }
    }

    private static final int MAX_FINGERPRINT_LENGTH = 0x80;
    private static final String TAG = "BuildInfo";
    public final String abiString;
    public final String androidBuildFingerprint;
    public final String customThemes;
    public final String extractedFileSuffix;
    public final String gmsVersionCode;
    public final String hostPackageLabel;
    public final int hostVersionCode;
    public final String installerPackageName;
    public final String packageName;
    public final String resourcesVersion;
    private static PackageInfo sBrowserPackageInfo;
    private static boolean sInitialized;
    public final int versionCode;
    public final String versionName;

    static {
    }

    private BuildInfo() {
        PackageInfo v2_2;
        PackageInfo v6;
        PackageInfo v4;
        PackageManager v1_1;
        String v2;
        super();
        BuildInfo.sInitialized = true;
        try {
            Context v1 = ContextUtils.getApplicationContext();
            v2 = v1.getPackageName();
            v1_1 = v1.getPackageManager();
            v4 = v1_1.getPackageInfo(v2, 0);
            this.hostVersionCode = v4.versionCode;
            v6 = null;
            if(BuildInfo.sBrowserPackageInfo != null) {
                this.packageName = BuildInfo.sBrowserPackageInfo.packageName;
                this.versionCode = BuildInfo.sBrowserPackageInfo.versionCode;
                this.versionName = BuildInfo.nullToEmpty(BuildInfo.sBrowserPackageInfo.versionName);
                BuildInfo.sBrowserPackageInfo = v6;
            }
            else {
                this.packageName = v2;
                this.versionCode = this.hostVersionCode;
                this.versionName = BuildInfo.nullToEmpty(v4.versionName);
            }

            this.hostPackageLabel = BuildInfo.nullToEmpty(v1_1.getApplicationLabel(v4.applicationInfo));
            this.installerPackageName = BuildInfo.nullToEmpty(v1_1.getInstallerPackageName(this.packageName));
        }
        catch(PackageManager$NameNotFoundException v0) {
            goto label_109;
        }

        try {
            v2_2 = v1_1.getPackageInfo("com.google.android.gms", 0);
            goto label_47;
        }
        catch(PackageManager$NameNotFoundException v2_1) {
            try {
                Log.d("BuildInfo", "GMS package is not found.", v2_1);
                v2_2 = v6;
            label_47:
                v2 = v2_2 != null ? String.valueOf(v2_2.versionCode) : "gms versionCode not available.";
                this.gmsVersionCode = v2;
                v2 = "true";
            }
            catch(PackageManager$NameNotFoundException v0) {
                goto label_109;
            }
        }

        try {
            v1_1.getPackageInfo("projekt.substratum", 0);
            goto label_58;
        }
        catch(PackageManager$NameNotFoundException ) {
            try {
                v2 = "false";
            label_58:
                this.customThemes = v2;
                String v1_2 = "Not Enabled";
                if(BuildConfig.R_STRING_PRODUCT_VERSION != 0) {
                    try {
                        v1_2 = ContextUtils.getApplicationContext().getString(BuildConfig.R_STRING_PRODUCT_VERSION);
                    }
                    catch(Exception ) {
                        v1_2 = "Not found";
                    }
                }

                this.resourcesVersion = v1_2;
                this.abiString = Build$VERSION.SDK_INT >= 21 ? TextUtils.join(", ", Build.SUPPORTED_ABIS) : String.format("ABI1: %s, ABI2: %s", Build.CPU_ABI, Build.CPU_ABI2);
                long v1_3 = this.versionCode > 10 ? ((long)this.versionCode) : v4.lastUpdateTime;
                this.extractedFileSuffix = String.format("@%x", Long.valueOf(v1_3));
                this.androidBuildFingerprint = Build.FINGERPRINT.substring(0, Math.min(Build.FINGERPRINT.length(), 0x80));
                return;
            }
            catch(PackageManager$NameNotFoundException v0) {
            label_109:
                throw new RuntimeException(((Throwable)v0));
            }
        }
    }

    BuildInfo(org.chromium.base.BuildInfo$1 arg1) {
        this();
    }

    @CalledByNative private static String[] getAll() {
        BuildInfo v0 = BuildInfo.getInstance();
        String v1 = ContextUtils.getApplicationContext().getPackageName();
        String[] v2 = new String[23];
        v2[0] = Build.BRAND;
        v2[1] = Build.DEVICE;
        v2[2] = Build.ID;
        v2[3] = Build.MANUFACTURER;
        v2[4] = Build.MODEL;
        v2[5] = String.valueOf(Build$VERSION.SDK_INT);
        v2[6] = Build.TYPE;
        v2[7] = Build.BOARD;
        v2[8] = v1;
        v2[9] = String.valueOf(v0.hostVersionCode);
        v2[10] = v0.hostPackageLabel;
        v2[11] = v0.packageName;
        v2[12] = String.valueOf(v0.versionCode);
        v2[13] = v0.versionName;
        v2[14] = v0.androidBuildFingerprint;
        v2[15] = v0.gmsVersionCode;
        v2[16] = v0.installerPackageName;
        v2[17] = v0.abiString;
        v2[18] = BuildConfig.FIREBASE_APP_ID;
        v2[19] = v0.customThemes;
        v2[20] = v0.resourcesVersion;
        v2[21] = v0.extractedFileSuffix;
        String v0_1 = BuildInfo.isAtLeastP() ? "1" : "0";
        v2[22] = v0_1;
        return v2;
    }

    public static BuildInfo getInstance() {
        return Holder.access$100();
    }

    public static boolean isAtLeastP() {
        boolean v0 = Build$VERSION.SDK_INT >= 28 ? true : false;
        return v0;
    }

    public static boolean isDebugAndroid() {
        boolean v0 = ("eng".equals(Build.TYPE)) || ("userdebug".equals(Build.TYPE)) ? true : false;
        return v0;
    }

    private static String nullToEmpty(CharSequence arg0) {
        String v0 = arg0 == null ? "" : arg0.toString();
        return v0;
    }

    public static void setBrowserPackageInfo(PackageInfo arg0) {
        BuildInfo.sBrowserPackageInfo = arg0;
    }

    public static boolean targetsAtLeastP() {
        boolean v0 = ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion >= 28 ? true : false;
        return v0;
    }
}

