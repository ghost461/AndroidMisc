package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ProxyConfigPollerClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ProxyConfigPollerClient {
    }

    public static final Manager MANAGER;

    static {
        ProxyConfigPollerClient.MANAGER = ProxyConfigPollerClient_Internal.MANAGER;
    }

    void onLazyProxyConfigPoll();
}

