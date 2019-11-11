package org.chromium.base;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public final class MainReturnCodeResult {
    private final int mMainReturnCode;
    private final boolean mTimedOut;

    private MainReturnCodeResult(int arg1, boolean arg2) {
        super();
        this.mMainReturnCode = arg1;
        this.mTimedOut = arg2;
    }

    public static MainReturnCodeResult createMainResult(int arg2) {
        return new MainReturnCodeResult(arg2, false);
    }

    public static MainReturnCodeResult createTimeoutMainResult() {
        return new MainReturnCodeResult(0, true);
    }

    @CalledByNative public int getReturnCode() {
        return this.mMainReturnCode;
    }

    @CalledByNative public boolean hasTimedOut() {
        return this.mTimedOut;
    }
}

