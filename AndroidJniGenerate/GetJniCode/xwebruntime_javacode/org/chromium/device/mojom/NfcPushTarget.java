package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class NfcPushTarget {
    public static final int ANY = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PEER = 1;
    public static final int TAG;

    private NfcPushTarget() {
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
        if(NfcPushTarget.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

