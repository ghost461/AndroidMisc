package org.chromium.base.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.MainDex;

@MainDex public class ChromiumMultiDexInstaller {
    private static final String IGNORE_MULTIDEX_KEY = ".ignore_multidex";
    private static final String TAG = "base_multidex";

    public ChromiumMultiDexInstaller() {
        super();
    }

    @VisibleForTesting public static void install(Context arg3) {
        if(Build$VERSION.SDK_INT >= 21 || (ChromiumMultiDexInstaller.shouldInstallMultiDex(arg3))) {
            MultiDex.install(arg3);
            Log.i("base_multidex", "Completed multidex installation.", new Object[0]);
        }
        else {
            Log.i("base_multidex", "Skipping multidex installation: not needed for process.", new Object[0]);
        }
    }

    private static boolean shouldInstallMultiDex(Context arg5) {
        if(ContextUtils.isIsolatedProcess()) {
            return 0;
        }

        String v0 = ContextUtils.getProcessName();
        PackageManager v2 = arg5.getPackageManager();
        try {
            ApplicationInfo v5 = v2.getApplicationInfo(arg5.getPackageName(), 0x80);
            if(v5 != null) {
                if(v5.metaData == null) {
                }
                else {
                    Bundle v5_1 = v5.metaData;
                    StringBuilder v2_1 = new StringBuilder();
                    v2_1.append(v0);
                    v2_1.append(".ignore_multidex");
                    return v5_1.getBoolean(v2_1.toString(), false) ^ 1;
                }
            }
        }
        catch(PackageManager$NameNotFoundException ) {
            return 1;
        }

        return 1;
    }
}

