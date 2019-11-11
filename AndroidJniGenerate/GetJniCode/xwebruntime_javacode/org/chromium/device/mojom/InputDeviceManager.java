package org.chromium.device.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface InputDeviceManager extends Interface {
    public interface GetDevicesAndSetClientResponse extends Callback1 {
    }

    public interface GetDevicesResponse extends Callback1 {
    }

    public interface Proxy extends InputDeviceManager, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        InputDeviceManager.MANAGER = InputDeviceManager_Internal.MANAGER;
    }

    void getDevices(GetDevicesResponse arg1);

    void getDevicesAndSetClient(AssociatedInterfaceNotSupported arg1, GetDevicesAndSetClientResponse arg2);
}

