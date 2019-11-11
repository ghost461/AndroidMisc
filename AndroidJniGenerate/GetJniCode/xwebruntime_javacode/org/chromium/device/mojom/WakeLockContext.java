package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface WakeLockContext extends Interface {
    public interface Proxy extends WakeLockContext, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        WakeLockContext.MANAGER = WakeLockContext_Internal.MANAGER;
    }

    void getWakeLock(int arg1, int arg2, String arg3, InterfaceRequest arg4);
}

