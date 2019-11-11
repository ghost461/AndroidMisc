package org.chromium.ui.base;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.annotation.UiThread;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.display.DisplayAndroid;
import org.chromium.ui.display.DisplayUtil;

public class DeviceFormFactor {
    public static final int MINIMUM_TABLET_WIDTH_DP = 600;
    private static final int SCREEN_BUCKET_LARGET_TABLET = 3;
    private static final int SCREEN_BUCKET_TABLET = 2;

    public DeviceFormFactor() {
        super();
    }

    private static int detectScreenWidthBucket(Context arg2) {
        if(Build$VERSION.SDK_INT >= 17 && (ThreadUtils.runningOnUiThread()) && !DeviceFormFactor.isTabletDisplay(DisplayAndroid.getNonMultiDisplay(arg2))) {
            return 0;
        }

        return arg2.getResources().getInteger(0x7F080004);
    }

    private static int detectScreenWidthBucket(WindowAndroid arg1) {
        ThreadUtils.assertOnUiThread();
        Object v0 = arg1.getContext().get();
        if(v0 != null) {
            if(!DeviceFormFactor.isTabletDisplay(arg1.getDisplay())) {
            }
            else {
                return ((Context)v0).getResources().getInteger(0x7F080004);
            }
        }

        return 0;
    }

    public static int getMinimumTabletWidthPx(DisplayAndroid arg1) {
        return DisplayUtil.dpToPx(arg1, 600);
    }

    @UiThread public static int getNonMultiDisplayMinimumTabletWidthPx(Context arg0) {
        return DeviceFormFactor.getMinimumTabletWidthPx(DisplayAndroid.getNonMultiDisplay(arg0));
    }

    public static boolean isNonMultiDisplayContextOnLargeTablet(Context arg1) {
        boolean v1 = DeviceFormFactor.detectScreenWidthBucket(arg1) == 3 ? true : false;
        return v1;
    }

    public static boolean isNonMultiDisplayContextOnTablet(Context arg1) {
        boolean v1 = DeviceFormFactor.detectScreenWidthBucket(arg1) >= 2 ? true : false;
        return v1;
    }

    @Deprecated @CalledByNative public static boolean isTablet() {
        boolean v0 = DeviceFormFactor.detectScreenWidthBucket(ContextUtils.getApplicationContext()) >= 2 ? true : false;
        return v0;
    }

    private static boolean isTabletDisplay(DisplayAndroid arg1) {
        boolean v1 = DisplayUtil.getSmallestWidth(arg1) >= DeviceFormFactor.getMinimumTabletWidthPx(arg1) ? true : false;
        return v1;
    }

    @UiThread public static boolean isWindowOnTablet(WindowAndroid arg1) {
        boolean v1 = DeviceFormFactor.detectScreenWidthBucket(arg1) >= 2 ? true : false;
        return v1;
    }
}

