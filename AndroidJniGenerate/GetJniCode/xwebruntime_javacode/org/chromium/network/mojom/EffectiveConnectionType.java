package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class EffectiveConnectionType {
    private static final boolean IS_EXTENSIBLE = false;

    private EffectiveConnectionType() {
        super();
    }

    public static boolean isKnownValue(int arg0) {
        return 0;
    }

    public static void validate(int arg1) {
        if(EffectiveConnectionType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

