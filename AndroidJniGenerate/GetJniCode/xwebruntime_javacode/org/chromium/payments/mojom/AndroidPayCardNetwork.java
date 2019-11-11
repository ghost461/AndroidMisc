package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AndroidPayCardNetwork {
    public static final int AMEX = 0;
    public static final int DISCOVER = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MASTERCARD = 2;
    public static final int VISA = 3;

    private AndroidPayCardNetwork() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(AndroidPayCardNetwork.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

