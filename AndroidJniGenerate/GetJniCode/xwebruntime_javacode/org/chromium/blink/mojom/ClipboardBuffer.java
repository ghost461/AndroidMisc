package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ClipboardBuffer {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SELECTION = 1;
    public static final int STANDARD;

    private ClipboardBuffer() {
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
        if(ClipboardBuffer.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

