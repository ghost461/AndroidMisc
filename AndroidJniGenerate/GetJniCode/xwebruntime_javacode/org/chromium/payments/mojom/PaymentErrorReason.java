package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class PaymentErrorReason {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NOT_SUPPORTED = 2;
    public static final int UNKNOWN = 0;
    public static final int USER_CANCEL = 1;

    private PaymentErrorReason() {
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
        if(PaymentErrorReason.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

