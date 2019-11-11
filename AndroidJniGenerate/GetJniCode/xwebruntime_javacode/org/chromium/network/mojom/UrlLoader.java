package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface UrlLoader extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, UrlLoader {
    }

    public static final int CLIENT_DISCONNECT_REASON = 1;
    public static final Manager MANAGER;

    static {
        UrlLoader.MANAGER = UrlLoader_Internal.MANAGER;
    }

    void followRedirect();

    void pauseReadingBodyFromNet();

    void proceedWithResponse();

    void resumeReadingBodyFromNet();

    void setPriority(int arg1, int arg2);
}

