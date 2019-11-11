package org.chromium.payments.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AndroidPayEnvironment {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PRODUCTION = 0;
    public static final int TEST = 1;

    private AndroidPayEnvironment() {
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
        if(AndroidPayEnvironment.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

