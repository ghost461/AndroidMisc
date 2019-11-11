package org.chromium.components.aboutui;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="about_ui") public class CreditUtils {
    private CreditUtils() {
        super();
    }

    public static native byte[] nativeGetJavaWrapperCredits() {
    }
}

