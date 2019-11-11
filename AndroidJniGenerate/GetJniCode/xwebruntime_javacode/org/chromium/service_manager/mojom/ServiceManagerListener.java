package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ServiceManagerListener extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ServiceManagerListener {
    }

    public static final Manager MANAGER;

    static {
        ServiceManagerListener.MANAGER = ServiceManagerListener_Internal.MANAGER;
    }

    void onInit(RunningServiceInfo[] arg1);

    void onServiceCreated(RunningServiceInfo arg1);

    void onServiceFailedToStart(Identity arg1);

    void onServicePidReceived(Identity arg1, int arg2);

    void onServiceStarted(Identity arg1, int arg2);

    void onServiceStopped(Identity arg1);
}

