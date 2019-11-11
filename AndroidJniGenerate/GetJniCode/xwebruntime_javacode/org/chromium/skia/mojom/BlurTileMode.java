package org.chromium.skia.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class BlurTileMode {
    public static final int BLUR_TILE_MODE_LAST = 2;
    public static final int CLAMP = 0;
    public static final int CLAMP_TO_BLACK = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int REPEAT = 1;

    private BlurTileMode() {
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
        if(BlurTileMode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

