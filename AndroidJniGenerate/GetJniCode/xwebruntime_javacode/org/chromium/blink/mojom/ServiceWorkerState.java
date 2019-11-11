package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ServiceWorkerState {
    public static final int ACTIVATED = 4;
    public static final int ACTIVATING = 3;
    public static final int INSTALLED = 2;
    public static final int INSTALLING = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int REDUNDANT = 5;
    public static final int UNKNOWN;

    private ServiceWorkerState() {
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
        if(ServiceWorkerState.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

