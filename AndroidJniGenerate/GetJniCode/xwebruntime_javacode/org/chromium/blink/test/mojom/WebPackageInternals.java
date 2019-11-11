package org.chromium.blink.test.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.Time;

public interface WebPackageInternals extends Interface {
    public interface Proxy extends WebPackageInternals, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface SetSignedExchangeVerificationTimeResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        WebPackageInternals.MANAGER = WebPackageInternals_Internal.MANAGER;
    }

    void setSignedExchangeVerificationTime(Time arg1, SetSignedExchangeVerificationTimeResponse arg2);
}

