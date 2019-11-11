package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ProgressClient extends Interface {
    public interface Proxy extends ProgressClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ProgressClient.MANAGER = ProgressClient_Internal.MANAGER;
    }

    void onProgress(long arg1);
}

