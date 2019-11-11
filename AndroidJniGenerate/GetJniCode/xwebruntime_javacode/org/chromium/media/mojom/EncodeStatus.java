package org.chromium.media.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class EncodeStatus {
    public static final int ENCODE_OK = 0;
    public static final int HW_JPEG_ENCODE_NOT_SUPPORTED = 1;
    public static final int INACCESSIBLE_OUTPUT_BUFFER = 4;
    public static final int INVALID_ARGUMENT = 3;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PARSE_IMAGE_FAILED = 5;
    public static final int PLATFORM_FAILURE = 6;
    public static final int THREAD_CREATION_FAILED = 2;

    private EncodeStatus() {
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
        if(EncodeStatus.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

