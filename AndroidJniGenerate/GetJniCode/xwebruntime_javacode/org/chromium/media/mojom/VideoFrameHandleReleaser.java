package org.chromium.media.mojom;

import org.chromium.gpu.mojom.SyncToken;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.UnguessableToken;

public interface VideoFrameHandleReleaser extends Interface {
    public interface Proxy extends VideoFrameHandleReleaser, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoFrameHandleReleaser.MANAGER = VideoFrameHandleReleaser_Internal.MANAGER;
    }

    void releaseVideoFrame(UnguessableToken arg1, SyncToken arg2);
}

