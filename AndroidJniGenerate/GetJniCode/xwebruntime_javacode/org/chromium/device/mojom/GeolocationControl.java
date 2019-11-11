package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface GeolocationControl extends Interface {
    public interface Proxy extends GeolocationControl, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        GeolocationControl.MANAGER = GeolocationControl_Internal.MANAGER;
    }

    void userDidOptIntoLocationServices();
}

