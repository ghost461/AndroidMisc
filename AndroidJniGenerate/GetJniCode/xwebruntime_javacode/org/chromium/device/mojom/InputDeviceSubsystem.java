package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class InputDeviceSubsystem {
    private static final boolean IS_EXTENSIBLE = false;
    public static final int SUBSYSTEM_HID = 0;
    public static final int SUBSYSTEM_INPUT = 1;
    public static final int SUBSYSTEM_UNKNOWN = 2;

    private InputDeviceSubsystem() {
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
        if(InputDeviceSubsystem.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

