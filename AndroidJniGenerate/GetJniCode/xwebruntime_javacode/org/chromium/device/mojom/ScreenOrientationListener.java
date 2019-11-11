package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ScreenOrientationListener extends Interface {
    public interface IsAutoRotateEnabledByUserResponse extends Callback1 {
    }

    public interface Proxy extends ScreenOrientationListener, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ScreenOrientationListener.MANAGER = ScreenOrientationListener_Internal.MANAGER;
    }

    void isAutoRotateEnabledByUser(IsAutoRotateEnabledByUserResponse arg1);

    void start();

    void stop();
}

