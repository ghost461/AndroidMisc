package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.ReadOnlyBuffer;
import org.chromium.net.interfaces.IpEndPoint;

public interface UdpSocketReceiver extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, UdpSocketReceiver {
    }

    public static final Manager MANAGER;

    static {
        UdpSocketReceiver.MANAGER = UdpSocketReceiver_Internal.MANAGER;
    }

    void onReceived(int arg1, IpEndPoint arg2, ReadOnlyBuffer arg3);
}

