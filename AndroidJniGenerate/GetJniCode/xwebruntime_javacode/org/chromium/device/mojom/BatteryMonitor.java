package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface BatteryMonitor extends Interface {
    public interface Proxy extends BatteryMonitor, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface QueryNextStatusResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        BatteryMonitor.MANAGER = BatteryMonitor_Internal.MANAGER;
    }

    void queryNextStatus(QueryNextStatusResponse arg1);
}

