package org.chromium.ui.base;

import java.util.Locale;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.LocaleUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="l10n_util") public class LocalizationUtils {
    public static final int LEFT_TO_RIGHT = 2;
    public static final int RIGHT_TO_LEFT = 1;
    public static final int UNKNOWN_DIRECTION;
    private static Boolean sIsLayoutRtl;

    static {
    }

    private LocalizationUtils() {
        super();
    }

    @CalledByNative private static String getDisplayNameForLocale(Locale arg0, Locale arg1) {
        return arg0.getDisplayName(arg1);
    }

    public static int getFirstStrongCharacterDirection(String arg0) {
        return LocalizationUtils.nativeGetFirstStrongCharacterDirection(arg0);
    }

    @CalledByNative private static Locale getJavaLocale(String arg1, String arg2, String arg3) {
        return new Locale(arg1, arg2, arg3);
    }

    @CalledByNative public static boolean isLayoutRtl() {
        if(LocalizationUtils.sIsLayoutRtl == null) {
            boolean v1 = true;
            if(ApiCompatibilityUtils.getLayoutDirection(ContextUtils.getApplicationContext().getResources().getConfiguration()) == 1) {
            }
            else {
                v1 = false;
            }

            LocalizationUtils.sIsLayoutRtl = Boolean.valueOf(v1);
        }

        return LocalizationUtils.sIsLayoutRtl.booleanValue();
    }

    private static native int nativeGetFirstStrongCharacterDirection(String arg0) {
    }

    @VisibleForTesting public static void setRtlForTesting(boolean arg0) {
        LocalizationUtils.sIsLayoutRtl = Boolean.valueOf(arg0);
    }

    public static String substituteLocalePlaceholder(String arg4) {
        return arg4.replace("$LOCALE", LocaleUtils.getDefaultLocaleString().replace('-', '_'));
    }
}

