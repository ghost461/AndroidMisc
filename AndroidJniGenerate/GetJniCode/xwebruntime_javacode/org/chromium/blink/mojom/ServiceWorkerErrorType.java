package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ServiceWorkerErrorType {
    public static final int ABORT = 1;
    public static final int ACTIVATE = 2;
    public static final int DISABLED = 3;
    public static final int INSTALL = 4;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NAVIGATION = 5;
    public static final int NETWORK = 6;
    public static final int NONE = 0;
    public static final int NOT_FOUND = 7;
    public static final int SCRIPT_EVALUATE_FAILED = 8;
    public static final int SECURITY = 9;
    public static final int STATE = 10;
    public static final int TIMEOUT = 11;
    public static final int TYPE = 12;
    public static final int UNKNOWN = 13;

    private ServiceWorkerErrorType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ServiceWorkerErrorType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

