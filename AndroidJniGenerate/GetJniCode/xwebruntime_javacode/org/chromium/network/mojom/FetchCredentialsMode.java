package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FetchCredentialsMode {
    public static final int INCLUDE = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OMIT = 0;
    public static final int SAME_ORIGIN = 1;

    private FetchCredentialsMode() {
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
        if(FetchCredentialsMode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

