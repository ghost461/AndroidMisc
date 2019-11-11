package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioInputStreamObserver extends Interface {
    public interface Proxy extends AudioInputStreamObserver, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioInputStreamObserver.MANAGER = AudioInputStreamObserver_Internal.MANAGER;
    }

    void didStartRecording();
}

