package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface NetworkService extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, NetworkService {
    }

    public static final Manager MANAGER;

    static {
        NetworkService.MANAGER = NetworkService_Internal.MANAGER;
    }

    void createNetworkContext(InterfaceRequest arg1, NetworkContextParams arg2);

    void disableQuic();

    void getNetworkChangeManager(InterfaceRequest arg1);

    void setClient(NetworkServiceClient arg1);

    void setRawHeadersAccess(int arg1, boolean arg2);
}

