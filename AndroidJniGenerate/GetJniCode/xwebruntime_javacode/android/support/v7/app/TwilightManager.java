package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import java.util.Calendar;

class TwilightManager {
    class TwilightState {
        boolean isNight;
        long nextUpdate;
        long todaySunrise;
        long todaySunset;
        long tomorrowSunrise;
        long yesterdaySunset;

        TwilightState() {
            super();
        }
    }

    private static final int SUNRISE = 6;
    private static final int SUNSET = 22;
    private static final String TAG = "TwilightManager";
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState;
    private static TwilightManager sInstance;

    @VisibleForTesting TwilightManager(@NonNull Context arg2, @NonNull LocationManager arg3) {
        super();
        this.mTwilightState = new TwilightState();
        this.mContext = arg2;
        this.mLocationManager = arg3;
    }

    static TwilightManager getInstance(@NonNull Context arg2) {
        if(TwilightManager.sInstance == null) {
            arg2 = arg2.getApplicationContext();
            TwilightManager.sInstance = new TwilightManager(arg2, arg2.getSystemService("location"));
        }

        return TwilightManager.sInstance;
    }

    private Location getLastKnownLocation() {
        Location v1 = null;
        Location v0 = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? this.getLastKnownLocationForProvider("network") : v1;
        if(PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            v1 = this.getLastKnownLocationForProvider("gps");
        }

        if(v1 != null && v0 != null) {
            if(v1.getTime() > v0.getTime()) {
                v0 = v1;
            }

            return v0;
        }

        if(v1 != null) {
            v0 = v1;
        }

        return v0;
    }

    private Location getLastKnownLocationForProvider(String arg3) {
        if(this.mLocationManager != null) {
            try {
                if(!this.mLocationManager.isProviderEnabled(arg3)) {
                    return null;
                }

                return this.mLocationManager.getLastKnownLocation(arg3);
            }
            catch(Exception v3) {
                Log.d("TwilightManager", "Failed to get last known location", ((Throwable)v3));
            }
        }

        return null;
    }

    boolean isNight() {
        TwilightState v0 = this.mTwilightState;
        if(this.isStateValid()) {
            return v0.isNight;
        }

        Location v1 = this.getLastKnownLocation();
        if(v1 != null) {
            this.updateState(v1);
            return v0.isNight;
        }

        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int v0_1 = Calendar.getInstance().get(11);
        boolean v0_2 = v0_1 < 6 || v0_1 >= 22 ? true : false;
        return v0_2;
    }

    private boolean isStateValid() {
        boolean v0 = this.mTwilightState == null || this.mTwilightState.nextUpdate <= System.currentTimeMillis() ? false : true;
        return v0;
    }

    @VisibleForTesting static void setInstance(TwilightManager arg0) {
        TwilightManager.sInstance = arg0;
    }

    private void updateState(@NonNull Location arg24) {
        TwilightState v1 = this.mTwilightState;
        long v9 = System.currentTimeMillis();
        TwilightCalculator v11 = TwilightCalculator.getInstance();
        long v12 = 86400000;
        v11.calculateTwilight(v9 - v12, arg24.getLatitude(), arg24.getLongitude());
        long v14 = v11.sunset;
        v11.calculateTwilight(v9, arg24.getLatitude(), arg24.getLongitude());
        boolean v7 = v11.state == 1 ? true : false;
        long v5 = v11.sunrise;
        long v16 = v9 + v12;
        long v20 = v14;
        v14 = v11.sunset;
        TwilightState v22 = v1;
        long v0 = v5;
        boolean v12_1 = v7;
        v11.calculateTwilight(v16, arg24.getLatitude(), arg24.getLongitude());
        long v2 = v11.sunrise;
        long v4 = 0;
        long v6 = -1;
        if(v0 == v6 || v14 == v6) {
            v6 = v9 + 43200000;
        }
        else {
            if(v9 > v14) {
                v6 = v2 + v4;
            }
            else if(v9 > v0) {
                v6 = v14 + v4;
            }
            else {
                v6 = v0 + v4;
            }

            v6 += 60000;
        }

        v22.isNight = v12_1;
        v22.yesterdaySunset = v20;
        v22.todaySunrise = v0;
        v22.todaySunset = v14;
        v22.tomorrowSunrise = v2;
        v22.nextUpdate = v6;
    }
}

