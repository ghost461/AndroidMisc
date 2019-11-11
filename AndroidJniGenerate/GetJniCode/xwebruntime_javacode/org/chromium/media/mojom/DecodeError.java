package org.chromium.media.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class DecodeError {
    public static final int INVALID_ARGUMENT = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NO_ERRORS = 0;
    public static final int PARSE_JPEG_FAILED = 3;
    public static final int PLATFORM_FAILURE = 5;
    public static final int UNREADABLE_INPUT = 2;
    public static final int UNSUPPORTED_JPEG = 4;

    private DecodeError() {
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
        if(DecodeError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

