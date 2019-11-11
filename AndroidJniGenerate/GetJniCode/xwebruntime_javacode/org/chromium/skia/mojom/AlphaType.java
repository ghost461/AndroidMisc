package org.chromium.skia.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AlphaType {
    public static final int ALPHA_TYPE_OPAQUE = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PREMUL = 2;
    public static final int UNKNOWN = 0;
    public static final int UNPREMUL = 3;

    private AlphaType() {
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
        if(AlphaType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

