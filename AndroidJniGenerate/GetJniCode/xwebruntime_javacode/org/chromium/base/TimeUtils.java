package org.chromium.base;

import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") @MainDex public class TimeUtils {
    private TimeUtils() {
        super();
    }

    public static native long nativeGetTimeTicksNowUs() {
    }
}

