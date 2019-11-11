package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FetchResponseType {
    public static final int BASIC = 0;
    public static final int CORS = 1;
    public static final int DEFAULT = 2;
    public static final int ERROR = 3;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OPAQUE = 4;
    public static final int OPAQUE_REDIRECT = 5;

    private FetchResponseType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(FetchResponseType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

