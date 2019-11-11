package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface DownloadedTempFile extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, DownloadedTempFile {
    }

    public static final Manager MANAGER;

    static {
        DownloadedTempFile.MANAGER = DownloadedTempFile_Internal.MANAGER;
    }
}

