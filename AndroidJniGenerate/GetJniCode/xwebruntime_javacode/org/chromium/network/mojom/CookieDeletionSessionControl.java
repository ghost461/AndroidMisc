package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CookieDeletionSessionControl {
    public static final int IGNORE_CONTROL = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PERSISTENT_COOKIES = 2;
    public static final int SESSION_COOKIES = 1;

    private CookieDeletionSessionControl() {
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
        if(CookieDeletionSessionControl.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

