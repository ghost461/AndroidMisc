package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface MediaSessionService extends Interface {
    public interface Proxy extends MediaSessionService, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        MediaSessionService.MANAGER = MediaSessionService_Internal.MANAGER;
    }

    void disableAction(int arg1);

    void enableAction(int arg1);

    void setClient(MediaSessionClient arg1);

    void setMetadata(MediaMetadata arg1);

    void setPlaybackState(int arg1);
}

