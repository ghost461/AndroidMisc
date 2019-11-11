package org.chromium.content.app;

import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="content") @MainDex public class ContentMain {
    public ContentMain() {
        super();
    }

    private static native int nativeStart() {
    }

    public static int start() {
        return ContentMain.nativeStart();
    }
}

