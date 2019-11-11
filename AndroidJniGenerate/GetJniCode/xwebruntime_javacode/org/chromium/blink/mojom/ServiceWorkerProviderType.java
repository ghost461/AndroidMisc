package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ServiceWorkerProviderType {
    public static final int FOR_SERVICE_WORKER = 3;
    public static final int FOR_SHARED_WORKER = 2;
    public static final int FOR_WINDOW = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int UNKNOWN;

    private ServiceWorkerProviderType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ServiceWorkerProviderType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

