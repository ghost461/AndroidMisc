package org.chromium.components.payments;

import java.net.URI;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="payments") public class PaymentManifestDownloader {
    public interface ManifestDownloadCallback {
        @CalledByNative(value="ManifestDownloadCallback") void onManifestDownloadFailure();

        @CalledByNative(value="ManifestDownloadCallback") void onPaymentMethodManifestDownloadSuccess(String arg1);

        @CalledByNative(value="ManifestDownloadCallback") void onWebAppManifestDownloadSuccess(String arg1);
    }

    private long mNativeObject;

    static {
    }

    public PaymentManifestDownloader() {
        super();
    }

    public void destroy() {
        ThreadUtils.assertOnUiThread();
        this.nativeDestroy(this.mNativeObject);
        this.mNativeObject = 0;
    }

    public void downloadPaymentMethodManifest(URI arg3, ManifestDownloadCallback arg4) {
        ThreadUtils.assertOnUiThread();
        this.nativeDownloadPaymentMethodManifest(this.mNativeObject, arg3, arg4);
    }

    public void downloadWebAppManifest(URI arg3, ManifestDownloadCallback arg4) {
        ThreadUtils.assertOnUiThread();
        this.nativeDownloadWebAppManifest(this.mNativeObject, arg3, arg4);
    }

    @CalledByNative private static String getUriString(URI arg0) {
        return arg0.toString();
    }

    public void initialize(WebContents arg3) {
        ThreadUtils.assertOnUiThread();
        this.mNativeObject = PaymentManifestDownloader.nativeInit(arg3);
    }

    public boolean isInitialized() {
        ThreadUtils.assertOnUiThread();
        boolean v0 = this.mNativeObject != 0 ? true : false;
        return v0;
    }

    private native void nativeDestroy(long arg1) {
    }

    private native void nativeDownloadPaymentMethodManifest(long arg1, URI arg2, ManifestDownloadCallback arg3) {
    }

    private native void nativeDownloadWebAppManifest(long arg1, URI arg2, ManifestDownloadCallback arg3) {
    }

    private static native long nativeInit(WebContents arg0) {
    }
}

