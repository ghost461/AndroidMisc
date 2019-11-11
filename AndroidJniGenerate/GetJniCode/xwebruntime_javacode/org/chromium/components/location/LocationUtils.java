package org.chromium.components.location;

import android.content.Context;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.Process;
import android.provider.Settings$Secure;
import android.text.TextUtils;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.base.WindowAndroid;

public class LocationUtils {
    public interface Factory {
        LocationUtils create();
    }

    private static Factory sFactory;
    private static LocationUtils sInstance;

    protected LocationUtils() {
        super();
    }

    public boolean canPromptToEnableSystemLocationSetting() {
        return 0;
    }

    public static LocationUtils getInstance() {
        ThreadUtils.assertOnUiThread();
        if(LocationUtils.sInstance == null) {
            LocationUtils.sInstance = LocationUtils.sFactory == null ? new LocationUtils() : LocationUtils.sFactory.create();
        }

        return LocationUtils.sInstance;
    }

    public Intent getSystemLocationSettingsIntent() {
        Intent v0 = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        v0.setFlags(0x10000000);
        return v0;
    }

    public boolean hasAndroidLocationPermission() {
        boolean v0 = (this.hasPermission("android.permission.ACCESS_COARSE_LOCATION")) || (this.hasPermission("android.permission.ACCESS_FINE_LOCATION")) ? true : false;
        return v0;
    }

    private boolean hasPermission(String arg4) {
        boolean v4 = ApiCompatibilityUtils.checkPermission(ContextUtils.getApplicationContext(), arg4, Process.myPid(), Process.myUid()) == 0 ? true : false;
        return v4;
    }

    public boolean isSystemLocationSettingEnabled() {
        Context v0 = ContextUtils.getApplicationContext();
        boolean v2 = true;
        if(Build$VERSION.SDK_INT >= 19) {
            if(Settings$Secure.getInt(v0.getContentResolver(), "location_mode", 0) != 0) {
            }
            else {
                v2 = false;
            }

            return v2;
        }

        return TextUtils.isEmpty(Settings$Secure.getString(v0.getContentResolver(), "location_providers_allowed")) ^ 1;
    }

    public void promptToEnableSystemLocationSetting(int arg1, WindowAndroid arg2, Callback arg3) {
        arg3.onResult(Integer.valueOf(3));
    }

    @VisibleForTesting public static void setFactory(Factory arg0) {
        LocationUtils.sFactory = arg0;
        LocationUtils.sInstance = null;
    }
}

