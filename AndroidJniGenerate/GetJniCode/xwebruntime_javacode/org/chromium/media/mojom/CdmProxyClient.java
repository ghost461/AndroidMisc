package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface CdmProxyClient extends Interface {
    public interface Proxy extends CdmProxyClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        CdmProxyClient.MANAGER = CdmProxyClient_Internal.MANAGER;
    }

    void notifyHardwareReset();
}

