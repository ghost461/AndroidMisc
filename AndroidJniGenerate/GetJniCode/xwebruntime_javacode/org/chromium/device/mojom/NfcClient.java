package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface NfcClient extends Interface {
    public interface Proxy extends NfcClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        NfcClient.MANAGER = NfcClient_Internal.MANAGER;
    }

    void onWatch(int[] arg1, NfcMessage arg2);
}

