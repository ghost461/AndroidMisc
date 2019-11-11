package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ScreenOrientation extends Interface {
    public interface LockOrientationResponse extends Callback1 {
    }

    public interface Proxy extends ScreenOrientation, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ScreenOrientation.MANAGER = ScreenOrientation_Internal.MANAGER;
    }

    void lockOrientation(int arg1, LockOrientationResponse arg2);

    void unlockOrientation();
}

