package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface KeySystemSupport extends Interface {
    public interface IsKeySystemSupportedResponse extends Callback3 {
    }

    public interface Proxy extends KeySystemSupport, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        KeySystemSupport.MANAGER = KeySystemSupport_Internal.MANAGER;
    }

    void isKeySystemSupported(String arg1, IsKeySystemSupportedResponse arg2);
}

