package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class OverlayTransform {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OVERLAY_TRANSFORM_FLIP_HORIZONTAL = 2;
    public static final int OVERLAY_TRANSFORM_FLIP_VERTICAL = 3;
    public static final int OVERLAY_TRANSFORM_INVALID = 0;
    public static final int OVERLAY_TRANSFORM_LAST = 6;
    public static final int OVERLAY_TRANSFORM_NONE = 1;
    public static final int OVERLAY_TRANSFORM_ROTATE_180 = 5;
    public static final int OVERLAY_TRANSFORM_ROTATE_270 = 6;
    public static final int OVERLAY_TRANSFORM_ROTATE_90 = 4;

    private OverlayTransform() {
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
        if(OverlayTransform.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

