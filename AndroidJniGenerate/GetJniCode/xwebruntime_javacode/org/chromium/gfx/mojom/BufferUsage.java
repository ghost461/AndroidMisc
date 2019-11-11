package org.chromium.gfx.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class BufferUsage {
    public static final int GPU_READ = 0;
    public static final int GPU_READ_CPU_READ_WRITE = 5;
    public static final int GPU_READ_CPU_READ_WRITE_PERSISTENT = 6;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAST = 6;
    public static final int SCANOUT = 1;
    public static final int SCANOUT_CAMERA_READ_WRITE = 2;
    public static final int SCANOUT_CPU_READ_WRITE = 3;
    public static final int SCANOUT_VDA_WRITE = 4;

    private BufferUsage() {
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
            case 6: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(BufferUsage.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

