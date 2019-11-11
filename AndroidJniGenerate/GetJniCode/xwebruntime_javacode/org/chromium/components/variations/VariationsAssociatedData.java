package org.chromium.components.variations;

import java.util.HashMap;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="variations::android") public final class VariationsAssociatedData {
    private VariationsAssociatedData() {
        super();
    }

    public static HashMap getFeedbackMap() {
        HashMap v0 = new HashMap();
        v0.put("Chrome Variations", VariationsAssociatedData.nativeGetFeedbackVariations());
        return v0;
    }

    public static String getVariationParamValue(String arg0, String arg1) {
        return VariationsAssociatedData.nativeGetVariationParamValue(arg0, arg1);
    }

    private static native String nativeGetFeedbackVariations() {
    }

    private static native String nativeGetVariationParamValue(String arg0, String arg1) {
    }
}

