package org.chromium.content_public.common;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public final class UseZoomForDSFPolicy {
    private UseZoomForDSFPolicy() {
        super();
    }

    public static boolean isUseZoomForDSFEnabled() {
        return UseZoomForDSFPolicy.nativeIsUseZoomForDSFEnabled();
    }

    private static native boolean nativeIsUseZoomForDSFEnabled() {
    }
}

