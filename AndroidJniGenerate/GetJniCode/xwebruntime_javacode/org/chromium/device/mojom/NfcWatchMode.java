package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class NfcWatchMode {
    public static final int ANY = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int WEBNFC_ONLY;

    private NfcWatchMode() {
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
        if(NfcWatchMode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

