package org.chromium.media.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AudioCodec {
    private static final boolean IS_EXTENSIBLE = false;

    private AudioCodec() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        return 0;
    }

    public static void validate(int arg1) {
        if(AudioCodec.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

