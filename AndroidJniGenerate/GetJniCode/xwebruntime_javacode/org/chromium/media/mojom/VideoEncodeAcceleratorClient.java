package org.chromium.media.mojom;

import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.TimeDelta;

public interface VideoEncodeAcceleratorClient extends Interface {
    public interface Proxy extends VideoEncodeAcceleratorClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoEncodeAcceleratorClient.MANAGER = VideoEncodeAcceleratorClient_Internal.MANAGER;
    }

    void bitstreamBufferReady(int arg1, int arg2, boolean arg3, TimeDelta arg4);

    void notifyError(int arg1);

    void requireBitstreamBuffers(int arg1, Size arg2, int arg3);
}

