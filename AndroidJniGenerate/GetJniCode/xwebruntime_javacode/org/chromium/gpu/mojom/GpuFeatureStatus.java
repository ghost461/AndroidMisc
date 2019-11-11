package org.chromium.gpu.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class GpuFeatureStatus {
    public static final int BLACKLISTED = 1;
    public static final int DISABLED = 2;
    public static final int ENABLED = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MAX = 5;
    public static final int SOFTWARE = 3;
    public static final int UNDEFINED = 4;

    private GpuFeatureStatus() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(GpuFeatureStatus.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

