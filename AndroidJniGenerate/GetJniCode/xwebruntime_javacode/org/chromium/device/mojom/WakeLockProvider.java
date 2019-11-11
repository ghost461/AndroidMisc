package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface WakeLockProvider extends Interface {
    public interface Proxy extends WakeLockProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        WakeLockProvider.MANAGER = WakeLockProvider_Internal.MANAGER;
    }

    void getWakeLockContextForId(int arg1, InterfaceRequest arg2);

    void getWakeLockWithoutContext(int arg1, int arg2, String arg3, InterfaceRequest arg4);
}

