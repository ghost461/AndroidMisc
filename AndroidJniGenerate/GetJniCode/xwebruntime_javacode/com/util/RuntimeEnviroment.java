package com.util;

import android.content.Context;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.XWalkPreferencesInternal;

public class RuntimeEnviroment {
    private static final int SUPPORT_M67_REPORT_SDK_VERSION = 180801;
    private static final int SUPPORT_VIDEO_TEXTUREVIEW_SDK_VERSION = 23;
    private static final String TAG = "RuntimeEnviroment";
    static Context sContext;

    public RuntimeEnviroment() {
        super();
    }

    public static boolean checkSupportM67Report() {
        Class v0 = RuntimeEnviroment.class;
        __monitor_enter(v0);
        try {
            if(RuntimeEnviroment.getXWebSDKVersion() < 180801) {
                goto label_7;
            }
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        boolean v1_1 = true;
        goto label_8;
    label_7:
        v1_1 = false;
    label_8:
        __monitor_exit(v0);
        return v1_1;
    }

    public static Context getContext() {
        Context v1_1;
        Class v0 = RuntimeEnviroment.class;
        __monitor_enter(v0);
        try {
            v1_1 = RuntimeEnviroment.sContext;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public static int getXWebSDKVersion() {
        int v1_2;
        Class v0 = RuntimeEnviroment.class;
        __monitor_enter(v0);
        try {
            v1_2 = Integer.parseInt(XWalkPreferencesInternal.getStringValue("xwebsdk-version"));
        }
        catch(Throwable v1) {
        label_22:
            __monitor_exit(v0);
            throw v1;
        }
        catch(Exception v1_1) {
            try {
                Log.e("RuntimeEnviroment", "getXWebVersion error:" + v1_1.getMessage());
            }
            catch(Throwable v1) {
                goto label_22;
            }

            __monitor_exit(v0);
            return 0;
        }

        __monitor_exit(v0);
        return v1_2;
    }

    public static int getXWebVersion() {
        int v1_2;
        Class v0 = RuntimeEnviroment.class;
        __monitor_enter(v0);
        try {
            v1_2 = Integer.parseInt(XWalkPreferencesInternal.getStringValue("xweb-version"));
        }
        catch(Throwable v1) {
        label_22:
            __monitor_exit(v0);
            throw v1;
        }
        catch(Exception v1_1) {
            try {
                Log.e("RuntimeEnviroment", "getXWebVersion error:" + v1_1.getMessage());
            }
            catch(Throwable v1) {
                goto label_22;
            }

            __monitor_exit(v0);
            return 0;
        }

        __monitor_exit(v0);
        return v1_2;
    }

    public static void init(Context arg1) {
        Class v0 = RuntimeEnviroment.class;
        __monitor_enter(v0);
        if(arg1 == null) {
            __monitor_exit(v0);
            return;
        }

        try {
            RuntimeEnviroment.sContext = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    public static boolean supportVideoTextureView() {
        Class v0 = RuntimeEnviroment.class;
        __monitor_enter(v0);
        try {
            if(RuntimeEnviroment.getXWebSDKVersion() < 23) {
                goto label_7;
            }
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        boolean v1_1 = true;
        goto label_8;
    label_7:
        v1_1 = false;
    label_8:
        __monitor_exit(v0);
        return v1_1;
    }
}

