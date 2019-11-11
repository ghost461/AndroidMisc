package org.chromium.url;

import java.net.IDN;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="url::android") public class IDNStringUtil {
    public IDNStringUtil() {
        super();
    }

    @CalledByNative private static String idnToASCII(String arg1) {
        int v0 = 2;
        try {
            return IDN.toASCII(arg1, v0);
        }
        catch(Exception ) {
            return null;
        }
    }
}

