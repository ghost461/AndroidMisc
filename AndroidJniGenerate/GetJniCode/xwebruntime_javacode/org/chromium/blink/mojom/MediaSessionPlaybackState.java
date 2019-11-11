package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class MediaSessionPlaybackState {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONE = 0;
    public static final int PAUSED = 1;
    public static final int PLAYING = 2;

    private MediaSessionPlaybackState() {
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
        if(MediaSessionPlaybackState.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

