package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface InputDeviceManagerClient extends Interface {
    public interface Proxy extends InputDeviceManagerClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        InputDeviceManagerClient.MANAGER = InputDeviceManagerClient_Internal.MANAGER;
    }

    void inputDeviceAdded(InputDeviceInfo arg1);

    void inputDeviceRemoved(String arg1);
}

