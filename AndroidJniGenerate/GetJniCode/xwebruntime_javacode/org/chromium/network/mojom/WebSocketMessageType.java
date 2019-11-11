package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class WebSocketMessageType {
    public static final int BINARY = 2;
    public static final int CONTINUATION = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int LAST = 2;
    public static final int TEXT = 1;

    private WebSocketMessageType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        switch(arg0) {
            case 0: 
            case 1: 
            case 2: {
                return 1;
            }
        }

        return 0;
    }

    public static void validate(int arg1) {
        if(WebSocketMessageType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

