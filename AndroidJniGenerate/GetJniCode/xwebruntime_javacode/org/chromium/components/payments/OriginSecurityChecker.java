package org.chromium.components.payments;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="payments") public class OriginSecurityChecker {
    private OriginSecurityChecker() {
        super();
    }

    public static boolean isOriginLocalhostOrFile(String arg0) {
        return OriginSecurityChecker.nativeIsOriginLocalhostOrFile(arg0);
    }

    public static boolean isOriginSecure(String arg0) {
        return OriginSecurityChecker.nativeIsOriginSecure(arg0);
    }

    public static boolean isSchemeCryptographic(String arg0) {
        return OriginSecurityChecker.nativeIsSchemeCryptographic(arg0);
    }

    private static native boolean nativeIsOriginLocalhostOrFile(String arg0) {
    }

    private static native boolean nativeIsOriginSecure(String arg0) {
    }

    private static native boolean nativeIsSchemeCryptographic(String arg0) {
    }
}

