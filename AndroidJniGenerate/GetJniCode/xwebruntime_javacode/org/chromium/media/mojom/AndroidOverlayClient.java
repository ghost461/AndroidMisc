package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AndroidOverlayClient extends Interface {
    public interface Proxy extends AndroidOverlayClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AndroidOverlayClient.MANAGER = AndroidOverlayClient_Internal.MANAGER;
    }

    void onDestroyed();

    void onPowerEfficientState(boolean arg1);

    void onSurfaceReady(long arg1);
}

