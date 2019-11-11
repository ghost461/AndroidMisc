package org.chromium.components.payments;

import java.util.Locale;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="payments") public class CurrencyFormatter {
    private long mCurrencyFormatterAndroid;

    static {
    }

    public CurrencyFormatter(String arg1, String arg2, Locale arg3) {
        super();
        this.mCurrencyFormatterAndroid = this.nativeInitCurrencyFormatterAndroid(arg1, arg2, arg3.toString());
    }

    public void destroy() {
        long v2 = 0;
        if(this.mCurrencyFormatterAndroid != v2) {
            this.nativeDestroy(this.mCurrencyFormatterAndroid);
            this.mCurrencyFormatterAndroid = v2;
        }
    }

    public String format(String arg3) {
        return this.nativeFormat(this.mCurrencyFormatterAndroid, arg3);
    }

    public String getFormattedCurrencyCode() {
        return this.nativeGetFormattedCurrencyCode(this.mCurrencyFormatterAndroid);
    }

    private native void nativeDestroy(long arg1) {
    }

    private native String nativeFormat(long arg1, String arg2) {
    }

    private native String nativeGetFormattedCurrencyCode(long arg1) {
    }

    private native long nativeInitCurrencyFormatterAndroid(String arg1, String arg2, String arg3) {
    }
}

