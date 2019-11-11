package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface NetworkChangeManagerClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, NetworkChangeManagerClient {
    }

    public static final Manager MANAGER;

    static {
        NetworkChangeManagerClient.MANAGER = NetworkChangeManagerClient_Internal.MANAGER;
    }

    void onInitialConnectionType(int arg1);

    void onNetworkChanged(int arg1);
}

