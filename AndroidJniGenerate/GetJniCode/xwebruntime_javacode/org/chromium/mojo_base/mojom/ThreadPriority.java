package org.chromium.mojo_base.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ThreadPriority {
    public static final int BACKGROUND = 0;
    public static final int DISPLAY = 2;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int NORMAL = 1;
    public static final int REALTIME_AUDIO = 3;

    private ThreadPriority() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(ThreadPriority.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

