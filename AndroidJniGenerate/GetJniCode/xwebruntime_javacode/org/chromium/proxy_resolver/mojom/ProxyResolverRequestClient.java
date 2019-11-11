package org.chromium.proxy_resolver.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.net.interfaces.HostResolverRequestClient;
import org.chromium.net.interfaces.HostResolverRequestInfo;

public interface ProxyResolverRequestClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ProxyResolverRequestClient {
    }

    public static final Manager MANAGER;

    static {
        ProxyResolverRequestClient.MANAGER = ProxyResolverRequestClient_Internal.MANAGER;
    }

    void alert(String arg1);

    void onError(int arg1, String arg2);

    void reportResult(int arg1, ProxyInfo arg2);

    void resolveDns(HostResolverRequestInfo arg1, HostResolverRequestClient arg2);
}

