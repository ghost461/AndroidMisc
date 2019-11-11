package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface ContentDecryptionModuleClient extends Interface {
    public interface Proxy extends ContentDecryptionModuleClient, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        ContentDecryptionModuleClient.MANAGER = ContentDecryptionModuleClient_Internal.MANAGER;
    }

    void onSessionClosed(String arg1);

    void onSessionExpirationUpdate(String arg1, double arg2);

    void onSessionKeysChange(String arg1, boolean arg2, CdmKeyInformation[] arg3);

    void onSessionMessage(String arg1, int arg2, byte[] arg3);
}

