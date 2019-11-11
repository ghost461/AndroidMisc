package org.xwalk.core.internal;

import org.chromium.base.Log$CallbackLog;

public class Log {
    private static XWalkLogMessageListenerInternal m_log_callback;

    static {
    }

    public Log() {
        super();
    }

    public static void SetLogCallBack(XWalkLogMessageListenerInternal arg0) {
        Log.m_log_callback = arg0;
        org.chromium.base.Log.setCallbackLog(new CallbackLog() {
            public void d(String arg1, String arg2) {
                android.util.Log.d(arg1, arg2);
            }

            public void d(String arg1, String arg2, Throwable arg3) {
                android.util.Log.d(arg1, arg2, arg3);
            }

            public void e(String arg1, String arg2) {
                Log.e(arg1, arg2);
            }

            public void e(String arg1, String arg2, Throwable arg3) {
                Log.e(arg1, arg2, arg3);
            }

            public void i(String arg1, String arg2) {
                Log.i(arg1, arg2);
            }

            public void i(String arg1, String arg2, Throwable arg3) {
                Log.i(arg1, arg2, arg3);
            }

            public void v(String arg1, String arg2) {
                android.util.Log.v(arg1, arg2);
            }

            public void v(String arg1, String arg2, Throwable arg3) {
                android.util.Log.v(arg1, arg2, arg3);
            }

            public void w(String arg1, String arg2) {
                Log.w(arg1, arg2);
            }

            public void w(String arg1, String arg2, Throwable arg3) {
                Log.w(arg1, arg2, arg3);
            }
        });
    }

    public static void d(String arg5, String arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg5, arg6));
            return;
        }

        android.util.Log.d(arg5, arg6);
    }

    public static void d(String arg4, String arg5, Throwable arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg4, arg5));
            return;
        }

        android.util.Log.d(arg4, arg5, arg6);
    }

    public static void e(String arg5, String arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg5, arg6));
            return;
        }

        android.util.Log.e(arg5, arg6);
    }

    public static void e(String arg4, String arg5, Throwable arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg4, arg5));
            return;
        }

        android.util.Log.e(arg4, arg5, arg6);
    }

    public static void f(String arg5, String arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg5, arg6));
            return;
        }

        android.util.Log.i(arg5, arg6);
    }

    public static void i(String arg5, String arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg5, arg6));
            return;
        }

        android.util.Log.i(arg5, arg6);
    }

    public static void i(String arg4, String arg5, Throwable arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg4, arg5));
            return;
        }

        android.util.Log.i(arg4, arg5, arg6);
    }

    public static void v(String arg5, String arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg5, arg6));
            return;
        }

        android.util.Log.v(arg5, arg6);
    }

    public static void v(String arg4, String arg5, Throwable arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg4, arg5));
            return;
        }

        android.util.Log.v(arg4, arg5, arg6);
    }

    public static void w(String arg5, String arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg5, arg6));
            return;
        }

        android.util.Log.w(arg5, arg6);
    }

    public static void w(String arg4, String arg5, Throwable arg6) {
        if(Log.m_log_callback != null) {
            Log.m_log_callback.onLogMessage(0, "", 0, String.format("[%s] %s", arg4, arg5));
            return;
        }

        android.util.Log.w(arg4, arg5, arg6);
    }
}

