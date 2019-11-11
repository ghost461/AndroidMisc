package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface MediaLog extends Interface {
    public interface Proxy extends MediaLog, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        MediaLog.MANAGER = MediaLog_Internal.MANAGER;
    }

    void addEvent(MediaLogEvent arg1);
}

