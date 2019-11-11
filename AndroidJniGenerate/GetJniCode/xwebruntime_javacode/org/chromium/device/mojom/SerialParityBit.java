package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SerialParityBit {
    public static final int EVEN = 3;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONE = 0;
    public static final int NO_PARITY = 1;
    public static final int ODD = 2;

    private SerialParityBit() {
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
        if(SerialParityBit.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

