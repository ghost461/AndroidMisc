package org.chromium.webshare.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ShareError {
    public static final int CANCELED = 2;
    public static final int INTERNAL_ERROR = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int OK;

    private ShareError() {
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
        if(ShareError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

