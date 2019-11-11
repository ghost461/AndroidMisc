package org.chromium.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;

public class PackageUtils {
    private PackageUtils() {
        super();
    }

    public static int getPackageVersion(Context arg2, String arg3) {
        PackageManager v2 = arg2.getPackageManager();
        int v1 = -1;
        try {
            PackageInfo v2_1 = v2.getPackageInfo(arg3, 0);
            if(v2_1 != null) {
                v1 = v2_1.versionCode;
            }

            return v1;
        }
        catch(PackageManager$NameNotFoundException ) {
            return v1;
        }
    }
}

