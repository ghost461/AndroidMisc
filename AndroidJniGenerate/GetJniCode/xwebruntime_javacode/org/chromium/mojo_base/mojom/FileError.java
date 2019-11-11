package org.chromium.mojo_base.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FileError {
    public static final int ABORT = -12;
    public static final int ACCESS_DENIED = -5;
    public static final int EXISTS = -3;
    public static final int FAILED = -1;
    public static final int INVALID_OPERATION = -10;
    public static final int INVALID_URL = -15;
    public static final int IN_USE = -2;
    public static final int IO = -16;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NOT_A_DIRECTORY = -9;
    public static final int NOT_A_FILE = -13;
    public static final int NOT_EMPTY = -14;
    public static final int NOT_FOUND = -4;
    public static final int NO_MEMORY = -7;
    public static final int NO_SPACE = -8;
    public static final int OK = 0;
    public static final int SECURITY = -11;
    public static final int TOO_MANY_OPENED = -6;

    private FileError() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case -16: 
            case -15: 
            case -14: 
            case -13: 
            case -12: 
            case -11: 
            case -10: 
            case -9: 
            case -8: 
            case -7: 
            case -6: 
            case -5: 
            case -4: 
            case -3: 
            case -2: 
            case -1: 
            case 0: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(FileError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

