package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SharedWorkerCreationContextType {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NONSECURE = 0;
    public static final int SECURE = 1;

    private SharedWorkerCreationContextType() {
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
        if(SharedWorkerCreationContextType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

