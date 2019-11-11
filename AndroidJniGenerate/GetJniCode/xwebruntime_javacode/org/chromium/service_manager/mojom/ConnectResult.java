package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ConnectResult {
    public static final int ACCESS_DENIED = 2;
    public static final int INVALID_ARGUMENT = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SUCCEEDED;

    private ConnectResult() {
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
        if(ConnectResult.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

