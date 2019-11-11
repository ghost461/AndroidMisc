package org.chromium.proxy_resolver.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface ProxyResolverFactory extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ProxyResolverFactory {
    }

    public static final Manager MANAGER;

    static {
        ProxyResolverFactory.MANAGER = ProxyResolverFactory_Internal.MANAGER;
    }

    void createResolver(String arg1, InterfaceRequest arg2, ProxyResolverFactoryRequestClient arg3);
}

