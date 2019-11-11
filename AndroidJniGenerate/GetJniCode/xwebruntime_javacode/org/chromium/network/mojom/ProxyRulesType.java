package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ProxyRulesType {
    public static final int EMPTY = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int PROXY_LIST = 1;
    public static final int PROXY_LIST_PER_SCHEME = 2;

    private ProxyRulesType() {
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
        if(ProxyRulesType.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

