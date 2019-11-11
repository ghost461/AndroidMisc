package org.chromium.webshare.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface ShareService extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ShareService {
    }

    public interface ShareResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        ShareService.MANAGER = ShareService_Internal.MANAGER;
    }

    void share(String arg1, String arg2, Url arg3, ShareResponse arg4);
}

