package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ConnectionType {
    public static final int CONNECTION_2G = 3;
    public static final int CONNECTION_3G = 4;
    public static final int CONNECTION_4G = 5;
    public static final int CONNECTION_BLUETOOTH = 7;
    public static final int CONNECTION_ETHERNET = 1;
    public static final int CONNECTION_LAST = 7;
    public static final int CONNECTION_NONE = 6;
    public static final int CONNECTION_UNKNOWN = 0;
    public static final int CONNECTION_WIFI = 2;
    private static final boolean IS_EXTENSIBLE = false;

    private ConnectionType() {
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
            case 7: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ConnectionType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

