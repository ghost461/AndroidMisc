package org.chromium.webauth.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class AuthenticatorStatus {
    public static final int ALGORITHM_UNSUPPORTED = 4;
    public static final int AUTHENTICATOR_CRITERIA_UNSUPPORTED = 3;
    public static final int CREDENTIAL_EXCLUDED = 8;
    public static final int CREDENTIAL_NOT_RECOGNIZED = 9;
    public static final int EMPTY_ALLOW_CREDENTIALS = 5;
    public static final int INVALID_DOMAIN = 7;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NOT_ALLOWED_ERROR = 2;
    public static final int NOT_FOCUSED = 11;
    public static final int NOT_IMPLEMENTED = 10;
    public static final int PENDING_REQUEST = 1;
    public static final int SUCCESS = 0;
    public static final int UNKNOWN_ERROR = 12;
    public static final int USER_VERIFICATION_UNSUPPORTED = 6;

    private AuthenticatorStatus() {
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
            case 12: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(AuthenticatorStatus.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

