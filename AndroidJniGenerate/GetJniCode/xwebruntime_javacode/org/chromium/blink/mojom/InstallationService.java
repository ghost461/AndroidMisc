package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface InstallationService extends Interface {
    public interface Proxy extends InstallationService, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        InstallationService.MANAGER = InstallationService_Internal.MANAGER;
    }

    void onInstall();
}

