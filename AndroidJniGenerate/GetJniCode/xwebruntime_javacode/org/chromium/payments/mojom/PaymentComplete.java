package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class PaymentComplete {
    public static final int FAIL = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SUCCESS = 1;
    public static final int UNKNOWN = 2;

    private PaymentComplete() {
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
        if(PaymentComplete.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

