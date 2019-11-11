package org.xwalk.core.internal;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") public class XWalkFormDatabase {
    public XWalkFormDatabase() {
        super();
    }

    public static void clearFormData() {
        XWalkFormDatabase.nativeClearFormData();
    }

    public static boolean hasFormData() {
        return XWalkFormDatabase.nativeHasFormData();
    }

    private static native void nativeClearFormData() {
    }

    private static native boolean nativeHasFormData() {
    }
}

