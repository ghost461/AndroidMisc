package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ClipboardFormat {
    public static final int BOOKMARK = 3;
    public static final int HTML = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PLAINTEXT = 0;
    public static final int SMART_PASTE = 2;

    private ClipboardFormat() {
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
        if(ClipboardFormat.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

