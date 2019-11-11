package org.chromium.proxy_resolver.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class ProxyScheme {
    public static final int DIRECT = 1;
    public static final int HTTP = 2;
    public static final int HTTPS = 5;
    public static final int INVALID = 0;
    private static final boolean IS_EXTENSIBLE = false;
    public static final int QUIC = 6;
    public static final int SOCKS4 = 3;
    public static final int SOCKS5 = 4;

    private ProxyScheme() {
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
        if(ProxyScheme.isKnownValue(arg1)) {
            return;
        }

        throw new DeserializationException("Invalid enum value.");
    }
}

