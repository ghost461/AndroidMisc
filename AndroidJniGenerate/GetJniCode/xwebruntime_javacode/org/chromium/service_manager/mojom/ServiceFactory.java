package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface ServiceFactory extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ServiceFactory {
    }

    public static final Manager MANAGER;

    static {
        ServiceFactory.MANAGER = ServiceFactory_Internal.MANAGER;
    }

    void createService(InterfaceRequest arg1, String arg2, PidReceiver arg3);
}

