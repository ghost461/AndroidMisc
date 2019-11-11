package org.chromium.webauth.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AuthenticatorAttachment {
    public static final int CROSS_PLATFORM = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NO_PREFERENCE = 0;
    public static final int PLATFORM = 1;

    private AuthenticatorAttachment() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(AuthenticatorAttachment.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

