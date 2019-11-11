package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface WebSocketClient extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, WebSocketClient {
    }

    public static final Manager MANAGER;

    static {
        WebSocketClient.MANAGER = WebSocketClient_Internal.MANAGER;
    }

    void onAddChannelResponse(String arg1, String arg2);

    void onClosingHandshake();

    void onDataFrame(boolean arg1, int arg2, byte[] arg3);

    void onDropChannel(boolean arg1, short arg2, String arg3);

    void onFailChannel(String arg1);

    void onFinishOpeningHandshake(WebSocketHandshakeResponse arg1);

    void onFlowControl(long arg1);

    void onStartOpeningHandshake(WebSocketHandshakeRequest arg1);
}

