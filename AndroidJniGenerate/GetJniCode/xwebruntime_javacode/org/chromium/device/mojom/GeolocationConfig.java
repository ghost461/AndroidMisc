package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface GeolocationConfig extends Interface {
    public interface IsHighAccuracyLocationBeingCapturedResponse extends Callback1 {
    }

    public interface Proxy extends GeolocationConfig, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        GeolocationConfig.MANAGER = GeolocationConfig_Internal.MANAGER;
    }

    void isHighAccuracyLocationBeingCaptured(IsHighAccuracyLocationBeingCapturedResponse arg1);
}

