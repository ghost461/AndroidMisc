package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class WakeLockReason {
    public static final int AUDIO_PLAYBACK = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OTHER = 2;
    public static final int VIDEO_PLAYBACK = 1;

    private WakeLockReason() {
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
        if(WakeLockReason.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

