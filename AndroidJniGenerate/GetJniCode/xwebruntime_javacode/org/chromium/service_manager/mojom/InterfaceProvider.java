package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.MessagePipeHandle;

public interface InterfaceProvider extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, InterfaceProvider {
    }

    public static final Manager MANAGER;

    static {
        InterfaceProvider.MANAGER = InterfaceProvider_Internal.MANAGER;
    }

    void getInterface(String arg1, MessagePipeHandle arg2);
}

