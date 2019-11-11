package org.chromium.device.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class SensorType {
    public static final int ABSOLUTE_ORIENTATION_EULER_ANGLES = 8;
    public static final int ABSOLUTE_ORIENTATION_QUATERNION = 9;
    public static final int ACCELEROMETER = 3;
    public static final int AMBIENT_LIGHT = 1;
    public static final int FIRST = 1;
    public static final int GYROSCOPE = 5;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAST = 11;
    public static final int LINEAR_ACCELERATION = 4;
    public static final int MAGNETOMETER = 6;
    public static final int PRESSURE = 7;
    public static final int PROXIMITY = 2;
    public static final int RELATIVE_ORIENTATION_EULER_ANGLES = 10;
    public static final int RELATIVE_ORIENTATION_QUATERNION = 11;

    private SensorType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(SensorType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

