package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceWorkerRegistrationObject extends Interface {
    public interface Proxy extends ServiceWorkerRegistrationObject, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerRegistrationObject.MANAGER = ServiceWorkerRegistrationObject_Internal.MANAGER;
    }

    void setVersionAttributes(int arg1, ServiceWorkerObjectInfo arg2, ServiceWorkerObjectInfo arg3, ServiceWorkerObjectInfo arg4);

    void updateFound();
}

