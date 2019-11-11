package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface UnhandledTapNotifier extends Interface {
    public interface Proxy extends UnhandledTapNotifier, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        UnhandledTapNotifier.MANAGER = UnhandledTapNotifier_Internal.MANAGER;
    }

    void showUnhandledTapUiIfNeeded(UnhandledTapInfo arg1);
}

