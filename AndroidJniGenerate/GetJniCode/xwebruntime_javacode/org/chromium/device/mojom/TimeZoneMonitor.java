package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TimeZoneMonitor extends Interface {
    public interface Proxy extends TimeZoneMonitor, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        TimeZoneMonitor.MANAGER = TimeZoneMonitor_Internal.MANAGER;
    }

    void addClient(TimeZoneMonitorClient arg1);
}

