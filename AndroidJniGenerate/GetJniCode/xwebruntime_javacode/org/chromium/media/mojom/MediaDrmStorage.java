package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface MediaDrmStorage extends Interface {
    public interface InitializeResponse extends Callback1 {
    }

    public interface LoadPersistentSessionResponse extends Callback1 {
    }

    public interface OnProvisionedResponse extends Callback1 {
    }

    public interface Proxy extends MediaDrmStorage, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface RemovePersistentSessionResponse extends Callback1 {
    }

    public interface SavePersistentSessionResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        MediaDrmStorage.MANAGER = MediaDrmStorage_Internal.MANAGER;
    }

    void initialize(InitializeResponse arg1);

    void loadPersistentSession(String arg1, LoadPersistentSessionResponse arg2);

    void onProvisioned(OnProvisionedResponse arg1);

    void removePersistentSession(String arg1, RemovePersistentSessionResponse arg2);

    void savePersistentSession(String arg1, SessionData arg2, SavePersistentSessionResponse arg3);
}

