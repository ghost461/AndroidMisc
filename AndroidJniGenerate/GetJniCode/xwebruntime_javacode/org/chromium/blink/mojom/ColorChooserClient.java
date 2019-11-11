package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ColorChooserClient extends Interface {
    public interface Proxy extends ColorChooserClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ColorChooserClient.MANAGER = ColorChooserClient_Internal.MANAGER;
    }

    void didChooseColor(int arg1);
}

