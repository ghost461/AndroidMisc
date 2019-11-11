package org.chromium.proxy_resolver.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.net.interfaces.HostResolverRequestClient;
import org.chromium.net.interfaces.HostResolverRequestInfo;

public interface ProxyResolverFactoryRequestClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ProxyResolverFactoryRequestClient {
    }

    public static final Manager MANAGER;

    static {
        ProxyResolverFactoryRequestClient.MANAGER = ProxyResolverFactoryRequestClient_Internal.MANAGER;
    }

    void alert(String arg1);

    void onError(int arg1, String arg2);

    void reportResult(int arg1);

    void resolveDns(HostResolverRequestInfo arg1, HostResolverRequestClient arg2);
}

