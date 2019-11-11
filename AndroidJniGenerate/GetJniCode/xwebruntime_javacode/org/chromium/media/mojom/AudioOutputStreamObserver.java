package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioOutputStreamObserver extends Interface {
    public interface Proxy extends AudioOutputStreamObserver, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioOutputStreamObserver.MANAGER = AudioOutputStreamObserver_Internal.MANAGER;
    }

    void didChangeAudibleState(boolean arg1);

    void didStartPlaying();

    void didStopPlaying();
}

