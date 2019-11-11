package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ServiceWorkerEventStatus {
    public static final int ABORTED = 2;
    public static final int COMPLETED = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MAX = 2;
    public static final int REJECTED = 1;

    private ServiceWorkerEventStatus() {
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
        if(ServiceWorkerEventStatus.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

