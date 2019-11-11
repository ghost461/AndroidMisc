package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceManager extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ServiceManager {
    }

    public static final Manager MANAGER;

    static {
        ServiceManager.MANAGER = ServiceManager_Internal.MANAGER;
    }

    void addListener(ServiceManagerListener arg1);
}

