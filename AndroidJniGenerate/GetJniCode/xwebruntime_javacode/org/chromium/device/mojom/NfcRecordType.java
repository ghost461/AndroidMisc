package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class NfcRecordType {
    public static final int EMPTY = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int JSON = 3;
    public static final int OPAQUE_RECORD = 4;
    public static final int TEXT = 1;
    public static final int URL = 2;

    private NfcRecordType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(NfcRecordType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

