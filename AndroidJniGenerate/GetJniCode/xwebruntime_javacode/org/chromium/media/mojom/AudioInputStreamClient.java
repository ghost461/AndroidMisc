package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioInputStreamClient extends Interface {
    public interface Proxy extends AudioInputStreamClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioInputStreamClient.MANAGER = AudioInputStreamClient_Internal.MANAGER;
    }

    void onError();

    void onMutedStateChanged(boolean arg1);
}

