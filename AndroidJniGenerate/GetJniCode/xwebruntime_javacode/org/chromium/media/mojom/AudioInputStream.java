package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioInputStream extends Interface {
    public interface Proxy extends AudioInputStream, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioInputStream.MANAGER = AudioInputStream_Internal.MANAGER;
    }

    void record();

    void setVolume(double arg1);
}

