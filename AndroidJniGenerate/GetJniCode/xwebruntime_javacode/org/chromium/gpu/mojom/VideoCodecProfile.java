package org.chromium.gpu.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class VideoCodecProfile {
    public static final int AV1PROFILE_PROFILE0 = 24;
    public static final int DOLBYVISION_PROFILE0 = 19;
    public static final int DOLBYVISION_PROFILE4 = 20;
    public static final int DOLBYVISION_PROFILE5 = 21;
    public static final int DOLBYVISION_PROFILE7 = 22;
    public static final int H264PROFILE_BASELINE = 0;
    public static final int H264PROFILE_EXTENDED = 2;
    public static final int H264PROFILE_HIGH = 3;
    public static final int H264PROFILE_HIGH10PROFILE = 4;
    public static final int H264PROFILE_HIGH422PROFILE = 5;
    public static final int H264PROFILE_HIGH444PREDICTIVEPROFILE = 6;
    public static final int H264PROFILE_MAIN = 1;
    public static final int H264PROFILE_MULTIVIEWHIGH = 10;
    public static final int H264PROFILE_SCALABLEBASELINE = 7;
    public static final int H264PROFILE_SCALABLEHIGH = 8;
    public static final int H264PROFILE_STEREOHIGH = 9;
    public static final int HEVCPROFILE_MAIN = 16;
    public static final int HEVCPROFILE_MAIN10 = 17;
    public static final int HEVCPROFILE_MAIN_STILL_PICTURE = 18;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int THEORAPROFILE_ANY = 23;
    public static final int VIDEO_CODEC_PROFILE_UNKNOWN = -1;
    public static final int VP8PROFILE_ANY = 11;
    public static final int VP9PROFILE_PROFILE0 = 12;
    public static final int VP9PROFILE_PROFILE1 = 13;
    public static final int VP9PROFILE_PROFILE2 = 14;
    public static final int VP9PROFILE_PROFILE3 = 15;

    private VideoCodecProfile() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case -1: 
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
        if(VideoCodecProfile.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

