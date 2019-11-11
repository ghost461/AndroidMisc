package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class GpuFenceHandleType {
    public static final int ANDROID_NATIVE_FENCE_SYNC = 1;
    public static final int EMPTY = 0;
    private static final boolean IS_EXTENSIBLE = false;

    private GpuFenceHandleType() {
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
        if(GpuFenceHandleType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

