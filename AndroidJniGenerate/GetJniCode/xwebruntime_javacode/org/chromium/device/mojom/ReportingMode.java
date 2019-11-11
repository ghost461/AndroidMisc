package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ReportingMode {
    public static final int CONTINUOUS = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int ON_CHANGE;

    private ReportingMode() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ReportingMode.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

