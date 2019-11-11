package org.chromium.device.geolocation;

import org.chromium.base.VisibleForTesting;

public class LocationProviderFactory {
    public interface LocationProvider {
        boolean isRunning();

        void start(boolean arg1);

        void stop();
    }

    private static LocationProvider sProviderImpl;
    private static boolean sUseGmsCoreLocationProvider;

    private LocationProviderFactory() {
        super();
    }

    public static LocationProvider create() {
        if(LocationProviderFactory.sProviderImpl != null) {
            return LocationProviderFactory.sProviderImpl;
        }

        LocationProviderFactory.sProviderImpl = new LocationProviderAndroid();
        return LocationProviderFactory.sProviderImpl;
    }

    @VisibleForTesting public static void setLocationProviderImpl(LocationProvider arg0) {
        LocationProviderFactory.sProviderImpl = arg0;
    }
}

