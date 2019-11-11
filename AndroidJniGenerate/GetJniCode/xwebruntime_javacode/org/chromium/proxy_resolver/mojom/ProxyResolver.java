package org.chromium.proxy_resolver.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface ProxyResolver extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ProxyResolver {
    }

    public static final Manager MANAGER;

    static {
        ProxyResolver.MANAGER = ProxyResolver_Internal.MANAGER;
    }

    void getProxyForUrl(Url arg1, ProxyResolverRequestClient arg2);
}

