package org.chromium.net.interfaces;

import org.chromium.mojo.bindings.DeserializationException;

public final class AddressFamily {
    public static final int IPV4 = 1;
    public static final int IPV6 = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int UNSPECIFIED;

    private AddressFamily() {
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
        if(AddressFamily.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

