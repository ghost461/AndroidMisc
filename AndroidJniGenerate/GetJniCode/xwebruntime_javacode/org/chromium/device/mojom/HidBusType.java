package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class HidBusType {
    public static final int HID_BUS_TYPE_BLUETOOTH = 1;
    public static final int HID_BUS_TYPE_USB = 0;
    private static final boolean IS_EXTENSIBLE = false;

    private HidBusType() {
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
        if(HidBusType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

