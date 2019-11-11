package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface PidReceiver extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, PidReceiver {
    }

    public static final Manager MANAGER;

    static {
        PidReceiver.MANAGER = PidReceiver_Internal.MANAGER;
    }

    void setPid(int arg1);
}

