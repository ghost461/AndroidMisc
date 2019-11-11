package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface VibrationManager extends Interface {
    public interface CancelResponse extends Callback0 {
    }

    public interface Proxy extends VibrationManager, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface VibrateResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        VibrationManager.MANAGER = VibrationManager_Internal.MANAGER;
    }

    void cancel(CancelResponse arg1);

    void vibrate(long arg1, VibrateResponse arg2);
}

