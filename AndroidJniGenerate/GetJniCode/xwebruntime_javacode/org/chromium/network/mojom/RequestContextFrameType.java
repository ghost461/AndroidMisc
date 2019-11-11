package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class RequestContextFrameType {
    public static final int AUXILIARY = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NESTED = 1;
    public static final int NONE = 2;
    public static final int TOP_LEVEL = 3;

    private RequestContextFrameType() {
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
        if(RequestContextFrameType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

