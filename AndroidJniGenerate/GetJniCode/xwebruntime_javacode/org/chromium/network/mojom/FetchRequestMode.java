package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FetchRequestMode {
    public static final int CORS = 2;
    public static final int CORS_WITH_FORCED_PREFLIGHT = 3;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NAVIGATE = 4;
    public static final int NO_CORS = 1;
    public static final int SAME_ORIGIN;

    private FetchRequestMode() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(FetchRequestMode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

