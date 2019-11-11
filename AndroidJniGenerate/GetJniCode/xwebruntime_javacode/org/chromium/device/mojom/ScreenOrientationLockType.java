package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ScreenOrientationLockType {
    public static final int ANY = 5;
    public static final int DEFAULT = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LANDSCAPE = 6;
    public static final int LANDSCAPE_PRIMARY = 3;
    public static final int LANDSCAPE_SECONDARY = 4;
    public static final int NATURAL = 8;
    public static final int PORTRAIT = 7;
    public static final int PORTRAIT_PRIMARY = 1;
    public static final int PORTRAIT_SECONDARY = 2;

    private ScreenOrientationLockType() {
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
        if(ScreenOrientationLockType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

