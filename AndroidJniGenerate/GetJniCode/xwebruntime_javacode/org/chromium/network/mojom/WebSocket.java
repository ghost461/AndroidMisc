package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface WebSocket extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, WebSocket {
    }

    public static final int INSUFFICIENT_RESOURCES = 1;
    public static final Manager MANAGER;

    static {
        WebSocket.MANAGER = WebSocket_Internal.MANAGER;
    }

    void addChannelRequest(Url arg1, String[] arg2, Url arg3, String arg4, WebSocketClient arg5);

    void sendFlowControl(long arg1);

    void sendFrame(boolean arg1, int arg2, byte[] arg3);

    void startClosingHandshake(short arg1, String arg2);
}

