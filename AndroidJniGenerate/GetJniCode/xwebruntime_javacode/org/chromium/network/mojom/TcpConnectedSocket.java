package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface TcpConnectedSocket extends Interface {
    public interface GetLocalAddressResponse extends Callback2 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, TcpConnectedSocket {
    }

    public static final Manager MANAGER;

    static {
        TcpConnectedSocket.MANAGER = TcpConnectedSocket_Internal.MANAGER;
    }

    void getLocalAddress(GetLocalAddressResponse arg1);
}

