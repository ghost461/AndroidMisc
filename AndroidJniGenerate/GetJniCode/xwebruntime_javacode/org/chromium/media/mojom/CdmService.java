package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo_base.mojom.FilePath;
import org.chromium.service_manager.mojom.InterfaceProvider;

public interface CdmService extends Interface {
    public interface Proxy extends CdmService, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        CdmService.MANAGER = CdmService_Internal.MANAGER;
    }

    void createCdmFactory(InterfaceRequest arg1, InterfaceProvider arg2);

    void loadCdm(FilePath arg1);
}

