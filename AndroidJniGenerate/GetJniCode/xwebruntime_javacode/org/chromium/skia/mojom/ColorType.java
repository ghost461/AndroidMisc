package org.chromium.skia.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ColorType {
    public static final int ALPHA_8 = 1;
    public static final int ARGB_4444 = 3;
    public static final int BGRA_8888 = 5;
    public static final int GRAY_8 = 7;
    public static final int INDEX_8 = 6;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int RGBA_8888 = 4;
    public static final int RGB_565 = 2;
    public static final int UNKNOWN;

    private ColorType() {
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
            case 6: 
            case 7: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ColorType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

