package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceControl extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ServiceControl {
    }

    public static final Manager MANAGER;

    static {
        ServiceControl.MANAGER = ServiceControl_Internal.MANAGER;
    }

    void requestQuit();
}

