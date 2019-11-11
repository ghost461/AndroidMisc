package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioOutputStreamClient extends Interface {
    public interface Proxy extends AudioOutputStreamClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioOutputStreamClient.MANAGER = AudioOutputStreamClient_Internal.MANAGER;
    }

    void onError();
}

