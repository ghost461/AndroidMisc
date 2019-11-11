package org.chromium.base;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public abstract class CpuFeatures {
    public CpuFeatures() {
        super();
    }

    public static int getCount() {
        return CpuFeatures.nativeGetCoreCount();
    }

    public static long getMask() {
        return CpuFeatures.nativeGetCpuFeatures();
    }

    private static native int nativeGetCoreCount() {
    }

    private static native long nativeGetCpuFeatures() {
    }
}

