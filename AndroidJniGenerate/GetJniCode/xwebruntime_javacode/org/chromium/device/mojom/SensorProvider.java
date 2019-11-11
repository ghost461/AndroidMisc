package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface SensorProvider extends Interface {
    public interface GetSensorResponse extends Callback2 {
    }

    public interface Proxy extends SensorProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        SensorProvider.MANAGER = SensorProvider_Internal.MANAGER;
    }

    void getSensor(int arg1, GetSensorResponse arg2);
}

