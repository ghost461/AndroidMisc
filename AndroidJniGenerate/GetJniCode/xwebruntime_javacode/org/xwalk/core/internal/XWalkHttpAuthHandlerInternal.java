package org.xwalk.core.internal;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") @XWalkAPI(createInternally=true, impl=XWalkHttpAuthInternal.class) public class XWalkHttpAuthHandlerInternal implements XWalkHttpAuthInternal {
    private final boolean mFirstAttempt;
    private long mNativeXWalkHttpAuthHandler;

    XWalkHttpAuthHandlerInternal() {
        super();
        this.mNativeXWalkHttpAuthHandler = 0;
        this.mFirstAttempt = false;
    }

    public XWalkHttpAuthHandlerInternal(long arg1, boolean arg3) {
        super();
        this.mNativeXWalkHttpAuthHandler = arg1;
        this.mFirstAttempt = arg3;
    }

    @XWalkAPI public void cancel() {
        long v2 = 0;
        if(this.mNativeXWalkHttpAuthHandler != v2) {
            this.nativeCancel(this.mNativeXWalkHttpAuthHandler);
            this.mNativeXWalkHttpAuthHandler = v2;
        }
    }

    @CalledByNative public static XWalkHttpAuthHandlerInternal create(long arg1, boolean arg3) {
        return new XWalkHttpAuthHandlerInternal(arg1, arg3);
    }

    @CalledByNative void handlerDestroyed() {
        this.mNativeXWalkHttpAuthHandler = 0;
    }

    @XWalkAPI public boolean isFirstAttempt() {
        return this.mFirstAttempt;
    }

    private native void nativeCancel(long arg1) {
    }

    private native void nativeProceed(long arg1, String arg2, String arg3) {
    }

    @XWalkAPI public void proceed(String arg6, String arg7) {
        long v2 = 0;
        if(this.mNativeXWalkHttpAuthHandler != v2) {
            this.nativeProceed(this.mNativeXWalkHttpAuthHandler, arg6, arg7);
            this.mNativeXWalkHttpAuthHandler = v2;
        }
    }
}

