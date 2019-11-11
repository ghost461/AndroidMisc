package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface HidManagerClient extends Interface {
    public interface Proxy extends HidManagerClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        HidManagerClient.MANAGER = HidManagerClient_Internal.MANAGER;
    }

    void deviceAdded(HidDeviceInfo arg1);

    void deviceRemoved(HidDeviceInfo arg1);
}

