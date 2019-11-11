package org.chromium.base;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public class ImportantFileWriterAndroid {
    public ImportantFileWriterAndroid() {
        super();
    }

    private static native boolean nativeWriteFileAtomically(String arg0, byte[] arg1) {
    }

    public static boolean writeFileAtomically(String arg0, byte[] arg1) {
        return ImportantFileWriterAndroid.nativeWriteFileAtomically(arg0, arg1);
    }
}

