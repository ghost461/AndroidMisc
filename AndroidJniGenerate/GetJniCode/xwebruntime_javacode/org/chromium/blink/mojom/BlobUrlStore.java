package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.url.mojom.Url;

public interface BlobUrlStore extends Interface {
    public interface Proxy extends BlobUrlStore, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RegisterResponse extends Callback0 {
    }

    public interface ResolveResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        BlobUrlStore.MANAGER = BlobUrlStore_Internal.MANAGER;
    }

    void register(Blob arg1, Url arg2, RegisterResponse arg3);

    void resolve(Url arg1, ResolveResponse arg2);

    void resolveAsUrlLoaderFactory(Url arg1, InterfaceRequest arg2);

    void resolveForNavigation(Url arg1, InterfaceRequest arg2);

    void revoke(Url arg1);
}

