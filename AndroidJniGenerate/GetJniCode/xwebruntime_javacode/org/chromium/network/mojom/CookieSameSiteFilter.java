package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CookieSameSiteFilter {
    public static final int DO_NOT_INCLUDE = 2;
    public static final int INCLUDE_LAX = 1;
    public static final int INCLUDE_STRICT_AND_LAX = 0;
    private static final boolean IS_EXTENSIBLE = false;

    private CookieSameSiteFilter() {
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
        if(CookieSameSiteFilter.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

