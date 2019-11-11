package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.UnguessableToken;

public interface VideoDecoderClient extends Interface {
    public interface Proxy extends VideoDecoderClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoDecoderClient.MANAGER = VideoDecoderClient_Internal.MANAGER;
    }

    void onVideoFrameDecoded(VideoFrame arg1, boolean arg2, UnguessableToken arg3);

    void requestOverlayInfo(boolean arg1);
}

