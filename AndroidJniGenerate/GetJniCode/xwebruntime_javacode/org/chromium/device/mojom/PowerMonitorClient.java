package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface PowerMonitorClient extends Interface {
    public interface Proxy extends PowerMonitorClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        PowerMonitorClient.MANAGER = PowerMonitorClient_Internal.MANAGER;
    }

    void powerStateChange(boolean arg1);

    void resume();

    void suspend();
}

