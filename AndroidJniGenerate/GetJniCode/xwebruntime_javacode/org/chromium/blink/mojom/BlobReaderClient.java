package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface BlobReaderClient extends Interface {
    public interface Proxy extends BlobReaderClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        BlobReaderClient.MANAGER = BlobReaderClient_Internal.MANAGER;
    }

    void onCalculatedSize(long arg1, long arg2);

    void onComplete(int arg1, long arg2);
}

