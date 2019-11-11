package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SerialStopBits {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONE = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;

    private SerialStopBits() {
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
        if(SerialStopBits.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

