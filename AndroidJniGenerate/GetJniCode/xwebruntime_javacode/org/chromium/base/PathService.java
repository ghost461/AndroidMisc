package org.chromium.base;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public abstract class PathService {
    public static final int DIR_MODULE = 3;

    private PathService() {
        super();
    }

    private static native void nativeOverride(int arg0, String arg1) {
    }

    public static void override(int arg0, String arg1) {
        PathService.nativeOverride(arg0, arg1);
    }
}

