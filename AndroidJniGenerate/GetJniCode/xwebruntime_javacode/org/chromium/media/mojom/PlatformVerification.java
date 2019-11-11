package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback4;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface PlatformVerification extends Interface {
    public interface ChallengePlatformResponse extends Callback4 {
    }

    public interface GetStorageIdResponse extends Callback2 {
    }

    public interface Proxy extends PlatformVerification, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        PlatformVerification.MANAGER = PlatformVerification_Internal.MANAGER;
    }

    void challengePlatform(String arg1, String arg2, ChallengePlatformResponse arg3);

    void getStorageId(int arg1, GetStorageIdResponse arg2);
}

