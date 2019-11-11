package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface Geolocation extends Interface {
    public interface Proxy extends Geolocation, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface QueryNextPositionResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        Geolocation.MANAGER = Geolocation_Internal.MANAGER;
    }

    void queryNextPosition(QueryNextPositionResponse arg1);

    void setHighAccuracy(boolean arg1);
}

