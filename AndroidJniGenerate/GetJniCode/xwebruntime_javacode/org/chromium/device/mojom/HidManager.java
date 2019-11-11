package org.chromium.device.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface HidManager extends Interface {
    public interface ConnectResponse extends Callback1 {
    }

    public interface GetDevicesAndSetClientResponse extends Callback1 {
    }

    public interface GetDevicesResponse extends Callback1 {
    }

    public interface Proxy extends HidManager, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        HidManager.MANAGER = HidManager_Internal.MANAGER;
    }

    void connect(String arg1, ConnectResponse arg2);

    void getDevices(GetDevicesResponse arg1);

    void getDevicesAndSetClient(AssociatedInterfaceNotSupported arg1, GetDevicesAndSetClientResponse arg2);
}

