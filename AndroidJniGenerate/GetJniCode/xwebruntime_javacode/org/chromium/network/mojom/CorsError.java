package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CorsError {
    public static final int ALLOW_ORIGIN_MISMATCH = 2;
    public static final int DISALLOWED_BY_MODE = 0;
    public static final int DISALLOW_CREDENTIALS_NOT_SET_TO_TRUE = 7;
    public static final int HEADER_DISALLOWED_BY_PREFLIGHT_RESPONSE = 14;
    public static final int INVALID_ALLOW_HEADERS_PREFLIGHT_RESPONSE = 12;
    public static final int INVALID_ALLOW_METHODS_PREFLIGHT_RESPONSE = 11;
    public static final int INVALID_ALLOW_ORIGIN_VALUE = 6;
    public static final int INVALID_RESPONSE = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int METHOD_DISALLOWED_BY_PREFLIGHT_RESPONSE = 13;
    public static final int MISSING_ALLOW_ORIGIN_HEADER = 4;
    public static final int MULTIPLE_ALLOW_ORIGIN_VALUES = 5;
    public static final int PREFLIGHT_INVALID_ALLOW_EXTERNAL = 10;
    public static final int PREFLIGHT_INVALID_STATUS = 8;
    public static final int PREFLIGHT_MISSING_ALLOW_EXTERNAL = 9;
    public static final int REDIRECT_CONTAINS_CREDENTIALS = 16;
    public static final int REDIRECT_DISALLOWED_SCHEME = 15;
    public static final int WILDCARD_ORIGIN_NOT_ALLOWED = 3;

    private CorsError() {
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
            case 13: 
            case 14: 
            case 15: 
            case 16: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(CorsError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

