package org.xwalk.core.internal;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") public class XWalkPathHelper {
    private static final String TAG = "XWalkPathHelper";
    static boolean sIsInitDirectory = false;
    static boolean sIsInitialize = false;

    static {
    }

    public XWalkPathHelper() {
        super();
    }

    public static void initExternalCacheDirectory(Context arg3) {
        Class v0 = XWalkPathHelper.class;
        __monitor_enter(v0);
        try {
            if(!XWalkPathHelper.sIsInitDirectory) {
                goto label_6;
            }
        }
        catch(Throwable v3) {
            goto label_22;
        }

        __monitor_exit(v0);
        return;
        try {
        label_6:
            String v1 = Environment.getExternalStorageState();
            if(("mounted".equals(v1)) || ("mounted_ro".equals(v1))) {
                File v3_1 = arg3.getExternalCacheDir();
                if(v3_1 != null) {
                    XWalkPathHelper.sIsInitDirectory = true;
                    XWalkPathHelper.setExternalCacheDirectory(v3_1.getPath());
                }
            }
        }
        catch(Throwable v3) {
        label_22:
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    public static void initialize() {
        int v5;
        Class v0 = XWalkPathHelper.class;
        __monitor_enter(v0);
        try {
            if(!XWalkPathHelper.sIsInitialize) {
                goto label_7;
            }
        }
        catch(Throwable v1) {
            goto label_72;
        }

        __monitor_exit(v0);
        return;
        try {
        label_7:
            XWalkPathHelper.sIsInitialize = true;
            XWalkPathHelper.nativeSetDirectory("EXTERNAL", Environment.getExternalStorageDirectory().getPath());
            String[] v3 = new String[9];
            v5 = 0;
            v3[0] = "ALARMS";
            v3[1] = "DCIM";
            v3[2] = "DOWNLOADS";
            v3[3] = "MOVIES";
            v3[4] = "MUSIC";
            v3[5] = "NOTIFICATIONS";
            v3[6] = "PICTURES";
            v3[7] = "PODCASTS";
            v3[8] = "RINGTONES";
            String[] v2 = new String[]{Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_DCIM, Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_RINGTONES};
            while(true) {
            label_59:
                if(v5 >= v3.length) {
                    goto label_69;
                }

                File v1_1 = Environment.getExternalStoragePublicDirectory(v2[v5]);
                if(v1_1 != null) {
                    XWalkPathHelper.nativeSetDirectory(v3[v5], v1_1.getPath());
                }

                break;
            }
        }
        catch(Throwable v1) {
            goto label_72;
        }

        ++v5;
        goto label_59;
    label_69:
        __monitor_exit(v0);
        return;
    label_72:
        __monitor_exit(v0);
        throw v1;
    }

    private static native void nativeSetDirectory(String arg0, String arg1) {
    }

    public static void setCacheDirectory(String arg1) {
        XWalkPathHelper.nativeSetDirectory("CACHEDIR", arg1);
    }

    public static void setExternalCacheDirectory(String arg1) {
        XWalkPathHelper.nativeSetDirectory("EXTERNAL_CACHEDIR", arg1);
    }
}

