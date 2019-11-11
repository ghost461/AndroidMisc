package org.chromium.media.mojom;

import org.chromium.gfx.mojom.Rect;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AndroidOverlay extends Interface {
    public interface Proxy extends AndroidOverlay, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AndroidOverlay.MANAGER = AndroidOverlay_Internal.MANAGER;
    }

    void scheduleLayout(Rect arg1);
}

