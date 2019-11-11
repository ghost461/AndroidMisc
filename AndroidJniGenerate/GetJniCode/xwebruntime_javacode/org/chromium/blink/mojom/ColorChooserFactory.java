package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface ColorChooserFactory extends Interface {
    public interface Proxy extends ColorChooserFactory, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ColorChooserFactory.MANAGER = ColorChooserFactory_Internal.MANAGER;
    }

    void openColorChooser(InterfaceRequest arg1, ColorChooserClient arg2, int arg3, ColorSuggestion[] arg4);
}

