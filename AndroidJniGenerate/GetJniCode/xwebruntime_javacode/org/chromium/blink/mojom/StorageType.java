package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class StorageType {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PERSISTENT = 1;
    public static final int QUOTA_NOT_MANAGED = 3;
    public static final int SYNCABLE = 2;
    public static final int TEMPORARY = 0;
    public static final int UNKNOWN = 4;

    private StorageType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(StorageType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

