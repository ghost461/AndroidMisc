package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class QuotaStatusCode {
    public static final int ERROR_ABORT = 17;
    public static final int ERROR_INVALID_ACCESS = 13;
    public static final int ERROR_INVALID_MODIFICATION = 11;
    public static final int ERROR_NOT_SUPPORTED = 7;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OK = 0;
    public static final int UNKNOWN = -1;

    private QuotaStatusCode() {
        super();
    }

    public static boolean isKnownValue(int arg1) {
        if(arg1 != 7 && arg1 != 11 && arg1 != 13 && arg1 != 17) {
            switch(arg1) {
                case -1: 
                case 0: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    public static void validate(int arg1) {
        if(QuotaStatusCode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

