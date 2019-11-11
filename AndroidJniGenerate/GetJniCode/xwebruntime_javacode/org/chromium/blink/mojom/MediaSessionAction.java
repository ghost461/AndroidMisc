package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class MediaSessionAction {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAST = 5;
    public static final int NEXT_TRACK = 3;
    public static final int PAUSE = 1;
    public static final int PLAY = 0;
    public static final int PREVIOUS_TRACK = 2;
    public static final int SEEK_BACKWARD = 4;
    public static final int SEEK_FORWARD = 5;

    private MediaSessionAction() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(MediaSessionAction.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

