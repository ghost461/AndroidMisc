package org.chromium.content.browser;

import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public class InterstitialPageDelegateAndroid {
    private long mNativePtr;

    @VisibleForTesting public InterstitialPageDelegateAndroid(String arg3) {
        super();
        this.mNativePtr = this.nativeInit(arg3);
    }

    @CalledByNative protected void commandReceived(String arg1) {
    }

    protected void dontProceed() {
        if(this.mNativePtr != 0) {
            this.nativeDontProceed(this.mNativePtr);
        }
    }

    @VisibleForTesting public long getNative() {
        return this.mNativePtr;
    }

    private native void nativeDontProceed(long arg1) {
    }

    private native long nativeInit(String arg1) {
    }

    private native void nativeProceed(long arg1) {
    }

    @CalledByNative protected void onDontProceed() {
    }

    @CalledByNative private void onNativeDestroyed() {
        this.mNativePtr = 0;
    }

    @CalledByNative protected void onProceed() {
    }

    protected void proceed() {
        if(this.mNativePtr != 0) {
            this.nativeProceed(this.mNativePtr);
        }
    }
}

