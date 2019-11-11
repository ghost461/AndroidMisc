package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ScreenOrientationLockResult {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SCREEN_ORIENTATION_LOCK_RESULT_ERROR_CANCELED = 3;
    public static final int SCREEN_ORIENTATION_LOCK_RESULT_ERROR_FULLSCREEN_REQUIRED = 2;
    public static final int SCREEN_ORIENTATION_LOCK_RESULT_ERROR_NOT_AVAILABLE = 1;
    public static final int SCREEN_ORIENTATION_LOCK_RESULT_SUCCESS;

    private ScreenOrientationLockResult() {
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
        if(ScreenOrientationLockResult.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

