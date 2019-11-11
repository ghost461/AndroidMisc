package org.chromium.services.service_manager;

import org.chromium.mojo.bindings.ConnectionErrorHandler;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;
import org.chromium.service_manager.mojom.InterfaceProvider$Proxy;

public class InterfaceProvider implements ConnectionErrorHandler {
    private Core mCore;
    private Proxy mInterfaceProvider;

    public InterfaceProvider(MessagePipeHandle arg3) {
        super();
        this.mCore = arg3.getCore();
        this.mInterfaceProvider = org.chromium.service_manager.mojom.InterfaceProvider.MANAGER.attachProxy(arg3, 0);
        this.mInterfaceProvider.getProxyHandler().setErrorHandler(((ConnectionErrorHandler)this));
    }

    public org.chromium.mojo.bindings.Interface$Proxy getInterface(Manager arg3) {
        Pair v0 = arg3.getInterfaceRequest(this.mCore);
        this.getInterface(arg3, v0.second);
        return v0.first;
    }

    public void getInterface(Manager arg2, InterfaceRequest arg3) {
        this.mInterfaceProvider.getInterface(arg2.getName(), arg3.passHandle());
    }

    public void onConnectionError(MojoException arg1) {
        this.mInterfaceProvider.close();
    }
}

