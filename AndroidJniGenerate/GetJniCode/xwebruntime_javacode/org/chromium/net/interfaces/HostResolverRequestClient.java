package org.chromium.net.interfaces;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface HostResolverRequestClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, HostResolverRequestClient {
    }

    public static final Manager MANAGER;

    static {
        HostResolverRequestClient.MANAGER = HostResolverRequestClient_Internal.MANAGER;
    }

    void reportResult(int arg1, AddressList arg2);
}

