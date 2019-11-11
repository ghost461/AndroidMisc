package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface PrefetchUrlLoaderService extends Interface {
    public interface Proxy extends PrefetchUrlLoaderService, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        PrefetchUrlLoaderService.MANAGER = PrefetchUrlLoaderService_Internal.MANAGER;
    }

    void getFactory(InterfaceRequest arg1);
}

