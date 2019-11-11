package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ProvisionFetcher extends Interface {
    public interface Proxy extends ProvisionFetcher, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RetrieveResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        ProvisionFetcher.MANAGER = ProvisionFetcher_Internal.MANAGER;
    }

    void retrieve(String arg1, String arg2, RetrieveResponse arg3);
}

