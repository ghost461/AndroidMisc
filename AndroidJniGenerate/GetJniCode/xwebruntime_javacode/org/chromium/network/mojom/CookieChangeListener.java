package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface CookieChangeListener extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, CookieChangeListener {
    }

    public static final Manager MANAGER;

    static {
        CookieChangeListener.MANAGER = CookieChangeListener_Internal.MANAGER;
    }

    void onCookieChange(CanonicalCookie arg1, int arg2);
}

