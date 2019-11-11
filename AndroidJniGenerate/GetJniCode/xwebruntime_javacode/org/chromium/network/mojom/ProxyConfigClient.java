package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ProxyConfigClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ProxyConfigClient {
    }

    public static final Manager MANAGER;

    static {
        ProxyConfigClient.MANAGER = ProxyConfigClient_Internal.MANAGER;
    }

    void onProxyConfigUpdated(ProxyConfigWithAnnotation arg1);
}

