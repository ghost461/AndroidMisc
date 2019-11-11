package org.chromium.webauth.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class PublicKeyCredentialType {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PUBLIC_KEY;

    private PublicKeyCredentialType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        if(arg0 != 0) {
            return 0;
        }

        return 1;
    }

    public static void validate(int arg1) {
        if(PublicKeyCredentialType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

