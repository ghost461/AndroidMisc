package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class NfcErrorType {
    public static final int CANNOT_CANCEL = 7;
    public static final int DEVICE_DISABLED = 2;
    public static final int INVALID_MESSAGE = 4;
    public static final int IO_ERROR = 8;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NOT_FOUND = 3;
    public static final int NOT_SUPPORTED = 1;
    public static final int OPERATION_CANCELLED = 5;
    public static final int SECURITY = 0;
    public static final int TIMER_EXPIRED = 6;

    private NfcErrorType() {
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
            case 7: 
            case 8: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(NfcErrorType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

