package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CookieMatchType {
    public static final int EQUALS = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int STARTS_WITH = 1;

    private CookieMatchType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(CookieMatchType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

