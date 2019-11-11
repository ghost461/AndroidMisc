package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class RequestPriority {
    public static final int HIGHEST = 5;
    public static final int IDLE = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LOW = 3;
    public static final int LOWEST = 2;
    public static final int MEDIUM = 4;
    public static final int THROTTLED;

    private RequestPriority() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(RequestPriority.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

