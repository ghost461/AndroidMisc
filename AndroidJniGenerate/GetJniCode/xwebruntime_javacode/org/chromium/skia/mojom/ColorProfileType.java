package org.chromium.skia.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ColorProfileType {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LINEAR = 0;
    public static final int SRGB = 1;

    private ColorProfileType() {
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
        if(ColorProfileType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

