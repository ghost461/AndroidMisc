package org.chromium.device.mojom;

import java.util.Map;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface FingerprintObserver extends Interface {
    public interface Proxy extends FingerprintObserver, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        FingerprintObserver.MANAGER = FingerprintObserver_Internal.MANAGER;
    }

    void onAuthScanDone(int arg1, Map arg2);

    void onEnrollScanDone(int arg1, boolean arg2, int arg3);

    void onRestarted();

    void onSessionFailed();
}

