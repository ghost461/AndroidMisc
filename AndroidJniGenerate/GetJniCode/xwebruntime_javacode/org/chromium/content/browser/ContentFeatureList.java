package org.chromium.content.browser;

import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="content::android") @MainDex public abstract class ContentFeatureList {
    public static final String ENHANCED_SELECTION_INSERTION_HANDLE = "EnhancedSelectionInsertionHandle";

    private ContentFeatureList() {
        super();
    }

    public static boolean isEnabled(String arg0) {
        return ContentFeatureList.nativeIsEnabled(arg0);
    }

    private static native boolean nativeIsEnabled(String arg0) {
    }
}

