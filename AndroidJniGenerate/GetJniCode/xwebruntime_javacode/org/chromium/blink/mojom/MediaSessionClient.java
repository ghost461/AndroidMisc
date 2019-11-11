package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface MediaSessionClient extends Interface {
    public interface Proxy extends MediaSessionClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        MediaSessionClient.MANAGER = MediaSessionClient_Internal.MANAGER;
    }

    void didReceiveAction(int arg1);
}

