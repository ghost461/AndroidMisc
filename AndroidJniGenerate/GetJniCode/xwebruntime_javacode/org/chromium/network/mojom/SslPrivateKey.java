package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface SslPrivateKey extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, SslPrivateKey {
    }

    public interface SignResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        SslPrivateKey.MANAGER = SslPrivateKey_Internal.MANAGER;
    }

    void sign(short arg1, byte[] arg2, SignResponse arg3);
}

