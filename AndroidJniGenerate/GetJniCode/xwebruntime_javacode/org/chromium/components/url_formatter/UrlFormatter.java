package org.chromium.components.url_formatter;

import android.text.TextUtils;
import java.net.URI;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="url_formatter::android") public final class UrlFormatter {
    public UrlFormatter() {
        super();
    }

    public static String fixupUrl(String arg1) {
        return TextUtils.isEmpty(((CharSequence)arg1)) ? null : UrlFormatter.nativeFixupUrl(arg1);
    }

    public static String formatUrlForDisplay(String arg0) {
        return UrlFormatter.nativeFormatUrlForDisplay(arg0);
    }

    public static String formatUrlForDisplay(URI arg0) {
        return UrlFormatter.formatUrlForDisplay(arg0.toString());
    }

    public static String formatUrlForDisplayOmitScheme(String arg0) {
        return UrlFormatter.nativeFormatUrlForDisplayOmitScheme(arg0);
    }

    public static String formatUrlForSecurityDisplay(String arg0, boolean arg1) {
        if(arg1) {
            return UrlFormatter.nativeFormatUrlForSecurityDisplay(arg0);
        }

        return UrlFormatter.nativeFormatUrlForSecurityDisplayOmitScheme(arg0);
    }

    public static String formatUrlForSecurityDisplay(URI arg0, boolean arg1) {
        return UrlFormatter.formatUrlForSecurityDisplay(arg0.toString(), arg1);
    }

    private static native String nativeFixupUrl(String arg0) {
    }

    private static native String nativeFormatUrlForDisplay(String arg0) {
    }

    private static native String nativeFormatUrlForDisplayOmitScheme(String arg0) {
    }

    private static native String nativeFormatUrlForSecurityDisplay(String arg0) {
    }

    private static native String nativeFormatUrlForSecurityDisplayOmitScheme(String arg0) {
    }
}

