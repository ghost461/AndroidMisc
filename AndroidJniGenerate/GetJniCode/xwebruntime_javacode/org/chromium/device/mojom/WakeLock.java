package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface WakeLock extends Interface {
    public interface ChangeTypeResponse extends Callback1 {
    }

    public interface HasWakeLockForTestsResponse extends Callback1 {
    }

    public interface Proxy extends WakeLock, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        WakeLock.MANAGER = WakeLock_Internal.MANAGER;
    }

    void addClient(InterfaceRequest arg1);

    void cancelWakeLock();

    void changeType(int arg1, ChangeTypeResponse arg2);

    void hasWakeLockForTests(HasWakeLockForTestsResponse arg1);

    void requestWakeLock();
}

