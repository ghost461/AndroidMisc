package org.xwalk.core.internal;

import android.webkit.ValueCallback;

public class RuntimeToSdkChannel {
    public static final String KEY_GET_CONFIG_CMD = "GET_CONFIG_CMD";
    public static final String KEY_INVOKE_METHOD = "INVOKE_INSTANCE_METHOD";
    public static final String KEY_STATIC_METHOD = "INVOKE_STATIC_METHOD";
    public static final String KEY_XPROFILE_TRACING_FRAME_COST_RESULT = "KEY_XPROFILE_TRACING_FRAME_COST_RESULT";
    public static final String TAG = "RuntimeToSdkChannel";
    private static ValueCallback sCallBackToSdk;

    static {
    }

    public RuntimeToSdkChannel() {
        super();
    }

    public static String getCmd(String arg2, String arg3) {
        Object v2 = RuntimeToSdkChannel.invoke("GET_CONFIG_CMD", new String[]{arg2, arg3});
        if((v2 instanceof String)) {
            return ((String)v2);
        }

        return "";
    }

    public static void init(ValueCallback arg1) {
        Class v0 = RuntimeToSdkChannel.class;
        __monitor_enter(v0);
        try {
            RuntimeToSdkChannel.sCallBackToSdk = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    public static Object invoke(String arg4, Object arg5) {
        Object v4_1;
        Object v2;
        Class v0 = RuntimeToSdkChannel.class;
        __monitor_enter(v0);
        try {
            v2 = null;
            if(RuntimeToSdkChannel.sCallBackToSdk != null) {
                goto label_10;
            }

            Log.e("RuntimeToSdkChannel", "RuntimeToSdkChannel invoke callback is null");
        }
        catch(Throwable v4) {
            goto label_24;
        }

        __monitor_exit(v0);
        return v2;
    label_10:
        int v1 = 3;
        try {
            Object[] v1_1 = new Object[v1];
            v1_1[0] = arg4;
            v1_1[1] = arg5;
            v1_1[2] = v2;
            RuntimeToSdkChannel.sCallBackToSdk.onReceiveValue(v1_1);
            v4_1 = v1_1[2];
        }
        catch(Throwable v4) {
        label_24:
            __monitor_exit(v0);
            throw v4;
        }

        __monitor_exit(v0);
        return v4_1;
    }

    public static Object invokeInstance(Object arg2, String arg3) {
        return RuntimeToSdkChannel.invoke("INVOKE_INSTANCE_METHOD", new Object[]{arg2, arg3});
    }

    public static Object invokeInstance(Object arg2, String arg3, Class[] arg4, Object[] arg5) {
        return RuntimeToSdkChannel.invoke("INVOKE_INSTANCE_METHOD", new Object[]{arg2, arg3, arg4, arg5});
    }

    public static Object invokeStatic(String arg2, String arg3) {
        return RuntimeToSdkChannel.invoke("INVOKE_STATIC_METHOD", new Object[]{arg2, arg3});
    }

    public static Object invokeStatic(String arg2, String arg3, Class[] arg4, Object[] arg5) {
        return RuntimeToSdkChannel.invoke("INVOKE_STATIC_METHOD", new Object[]{arg2, arg3, arg4, arg5});
    }

    static void test() {
        if(RuntimeToSdkChannel.getCmd("setwebtype", "appbrand").equals(RuntimeToSdkChannel.getCmd("setwebtype", null))) {
            return;
        }

        RuntimeToSdkChannel.invokeStatic("org.xwalk.core.XWalkEnvironment", "getApplicationContext");
        if(RuntimeToSdkChannel.invokeStatic("org.xwalk.core.XWalkEnvironment", "getMMKVSharedTransportOld", new Class[]{String.class}, new Object[]{"XWEB.CMDCFG"}) == RuntimeToSdkChannel.invokeInstance("com.tencent.xweb.internal.LoadUrlWatchDog", "getStage")) {
            return;
        }
    }
}

