package org.chromium.base.metrics;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public final class StatisticsRecorderAndroid {
    private StatisticsRecorderAndroid() {
        super();
    }

    private static native String nativeToJson(int arg0) {
    }

    public static String toJson(int arg0) {
        return StatisticsRecorderAndroid.nativeToJson(arg0);
    }
}

