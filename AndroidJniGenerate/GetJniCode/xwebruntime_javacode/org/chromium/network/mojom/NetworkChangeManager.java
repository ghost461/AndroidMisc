package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface NetworkChangeManager extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, NetworkChangeManager {
    }

    public static final Manager MANAGER;

    static {
        NetworkChangeManager.MANAGER = NetworkChangeManager_Internal.MANAGER;
    }

    void requestNotifications(NetworkChangeManagerClient arg1);
}

