package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SelectionBoundType {
    public static final int CENTER = 2;
    public static final int EMPTY = 3;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAST = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    private SelectionBoundType() {
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
        if(SelectionBoundType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

