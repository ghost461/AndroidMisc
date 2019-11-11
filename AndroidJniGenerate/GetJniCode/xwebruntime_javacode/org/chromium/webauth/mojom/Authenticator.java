package org.chromium.webauth.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface Authenticator extends Interface {
    public interface GetAssertionResponse extends Callback2 {
    }

    public interface MakeCredentialResponse extends Callback2 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, Authenticator {
    }

    public static final Manager MANAGER;

    static {
        Authenticator.MANAGER = Authenticator_Internal.MANAGER;
    }

    void getAssertion(PublicKeyCredentialRequestOptions arg1, GetAssertionResponse arg2);

    void makeCredential(PublicKeyCredentialCreationOptions arg1, MakeCredentialResponse arg2);
}

