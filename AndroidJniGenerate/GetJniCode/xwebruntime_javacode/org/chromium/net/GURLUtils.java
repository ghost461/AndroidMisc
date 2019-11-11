package org.chromium.net;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net") public final class GURLUtils {
    public GURLUtils() {
        super();
    }

    public static String getOrigin(String arg0) {
        return GURLUtils.nativeGetOrigin(arg0);
    }

    public static String getScheme(String arg0) {
        return GURLUtils.nativeGetScheme(arg0);
    }

    private static native String nativeGetOrigin(String arg0) {
    }

    private static native String nativeGetScheme(String arg0) {
    }
}

