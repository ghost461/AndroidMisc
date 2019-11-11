package android.support.v4.content.res;

import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;

public final class ConfigurationHelper {
    private ConfigurationHelper() {
        super();
    }

    public static int getDensityDpi(@NonNull Resources arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getConfiguration().densityDpi;
        }

        return arg2.getDisplayMetrics().densityDpi;
    }

    @Deprecated public static int getScreenHeightDp(@NonNull Resources arg0) {
        return arg0.getConfiguration().screenHeightDp;
    }

    @Deprecated public static int getScreenWidthDp(@NonNull Resources arg0) {
        return arg0.getConfiguration().screenWidthDp;
    }

    @Deprecated public static int getSmallestScreenWidthDp(@NonNull Resources arg0) {
        return arg0.getConfiguration().smallestScreenWidthDp;
    }
}

