package org.chromium.blink.mojom.document_metadata;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface CopylessPaste extends Interface {
    public interface GetEntitiesResponse extends Callback1 {
    }

    public interface Proxy extends CopylessPaste, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        CopylessPaste.MANAGER = CopylessPaste_Internal.MANAGER;
    }

    void getEntities(GetEntitiesResponse arg1);
}

