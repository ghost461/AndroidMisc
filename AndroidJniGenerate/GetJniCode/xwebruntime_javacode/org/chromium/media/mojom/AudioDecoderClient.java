package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioDecoderClient extends Interface {
    public interface Proxy extends AudioDecoderClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioDecoderClient.MANAGER = AudioDecoderClient_Internal.MANAGER;
    }

    void onBufferDecoded(AudioBuffer arg1);
}

