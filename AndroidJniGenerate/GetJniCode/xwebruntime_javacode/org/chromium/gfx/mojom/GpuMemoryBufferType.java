package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class GpuMemoryBufferType {
    public static final int ANDROID_HARDWARE_BUFFER = 5;
    public static final int DXGI_SHARED_HANDLE = 4;
    public static final int EMPTY_BUFFER = 0;
    public static final int IO_SURFACE_BUFFER = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NATIVE_PIXMAP = 3;
    public static final int SHARED_MEMORY_BUFFER = 1;

    private GpuMemoryBufferType() {
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
        if(GpuMemoryBufferType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

