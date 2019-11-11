package org.chromium.base;

import org.chromium.base.annotations.MainDex;

@MainDex public class FieldTrialList {
    private FieldTrialList() {
        super();
    }

    public static String findFullName(String arg0) {
        return FieldTrialList.nativeFindFullName(arg0);
    }

    public static String getVariationParameter(String arg0, String arg1) {
        return FieldTrialList.nativeGetVariationParameter(arg0, arg1);
    }

    private static native String nativeFindFullName(String arg0) {
    }

    private static native String nativeGetVariationParameter(String arg0, String arg1) {
    }

    private static native boolean nativeTrialExists(String arg0) {
    }

    public static boolean trialExists(String arg0) {
        return FieldTrialList.nativeTrialExists(arg0);
    }
}

