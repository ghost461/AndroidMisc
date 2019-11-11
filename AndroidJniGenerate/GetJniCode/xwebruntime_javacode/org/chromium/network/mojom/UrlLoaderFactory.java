package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface UrlLoaderFactory extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, UrlLoaderFactory {
    }

    public static final Manager MANAGER;

    static {
        UrlLoaderFactory.MANAGER = UrlLoaderFactory_Internal.MANAGER;
    }

    void clone(InterfaceRequest arg1);

    void createLoaderAndStart(InterfaceRequest arg1, int arg2, int arg3, int arg4, UrlRequest arg5, UrlLoaderClient arg6, MutableNetworkTrafficAnnotationTag arg7);
}

