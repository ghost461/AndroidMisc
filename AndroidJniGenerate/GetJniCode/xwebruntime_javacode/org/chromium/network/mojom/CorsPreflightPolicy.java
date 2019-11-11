package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CorsPreflightPolicy {
    public static final int CONSIDER_PREFLIGHT = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PREVENT_PREFLIGHT = 1;

    private CorsPreflightPolicy() {
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
        if(CorsPreflightPolicy.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

