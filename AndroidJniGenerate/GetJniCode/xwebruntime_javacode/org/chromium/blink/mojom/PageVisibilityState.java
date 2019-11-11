package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class PageVisibilityState {
    public static final int HIDDEN = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PRERENDER = 2;
    public static final int VISIBLE;

    private PageVisibilityState() {
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
        if(PageVisibilityState.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

