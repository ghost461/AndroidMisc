package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CookieSameSite {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAX_MODE = 1;
    public static final int NO_RESTRICTION = 0;
    public static final int STRICT_MODE = 2;

    private CookieSameSite() {
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
        if(CookieSameSite.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

