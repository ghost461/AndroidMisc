package org.chromium.webauth.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AttestationConveyancePreference {
    public static final int DIRECT = 2;
    public static final int INDIRECT = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONE;

    private AttestationConveyancePreference() {
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
        if(AttestationConveyancePreference.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

