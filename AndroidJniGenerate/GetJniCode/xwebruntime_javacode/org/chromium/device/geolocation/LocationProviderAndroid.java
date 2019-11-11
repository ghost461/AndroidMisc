package org.chromium.device.geolocation;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

public class LocationProviderAndroid implements LocationListener, LocationProvider {
    private static final String TAG = "cr_LocationProvider";
    private boolean mIsRunning;
    private LocationManager mLocationManager;

    static {
    }

    LocationProviderAndroid() {
        super();
    }

    private void createLocationManagerIfNeeded() {
        if(this.mLocationManager != null) {
            return;
        }

        this.mLocationManager = ContextUtils.getApplicationContext().getSystemService("location");
        if(this.mLocationManager == null) {
            Log.e("cr_LocationProvider", "Could not get location manager.", new Object[0]);
        }
    }

    private boolean isOnlyPassiveLocationProviderEnabled() {
        boolean v1 = true;
        List v0 = this.mLocationManager.getProviders(true);
        if(v0 == null || v0.size() != 1 || !v0.get(0).equals("passive")) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public boolean isRunning() {
        ThreadUtils.assertOnUiThread();
        return this.mIsRunning;
    }

    public void onLocationChanged(Location arg2) {
        if(this.mIsRunning) {
            LocationProviderAdapter.onNewLocationAvailable(arg2);
        }
    }

    public void onProviderDisabled(String arg1) {
    }

    public void onProviderEnabled(String arg1) {
    }

    public void onStatusChanged(String arg1, int arg2, Bundle arg3) {
    }

    private void registerForLocationUpdates(boolean arg10) {
        this.createLocationManagerIfNeeded();
        if(this.usePassiveOneShotLocation()) {
            return;
        }

        this.mIsRunning = true;
        try {
            Criteria v6 = new Criteria();
            if(arg10) {
                v6.setAccuracy(1);
            }

            this.mLocationManager.requestLocationUpdates(0, 0f, v6, this, ThreadUtils.getUiThreadLooper());
        }
        catch(IllegalArgumentException ) {
            Log.e("cr_LocationProvider", "Caught IllegalArgumentException registering for location updates.", new Object[0]);
            this.unregisterFromLocationUpdates();
        }
        catch(SecurityException ) {
            Log.e("cr_LocationProvider", "Caught security exception while registering for location updates from the system. The application does not have sufficient geolocation permissions.", new Object[0]);
            this.unregisterFromLocationUpdates();
            LocationProviderAdapter.newErrorAvailable("application does not have sufficient geolocation permissions.");
        }
    }

    @VisibleForTesting public void setLocationManagerForTesting(LocationManager arg1) {
        this.mLocationManager = arg1;
    }

    public void start(boolean arg1) {
        ThreadUtils.assertOnUiThread();
        this.unregisterFromLocationUpdates();
        this.registerForLocationUpdates(arg1);
    }

    public void stop() {
        ThreadUtils.assertOnUiThread();
        this.unregisterFromLocationUpdates();
    }

    private void unregisterFromLocationUpdates() {
        if(!this.mIsRunning) {
            return;
        }

        this.mIsRunning = false;
        this.mLocationManager.removeUpdates(((LocationListener)this));
    }

    private boolean usePassiveOneShotLocation() {
        if(!this.isOnlyPassiveLocationProviderEnabled()) {
            return 0;
        }

        Location v0 = this.mLocationManager.getLastKnownLocation("passive");
        if(v0 != null) {
            ThreadUtils.runOnUiThread(new Runnable(v0) {
                public void run() {
                    LocationProviderAdapter.onNewLocationAvailable(this.val$location);
                }
            });
        }

        return 1;
    }
}

