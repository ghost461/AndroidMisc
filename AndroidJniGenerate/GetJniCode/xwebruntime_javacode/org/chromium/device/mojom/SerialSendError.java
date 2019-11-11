package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SerialSendError {
    public static final int DISCONNECTED = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONE = 0;
    public static final int PENDING = 2;
    public static final int SYSTEM_ERROR = 4;
    public static final int TIMEOUT = 3;

    private SerialSendError() {
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
        if(SerialSendError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

