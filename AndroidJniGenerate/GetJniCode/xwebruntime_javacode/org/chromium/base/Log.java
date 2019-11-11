package org.chromium.base;

import java.util.Locale;
import org.chromium.base.annotations.RemovableInRelease;

public class Log {
    public abstract class CallbackLog {
        public CallbackLog() {
            super();
        }

        public abstract void d(String arg1, String arg2, Throwable arg3);

        public abstract void d(String arg1, String arg2);

        public abstract void e(String arg1, String arg2, Throwable arg3);

        public abstract void e(String arg1, String arg2);

        public abstract void i(String arg1, String arg2, Throwable arg3);

        public abstract void i(String arg1, String arg2);

        public abstract void v(String arg1, String arg2, Throwable arg3);

        public abstract void v(String arg1, String arg2);

        public abstract void w(String arg1, String arg2, Throwable arg3);

        public abstract void w(String arg1, String arg2);
    }

    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static CallbackLog sCallbackLog = null;
    private static final String sDeprecatedTagPrefix = "cr.";
    private static final String sTagPrefix = "cr_";

    private Log() {
        super();
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4) {
        Log.debug(arg2, arg3, new Object[]{arg4});
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg1, String arg2) {
        Log.debug(arg1, arg2, new Object[0]);
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4, Object arg5) {
        Log.debug(arg2, arg3, new Object[]{arg4, arg5});
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4, Object arg5, Object arg6) {
        Log.debug(arg2, arg3, new Object[]{arg4, arg5, arg6});
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        Log.debug(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7});
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) {
        Log.debug(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7, arg8});
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) {
        Log.debug(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7, arg8, arg9});
    }

    @VisibleForTesting @RemovableInRelease public static void d(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10) {
        Log.debug(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7, arg8, arg9, arg10});
    }

    private static void debug(String arg1, String arg2, Object[] arg3) {
        arg2 = Log.formatLogWithStack(arg2, arg3);
        Throwable v3 = Log.getThrowableToLog(arg3);
        if(v3 != null) {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.d(Log.normalizeTag(arg1), arg2, v3);
            }
            else {
                android.util.Log.d(Log.normalizeTag(arg1), arg2, v3);
            }
        }
        else if(Log.sCallbackLog != null) {
            Log.sCallbackLog.d(Log.normalizeTag(arg1), arg2);
        }
        else {
            android.util.Log.d(Log.normalizeTag(arg1), arg2);
        }
    }

    @VisibleForTesting public static void e(String arg1, String arg2, Object[] arg3) {
        arg2 = Log.formatLog(arg2, arg3);
        Throwable v3 = Log.getThrowableToLog(arg3);
        if(v3 != null) {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.e(Log.normalizeTag(arg1), arg2, v3);
            }
            else {
                android.util.Log.e(Log.normalizeTag(arg1), arg2, v3);
            }
        }
        else if(Log.sCallbackLog != null) {
            Log.sCallbackLog.e(Log.normalizeTag(arg1), arg2);
        }
        else {
            android.util.Log.e(Log.normalizeTag(arg1), arg2);
        }
    }

    private static String formatLog(String arg1, Object[] arg2) {
        if(arg2 != null && arg2.length != 0) {
            arg1 = String.format(Locale.US, arg1, arg2);
        }

        return arg1;
    }

    private static String formatLogWithStack(String arg2, Object[] arg3) {
        return "[" + Log.getCallOrigin() + "] " + Log.formatLog(arg2, arg3);
    }

    private static String getCallOrigin() {
        StackTraceElement[] v0 = Thread.currentThread().getStackTrace();
        String v1 = Log.class.getName();
        int v2 = 0;
        while(v2 < v0.length) {
            if(v0[v2].getClassName().equals(v1)) {
                v2 += 4;
            }
            else {
                ++v2;
                continue;
            }

            break;
        }

        return v0[v2].getFileName() + ":" + v0[v2].getLineNumber();
    }

    public static String getStackTraceString(Throwable arg0) {
        return android.util.Log.getStackTraceString(arg0);
    }

    private static Throwable getThrowableToLog(Object[] arg2) {
        Throwable v0 = null;
        if(arg2 != null) {
            if(arg2.length == 0) {
            }
            else {
                Object v2 = arg2[arg2.length - 1];
                if(!(v2 instanceof Throwable)) {
                    return v0;
                }
                else {
                    return ((Throwable)v2);
                }
            }
        }

        return v0;
    }

    @VisibleForTesting public static void i(String arg1, String arg2, Object[] arg3) {
        arg2 = Log.formatLog(arg2, arg3);
        Throwable v3 = Log.getThrowableToLog(arg3);
        if(v3 != null) {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.i(Log.normalizeTag(arg1), arg2, v3);
            }
            else {
                android.util.Log.i(Log.normalizeTag(arg1), arg2, v3);
            }
        }
        else if(Log.sCallbackLog != null) {
            Log.sCallbackLog.i(Log.normalizeTag(arg1), arg2);
        }
        else {
            android.util.Log.i(Log.normalizeTag(arg1), arg2);
        }
    }

    public static boolean isLoggable(String arg0, int arg1) {
        return android.util.Log.isLoggable(arg0, arg1);
    }

    public static String normalizeTag(String arg3) {
        if(arg3.startsWith("cr_")) {
            return arg3;
        }

        int v0 = 0;
        if(arg3.startsWith("cr.")) {
            v0 = "cr.".length();
        }

        return "cr_" + arg3.substring(v0, arg3.length());
    }

    public static void setCallbackLog(CallbackLog arg0) {
        Log.sCallbackLog = arg0;
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg1, String arg2) {
        Log.verbose(arg1, arg2, new Object[0]);
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4) {
        Log.verbose(arg2, arg3, new Object[]{arg4});
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4, Object arg5) {
        Log.verbose(arg2, arg3, new Object[]{arg4, arg5});
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4, Object arg5, Object arg6) {
        Log.verbose(arg2, arg3, new Object[]{arg4, arg5, arg6});
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        Log.verbose(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7});
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) {
        Log.verbose(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7, arg8});
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) {
        Log.verbose(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7, arg8, arg9});
    }

    @VisibleForTesting @RemovableInRelease public static void v(String arg2, String arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10) {
        Log.verbose(arg2, arg3, new Object[]{arg4, arg5, arg6, arg7, arg8, arg9, arg10});
    }

    private static void verbose(String arg1, String arg2, Object[] arg3) {
        arg2 = Log.formatLogWithStack(arg2, arg3);
        Throwable v3 = Log.getThrowableToLog(arg3);
        if(v3 != null) {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.v(Log.normalizeTag(arg1), arg2, v3);
            }
            else {
                android.util.Log.v(Log.normalizeTag(arg1), arg2, v3);
            }
        }
        else if(Log.sCallbackLog != null) {
            Log.sCallbackLog.v(Log.normalizeTag(arg1), arg2);
        }
        else {
            android.util.Log.v(Log.normalizeTag(arg1), arg2);
        }
    }

    @VisibleForTesting public static void w(String arg1, String arg2, Object[] arg3) {
        arg2 = Log.formatLog(arg2, arg3);
        Throwable v3 = Log.getThrowableToLog(arg3);
        if(v3 != null) {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.w(Log.normalizeTag(arg1), arg2, v3);
            }
            else {
                android.util.Log.w(Log.normalizeTag(arg1), arg2, v3);
            }
        }
        else if(Log.sCallbackLog != null) {
            Log.sCallbackLog.w(Log.normalizeTag(arg1), arg2);
        }
        else {
            android.util.Log.w(Log.normalizeTag(arg1), arg2);
        }
    }

    @VisibleForTesting public static void wtf(String arg2, String arg3, Object[] arg4) {
        arg3 = Log.formatLog(arg3, arg4);
        Throwable v4 = Log.getThrowableToLog(arg4);
        if(v4 != null) {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.e(Log.normalizeTag(arg2), arg3, v4);
            }

            android.util.Log.wtf(Log.normalizeTag(arg2), arg3, v4);
        }
        else {
            if(Log.sCallbackLog != null) {
                Log.sCallbackLog.e(Log.normalizeTag(arg2), arg3);
            }

            android.util.Log.wtf(Log.normalizeTag(arg2), arg3);
        }
    }
}

