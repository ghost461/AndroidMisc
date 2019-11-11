package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CookieChangeCause {
    public static final int EVICTED = 5;
    public static final int EXPIRED = 4;
    public static final int EXPIRED_OVERWRITE = 6;
    public static final int EXPLICIT = 1;
    public static final int INSERTED = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OVERWRITE = 3;
    public static final int UNKNOWN_DELETION = 2;

    private CookieChangeCause() {
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
            case 6: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(CookieChangeCause.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

