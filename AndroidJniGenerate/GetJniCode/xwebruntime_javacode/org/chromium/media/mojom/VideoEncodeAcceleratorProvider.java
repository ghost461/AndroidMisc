package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface VideoEncodeAcceleratorProvider extends Interface {
    public interface Proxy extends VideoEncodeAcceleratorProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoEncodeAcceleratorProvider.MANAGER = VideoEncodeAcceleratorProvider_Internal.MANAGER;
    }

    void createVideoEncodeAccelerator(InterfaceRequest arg1);
}

