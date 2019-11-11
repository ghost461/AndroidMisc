package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface SerialDeviceEnumerator extends Interface {
    public interface GetDevicesResponse extends Callback1 {
    }

    public interface Proxy extends SerialDeviceEnumerator, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        SerialDeviceEnumerator.MANAGER = SerialDeviceEnumerator_Internal.MANAGER;
    }

    void getDevices(GetDevicesResponse arg1);
}

