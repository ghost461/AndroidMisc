package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SensorCreationResult {
    public static final int ERROR_NOT_ALLOWED = 2;
    public static final int ERROR_NOT_AVAILABLE = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SUCCESS;

    private SensorCreationResult() {
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
        if(SensorCreationResult.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

