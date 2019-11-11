package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface AndroidOverlayProvider extends Interface {
    public interface Proxy extends AndroidOverlayProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AndroidOverlayProvider.MANAGER = AndroidOverlayProvider_Internal.MANAGER;
    }

    void createOverlay(InterfaceRequest arg1, AndroidOverlayClient arg2, AndroidOverlayConfig arg3);
}

