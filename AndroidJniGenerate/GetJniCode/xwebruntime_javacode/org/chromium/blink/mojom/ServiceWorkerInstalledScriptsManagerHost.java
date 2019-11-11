package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface ServiceWorkerInstalledScriptsManagerHost extends Interface {
    public interface Proxy extends ServiceWorkerInstalledScriptsManagerHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerInstalledScriptsManagerHost.MANAGER = ServiceWorkerInstalledScriptsManagerHost_Internal.MANAGER;
    }

    void requestInstalledScript(Url arg1);
}

