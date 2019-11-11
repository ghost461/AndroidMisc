package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceWorkerInstalledScriptsManager extends Interface {
    public interface Proxy extends ServiceWorkerInstalledScriptsManager, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerInstalledScriptsManager.MANAGER = ServiceWorkerInstalledScriptsManager_Internal.MANAGER;
    }

    void transferInstalledScript(ServiceWorkerScriptInfo arg1);
}

