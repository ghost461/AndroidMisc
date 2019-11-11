package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ServiceWorkerResponseError {
    public static final int BODY_LOCKED = 10;
    public static final int BODY_USED = 7;
    public static final int DATA_PIPE_CREATION_FAILED = 15;
    public static final int DEFAULT_PREVENTED = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NO_V8_INSTANCE = 3;
    public static final int PROMISE_REJECTED = 1;
    public static final int REDIRECTED_RESPONSE_FOR_NOT_FOLLOW_REQUEST = 14;
    public static final int RESPONSE_TYPE_CORS_FOR_REQUEST_MODE_SAME_ORIGIN = 16;
    public static final int RESPONSE_TYPE_ERROR = 4;
    public static final int RESPONSE_TYPE_NOT_BASIC_OR_DEFAULT = 6;
    public static final int RESPONSE_TYPE_OPAQUE = 5;
    public static final int RESPONSE_TYPE_OPAQUE_FOR_CLIENT_REQUEST = 8;
    public static final int RESPONSE_TYPE_OPAQUE_REDIRECT = 9;
    public static final int UNKNOWN;

    private ServiceWorkerResponseError() {
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
            case 14: 
            case 15: 
            case 16: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ServiceWorkerResponseError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

