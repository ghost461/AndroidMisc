package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class WakeLockType {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PREVENT_APP_SUSPENSION = 0;
    public static final int PREVENT_DISPLAY_SLEEP = 1;
    public static final int PREVENT_DISPLAY_SLEEP_ALLOW_DIMMING = 2;

    private WakeLockType() {
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
        if(WakeLockType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

