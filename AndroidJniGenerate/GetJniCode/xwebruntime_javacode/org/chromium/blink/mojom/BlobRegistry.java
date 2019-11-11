package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.AssociatedInterfaceRequestNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.url.mojom.Origin;

public interface BlobRegistry extends Interface {
    public interface GetBlobFromUuidResponse extends Callback0 {
    }

    public interface Proxy extends BlobRegistry, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RegisterFromStreamResponse extends Callback1 {
    }

    public interface RegisterResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        BlobRegistry.MANAGER = BlobRegistry_Internal.MANAGER;
    }

    void getBlobFromUuid(InterfaceRequest arg1, String arg2, GetBlobFromUuidResponse arg3);

    void register(InterfaceRequest arg1, String arg2, String arg3, String arg4, DataElement[] arg5, RegisterResponse arg6);

    void registerFromStream(String arg1, String arg2, long arg3, ConsumerHandle arg4, AssociatedInterfaceNotSupported arg5, RegisterFromStreamResponse arg6);

    void urlStoreForOrigin(Origin arg1, AssociatedInterfaceRequestNotSupported arg2);
}

