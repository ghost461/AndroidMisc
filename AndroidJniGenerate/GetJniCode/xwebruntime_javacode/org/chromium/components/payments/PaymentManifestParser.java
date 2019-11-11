package org.chromium.components.payments;

import java.net.URI;
import java.net.URISyntaxException;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="payments") public class PaymentManifestParser {
    public interface ManifestParseCallback {
        @CalledByNative(value="ManifestParseCallback") void onManifestParseFailure();

        @CalledByNative(value="ManifestParseCallback") void onPaymentMethodManifestParseSuccess(URI[] arg1, URI[] arg2, boolean arg3);

        @CalledByNative(value="ManifestParseCallback") void onWebAppManifestParseSuccess(WebAppManifestSection[] arg1);
    }

    private long mNativePaymentManifestParserAndroid;

    static {
    }

    public PaymentManifestParser() {
        super();
    }

    @CalledByNative private static void addFingerprintToSection(WebAppManifestSection[] arg0, int arg1, int arg2, byte[] arg3) {
        arg0[arg1].fingerprints[arg2] = arg3;
    }

    @CalledByNative private static void addSectionToManifest(WebAppManifestSection[] arg1, int arg2, String arg3, long arg4, int arg6) {
        arg1[arg2] = new WebAppManifestSection(arg3, arg4, arg6);
    }

    @CalledByNative private static boolean addUri(URI[] arg1, int arg2, String arg3) {
        try {
            arg1[arg2] = new URI(arg3);
            return 1;
        }
        catch(URISyntaxException ) {
            return 0;
        }
    }

    @CalledByNative private static WebAppManifestSection[] createManifest(int arg0) {
        return new WebAppManifestSection[arg0];
    }

    public void createNative() {
        ThreadUtils.assertOnUiThread();
        this.mNativePaymentManifestParserAndroid = PaymentManifestParser.nativeCreatePaymentManifestParserAndroid();
    }

    @CalledByNative private static URI[] createUriArray(int arg0) {
        return new URI[arg0];
    }

    public void destroyNative() {
        ThreadUtils.assertOnUiThread();
        PaymentManifestParser.nativeDestroyPaymentManifestParserAndroid(this.mNativePaymentManifestParserAndroid);
        this.mNativePaymentManifestParserAndroid = 0;
    }

    public boolean isNativeInitialized() {
        ThreadUtils.assertOnUiThread();
        boolean v0 = this.mNativePaymentManifestParserAndroid != 0 ? true : false;
        return v0;
    }

    private static native long nativeCreatePaymentManifestParserAndroid() {
    }

    private static native void nativeDestroyPaymentManifestParserAndroid(long arg0) {
    }

    private static native void nativeParsePaymentMethodManifest(long arg0, String arg1, ManifestParseCallback arg2) {
    }

    private static native void nativeParseWebAppManifest(long arg0, String arg1, ManifestParseCallback arg2) {
    }

    public void parsePaymentMethodManifest(String arg3, ManifestParseCallback arg4) {
        ThreadUtils.assertOnUiThread();
        PaymentManifestParser.nativeParsePaymentMethodManifest(this.mNativePaymentManifestParserAndroid, arg3, arg4);
    }

    public void parseWebAppManifest(String arg3, ManifestParseCallback arg4) {
        ThreadUtils.assertOnUiThread();
        PaymentManifestParser.nativeParseWebAppManifest(this.mNativePaymentManifestParserAndroid, arg3, arg4);
    }
}

