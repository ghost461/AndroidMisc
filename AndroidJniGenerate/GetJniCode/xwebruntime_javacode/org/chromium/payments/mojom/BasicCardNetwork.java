package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class BasicCardNetwork {
    public static final int AMEX = 0;
    public static final int DINERS = 1;
    public static final int DISCOVER = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int JCB = 3;
    public static final int MASTERCARD = 4;
    public static final int MIR = 5;
    public static final int UNIONPAY = 6;
    public static final int VISA = 7;

    private BasicCardNetwork() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(BasicCardNetwork.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

