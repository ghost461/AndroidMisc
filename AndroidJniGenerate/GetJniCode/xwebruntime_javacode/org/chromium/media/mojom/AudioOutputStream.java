package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioOutputStream extends Interface {
    public interface Proxy extends AudioOutputStream, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioOutputStream.MANAGER = AudioOutputStream_Internal.MANAGER;
    }

    void pause();

    void play();

    void setVolume(double arg1);
}

