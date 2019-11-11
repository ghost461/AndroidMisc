package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceWorkerObjectHost extends Interface {
    public interface Proxy extends ServiceWorkerObjectHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface TerminateForTestingResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerObjectHost.MANAGER = ServiceWorkerObjectHost_Internal.MANAGER;
    }

    void postMessageToServiceWorker(TransferableMessage arg1);

    void terminateForTesting(TerminateForTestingResponse arg1);
}

