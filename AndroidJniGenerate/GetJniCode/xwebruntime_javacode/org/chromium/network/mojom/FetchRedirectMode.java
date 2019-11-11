package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FetchRedirectMode {
    public static final int ERROR = 1;
    public static final int FOLLOW = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MANUAL = 2;

    private FetchRedirectMode() {
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
        if(FetchRedirectMode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

