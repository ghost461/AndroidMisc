package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceWorkerRegistrationObjectHost extends Interface {
    public interface EnableNavigationPreloadResponse extends Callback2 {
    }

    public interface GetNavigationPreloadStateResponse extends Callback3 {
    }

    public interface Proxy extends ServiceWorkerRegistrationObjectHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface SetNavigationPreloadHeaderResponse extends Callback2 {
    }

    public interface UnregisterResponse extends Callback2 {
    }

    public interface UpdateResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerRegistrationObjectHost.MANAGER = ServiceWorkerRegistrationObjectHost_Internal.MANAGER;
    }

    void enableNavigationPreload(boolean arg1, EnableNavigationPreloadResponse arg2);

    void getNavigationPreloadState(GetNavigationPreloadStateResponse arg1);

    void setNavigationPreloadHeader(String arg1, SetNavigationPreloadHeaderResponse arg2);

    void unregister(UnregisterResponse arg1);

    void update(UpdateResponse arg1);
}

