package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class RemoteInvocationError {
    public static final int EXCEPTION_THROWN = 3;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int METHOD_NOT_FOUND = 1;
    public static final int OBJECT_GET_CLASS_BLOCKED = 2;
    public static final int OK;

    private RemoteInvocationError() {
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
        if(RemoteInvocationError.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

