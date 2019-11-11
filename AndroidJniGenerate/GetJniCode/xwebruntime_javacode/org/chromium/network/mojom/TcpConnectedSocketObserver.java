package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TcpConnectedSocketObserver extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, TcpConnectedSocketObserver {
    }

    public static final Manager MANAGER;

    static {
        TcpConnectedSocketObserver.MANAGER = TcpConnectedSocketObserver_Internal.MANAGER;
    }

    void onReadError(int arg1);

    void onWriteError(int arg1);
}

