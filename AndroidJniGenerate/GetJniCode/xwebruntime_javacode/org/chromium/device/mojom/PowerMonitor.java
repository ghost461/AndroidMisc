package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface PowerMonitor extends Interface {
    public interface Proxy extends PowerMonitor, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        PowerMonitor.MANAGER = PowerMonitor_Internal.MANAGER;
    }

    void addClient(PowerMonitorClient arg1);
}

