package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ServiceWorkerClientType {
    public static final int ALL = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SHARED_WORKER = 1;
    public static final int WINDOW;

    private ServiceWorkerClientType() {
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
        if(ServiceWorkerClientType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

