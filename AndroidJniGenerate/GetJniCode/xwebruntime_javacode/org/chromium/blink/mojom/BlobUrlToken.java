package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface BlobUrlToken extends Interface {
    public interface GetTokenResponse extends Callback1 {
    }

    public interface Proxy extends BlobUrlToken, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        BlobUrlToken.MANAGER = BlobUrlToken_Internal.MANAGER;
    }

    void clone(InterfaceRequest arg1);

    void getToken(GetTokenResponse arg1);
}

