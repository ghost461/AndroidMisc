package org.xwalk.core.internal;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") class InputStreamUtil {
    private static final int CALL_FAILED_STATUS = -1;
    private static final int EXCEPTION_THROWN_STATUS = -2;
    private static final String LOGTAG = "InputStreamUtil";

    InputStreamUtil() {
        super();
    }

    @CalledByNative public static int available(InputStream arg2) {
        int v0 = -1;
        try {
            return Math.max(v0, arg2.available());
        }
        catch(IOException v2) {
            Log.e("InputStreamUtil", InputStreamUtil.logMessage("available"), ((Throwable)v2));
            return -2;
        }
    }

    @CalledByNative public static void close(InputStream arg2) {
        try {
            arg2.close();
        }
        catch(IOException v2) {
            Log.e("InputStreamUtil", InputStreamUtil.logMessage("close"), ((Throwable)v2));
        }
    }

    private static String logMessage(String arg2) {
        return "Got exception when calling " + arg2 + "() on an InputStream returned from shouldInterceptRequest. This will cause the related request to fail.";
    }

    @CalledByNative public static int read(InputStream arg1, byte[] arg2, int arg3, int arg4) {
        int v0 = -1;
        try {
            return Math.max(v0, arg1.read(arg2, arg3, arg4));
        }
        catch(IOException v1) {
            Log.e("InputStreamUtil", InputStreamUtil.logMessage("read"), ((Throwable)v1));
            return -2;
        }
    }

    @CalledByNative public static long skip(InputStream arg2, long arg3) {
        long v0 = -1;
        try {
            return Math.max(v0, arg2.skip(arg3));
        }
        catch(IOException v2) {
            Log.e("InputStreamUtil", InputStreamUtil.logMessage("skip"), ((Throwable)v2));
            return -2;
        }
    }
}

