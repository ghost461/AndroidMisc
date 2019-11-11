package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class InputDeviceType {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int TYPE_BLUETOOTH = 0;
    public static final int TYPE_SERIO = 2;
    public static final int TYPE_UNKNOWN = 3;
    public static final int TYPE_USB = 1;

    private InputDeviceType() {
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
        if(InputDeviceType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

