package org.chromium.base;

import android.support.annotation.UiThread;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") @MainDex public class JavaExceptionReporter implements Thread$UncaughtExceptionHandler {
    private final boolean mCrashAfterReport;
    private boolean mHandlingException;
    private final Thread$UncaughtExceptionHandler mParent;

    static {
    }

    private JavaExceptionReporter(Thread$UncaughtExceptionHandler arg1, boolean arg2) {
        super();
        this.mParent = arg1;
        this.mCrashAfterReport = arg2;
    }

    @CalledByNative private static void installHandler(boolean arg2) {
        Thread.setDefaultUncaughtExceptionHandler(new JavaExceptionReporter(Thread.getDefaultUncaughtExceptionHandler(), arg2));
    }

    private static native void nativeReportJavaException(boolean arg0, Throwable arg1) {
    }

    private static native void nativeReportJavaStackTrace(String arg0) {
    }

    @UiThread public static void reportStackTrace(String arg0) {
        JavaExceptionReporter.nativeReportJavaStackTrace(arg0);
    }

    public void uncaughtException(Thread arg2, Throwable arg3) {
        if(!this.mHandlingException) {
            this.mHandlingException = true;
            JavaExceptionReporter.nativeReportJavaException(this.mCrashAfterReport, arg3);
        }

        if(this.mParent != null) {
            this.mParent.uncaughtException(arg2, arg3);
        }
    }
}

