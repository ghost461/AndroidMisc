package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface NfcProvider extends Interface {
    public interface Proxy extends NfcProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        NfcProvider.MANAGER = NfcProvider_Internal.MANAGER;
    }

    void getNfcForHost(int arg1, InterfaceRequest arg2);
}

