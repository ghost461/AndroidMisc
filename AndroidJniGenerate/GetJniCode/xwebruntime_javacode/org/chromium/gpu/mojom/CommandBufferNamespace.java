package org.chromium.gpu.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CommandBufferNamespace {
    public static final int GPU_IO = 0;
    public static final int INVALID = -1;
    public static final int IN_PROCESS = 1;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int MOJO = 2;
    public static final int MOJO_LOCAL = 3;
    public static final int NUM_COMMAND_BUFFER_NAMESPACES = 4;

    private CommandBufferNamespace() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case -1: 
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(CommandBufferNamespace.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

