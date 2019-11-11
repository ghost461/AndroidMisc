package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface SensorClient extends Interface {
    public interface Proxy extends SensorClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        SensorClient.MANAGER = SensorClient_Internal.MANAGER;
    }

    void raiseError();

    void sensorReadingChanged();
}

