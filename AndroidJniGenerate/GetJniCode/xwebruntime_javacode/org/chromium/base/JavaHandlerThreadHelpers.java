package org.chromium.base;

import android.os.Handler;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.CalledByNativeUnchecked;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") class JavaHandlerThreadHelpers {
    class TestException extends Exception {
        TestException(org.chromium.base.JavaHandlerThreadHelpers$1 arg1) {
            this();
        }

        private TestException() {
            super();
        }
    }

    JavaHandlerThreadHelpers() {
        super();
    }

    @CalledByNative private static boolean isExceptionTestException(Throwable arg0) {
        if(arg0 == null) {
            return 0;
        }

        return arg0 instanceof TestException;
    }

    @CalledByNative private static JavaHandlerThread testAndGetJavaHandlerThread() {
        AtomicBoolean v0 = new AtomicBoolean();
        Object v1 = new Object();
        org.chromium.base.JavaHandlerThreadHelpers$1 v2 = new Runnable(v1, v0) {
            public void run() {
                Object v0 = this.val$lock;
                __monitor_enter(v0);
                try {
                    this.val$taskExecuted.set(true);
                    this.val$lock.notifyAll();
                    __monitor_exit(v0);
                    return;
                label_10:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_10;
                }

                throw v1;
            }
        };
        JavaHandlerThread v3 = new JavaHandlerThread("base_unittests_java");
        v3.maybeStart();
        new Handler(v3.getLooper()).post(((Runnable)v2));
        __monitor_enter(v1);
        try {
            while(!v0.get()) {
                try {
                    v1.wait();
                }
                catch(InterruptedException ) {
                }
            }

            __monitor_exit(v1);
            return v3;
        label_22:
            __monitor_exit(v1);
        }
        catch(Throwable v0_1) {
            goto label_22;
        }

        throw v0_1;
    }

    @CalledByNativeUnchecked private static void throwException() throws TestException {
        throw new TestException(null);
    }
}

