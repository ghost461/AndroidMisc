package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.service_manager.mojom.InterfaceProvider;

public interface MediaService extends Interface {
    public interface Proxy extends MediaService, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        MediaService.MANAGER = MediaService_Internal.MANAGER;
    }

    void createInterfaceFactory(InterfaceRequest arg1, InterfaceProvider arg2);
}

