package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CookiePriority {
    public static final int HIGH = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LOW = 0;
    public static final int MEDIUM = 1;

    private CookiePriority() {
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
        if(CookiePriority.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

