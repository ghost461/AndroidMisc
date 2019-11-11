package org.chromium.mojo_base.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class TextDirection {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LEFT_TO_RIGHT = 2;
    public static final int RIGHT_TO_LEFT = 1;
    public static final int UNKNOWN_DIRECTION;

    private TextDirection() {
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
        if(TextDirection.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

