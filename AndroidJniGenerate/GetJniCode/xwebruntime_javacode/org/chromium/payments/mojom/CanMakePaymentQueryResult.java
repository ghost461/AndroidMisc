package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CanMakePaymentQueryResult {
    public static final int CANNOT_MAKE_PAYMENT = 1;
    public static final int CAN_MAKE_PAYMENT = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int QUERY_QUOTA_EXCEEDED = 2;
    public static final int WARNING_CANNOT_MAKE_PAYMENT = 4;
    public static final int WARNING_CAN_MAKE_PAYMENT = 3;

    private CanMakePaymentQueryResult() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(CanMakePaymentQueryResult.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

