package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TimeZoneMonitorClient extends Interface {
    public interface Proxy extends TimeZoneMonitorClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        TimeZoneMonitorClient.MANAGER = TimeZoneMonitorClient_Internal.MANAGER;
    }

    void onTimeZoneChange(String arg1);
}

