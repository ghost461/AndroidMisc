package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface FrameResourceReleaser extends Interface {
    public interface Proxy extends FrameResourceReleaser, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        FrameResourceReleaser.MANAGER = FrameResourceReleaser_Internal.MANAGER;
    }
}

