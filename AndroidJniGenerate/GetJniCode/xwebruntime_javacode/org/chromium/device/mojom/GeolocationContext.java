package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface GeolocationContext extends Interface {
    public interface Proxy extends GeolocationContext, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        GeolocationContext.MANAGER = GeolocationContext_Internal.MANAGER;
    }

    void bindGeolocation(InterfaceRequest arg1);

    void clearOverride();

    void setOverride(Geoposition arg1);
}

