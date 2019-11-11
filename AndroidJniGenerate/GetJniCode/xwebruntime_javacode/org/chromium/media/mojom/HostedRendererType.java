package org.chromium.media.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class HostedRendererType {
    public static final int DEFAULT = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MEDIA_PLAYER = 1;

    private HostedRendererType() {
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
        if(HostedRendererType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

