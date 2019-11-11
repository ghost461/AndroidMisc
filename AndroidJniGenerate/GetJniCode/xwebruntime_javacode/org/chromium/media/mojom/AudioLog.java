package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface AudioLog extends Interface {
    public interface Proxy extends AudioLog, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioLog.MANAGER = AudioLog_Internal.MANAGER;
    }

    void onClosed();

    void onCreated(AudioParameters arg1, String arg2);

    void onError();

    void onLogMessage(String arg1);

    void onSetVolume(double arg1);

    void onStarted();

    void onStopped();
}

