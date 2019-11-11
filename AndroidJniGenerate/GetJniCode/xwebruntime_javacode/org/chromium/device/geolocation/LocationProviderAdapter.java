package org.chromium.device.geolocation;

import android.location.Location;
import java.util.concurrent.FutureTask;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;

@VisibleForTesting public class LocationProviderAdapter {
    private static final String TAG = "cr_LocationProvider";
    private LocationProvider mImpl;

    static {
    }

    private LocationProviderAdapter() {
        super();
        this.mImpl = LocationProviderFactory.create();
    }

    static LocationProvider access$000(LocationProviderAdapter arg0) {
        return arg0.mImpl;
    }

    @CalledByNative public static LocationProviderAdapter create() {
        return new LocationProviderAdapter();
    }

    public boolean isRunning() {
        return this.mImpl.isRunning();
    }

    private static native void nativeNewErrorAvailable(String arg0) {
    }

    private static native void nativeNewLocationAvailable(double arg0, double arg1, double arg2, boolean arg3, double arg4, boolean arg5, double arg6, boolean arg7, double arg8, boolean arg9, double arg10) {
    }

    public static void newErrorAvailable(String arg4) {
        Log.e("cr_LocationProvider", "newErrorAvailable %s", new Object[]{arg4});
        LocationProviderAdapter.nativeNewErrorAvailable(arg4);
    }

    public static void onNewLocationAvailable(Location arg21) {
        LocationProviderAdapter.nativeNewLocationAvailable(arg21.getLatitude(), arg21.getLongitude(), (((double)arg21.getTime())) / 1000, arg21.hasAltitude(), arg21.getAltitude(), arg21.hasAccuracy(), ((double)arg21.getAccuracy()), arg21.hasBearing(), ((double)arg21.getBearing()), arg21.hasSpeed(), ((double)arg21.getSpeed()));
    }

    @CalledByNative public void start(boolean arg3) {
        ThreadUtils.runOnUiThread(new FutureTask(new Runnable(arg3) {
            public void run() {
                LocationProviderAdapter.this.mImpl.start(this.val$enableHighAccuracy);
            }
        }, null));
    }

    @CalledByNative public void stop() {
        ThreadUtils.runOnUiThread(new FutureTask(new Runnable() {
            public void run() {
                LocationProviderAdapter.this.mImpl.stop();
            }
        }, null));
    }
}

