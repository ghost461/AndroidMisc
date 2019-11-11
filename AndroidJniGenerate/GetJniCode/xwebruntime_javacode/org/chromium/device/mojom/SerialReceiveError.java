package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SerialReceiveError {
    public static final int BREAK = 4;
    public static final int BUFFER_OVERFLOW = 7;
    public static final int DEVICE_LOST = 3;
    public static final int DISCONNECTED = 1;
    public static final int FRAME_ERROR = 5;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONE = 0;
    public static final int OVERRUN = 6;
    public static final int PARITY_ERROR = 8;
    public static final int SYSTEM_ERROR = 9;
    public static final int TIMEOUT = 2;

    private SerialReceiveError() {
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
            case 8: 
            case 9: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(SerialReceiveError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

