package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AndroidPayTokenization {
    public static final int GATEWAY_TOKEN = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NETWORK_TOKEN = 2;
    public static final int UNSPECIFIED;

    private AndroidPayTokenization() {
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
        if(AndroidPayTokenization.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

