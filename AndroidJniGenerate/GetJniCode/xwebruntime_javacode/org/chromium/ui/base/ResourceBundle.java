package org.chromium.ui.base;

import java.util.Arrays;
import org.chromium.base.BuildConfig;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="ui") final class ResourceBundle {
    private ResourceBundle() {
        super();
    }

    @CalledByNative private static String getLocalePakResourcePath(String arg3) {
        String[] v0 = BuildConfig.UNCOMPRESSED_LOCALES;
        StringBuilder v1 = new StringBuilder();
        v1.append("stored-locales/");
        v1.append(arg3);
        if(Arrays.binarySearch(((Object[])v0), v1.toString()) >= 0) {
            return "assets/stored-locales/" + arg3 + ".pak";
        }

        return null;
    }
}

