package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ColorChooser extends Interface {
    public interface Proxy extends ColorChooser, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ColorChooser.MANAGER = ColorChooser_Internal.MANAGER;
    }

    void setSelectedColor(int arg1);
}

