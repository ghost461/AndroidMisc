package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class BufferFormat {
    public static final int ATC = 0;
    public static final int ATCIA = 1;
    public static final int BGRA_8888 = 15;
    public static final int BGRX_1010102 = 13;
    public static final int BGRX_8888 = 12;
    public static final int BGR_565 = 8;
    public static final int DXT1 = 2;
    public static final int DXT5 = 3;
    public static final int ETC1 = 4;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAST = 19;
    public static final int RGBA_4444 = 9;
    public static final int RGBA_8888 = 11;
    public static final int RGBA_F16 = 16;
    public static final int RGBX_1010102 = 14;
    public static final int RGBX_8888 = 10;
    public static final int RG_88 = 7;
    public static final int R_16 = 6;
    public static final int R_8 = 5;
    public static final int UYVY_422 = 19;
    public static final int YUV_420_BIPLANAR = 18;
    public static final int YVU_420 = 17;

    private BufferFormat() {
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
            case 19: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(BufferFormat.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

