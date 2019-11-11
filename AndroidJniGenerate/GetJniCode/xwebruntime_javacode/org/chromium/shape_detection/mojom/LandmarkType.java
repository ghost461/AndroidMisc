package org.chromium.shape_detection.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class LandmarkType {
    public static final int EYE = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MOUTH;

    private LandmarkType() {
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
        if(LandmarkType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

