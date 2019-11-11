package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface CdmFactory extends Interface {
    public interface Proxy extends CdmFactory, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        CdmFactory.MANAGER = CdmFactory_Internal.MANAGER;
    }

    void createCdm(String arg1, InterfaceRequest arg2);
}

