package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback5;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TcpServerSocket extends Interface {
    public interface AcceptResponse extends Callback5 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, TcpServerSocket {
    }

    public static final Manager MANAGER;

    static {
        TcpServerSocket.MANAGER = TcpServerSocket_Internal.MANAGER;
    }

    void accept(TcpConnectedSocketObserver arg1, AcceptResponse arg2);
}

