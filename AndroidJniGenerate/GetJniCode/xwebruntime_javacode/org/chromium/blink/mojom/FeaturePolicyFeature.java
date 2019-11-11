package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FeaturePolicyFeature {
    public static final int ACCELEROMETER = 18;
    public static final int ACCESSIBILITY_EVENTS = 16;
    public static final int AMBIENT_LIGHT_SENSOR = 19;
    public static final int AUTOPLAY = 1;
    public static final int CAMERA = 2;
    public static final int DOCUMENT_COOKIE = 10;
    public static final int DOCUMENT_DOMAIN = 11;
    public static final int DOCUMENT_WRITE = 12;
    public static final int ENCRYPTED_MEDIA = 3;
    public static final int FULLSCREEN = 4;
    public static final int GEOLOCATION = 5;
    public static final int GYROSCOPE = 20;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MAGNETOMETER = 21;
    public static final int MICROPHONE = 6;
    public static final int MIDI_FEATURE = 7;
    public static final int NOT_FOUND = 0;
    public static final int PAYMENT = 8;
    public static final int PICTURE_IN_PICTURE = 23;
    public static final int SPEAKER = 9;
    public static final int SYNC_SCRIPT = 13;
    public static final int SYNC_XHR = 14;
    public static final int UNSIZED_MEDIA = 22;
    public static final int USB = 15;
    public static final int VERTICAL_SCROLL = 24;
    public static final int WEB_VR = 17;

    private FeaturePolicyFeature() {
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
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(FeaturePolicyFeature.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

