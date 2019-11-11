package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceWorkerStreamCallback extends Interface {
    public interface Proxy extends ServiceWorkerStreamCallback, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerStreamCallback.MANAGER = ServiceWorkerStreamCallback_Internal.MANAGER;
    }

    void onAborted();

    void onCompleted();
}

