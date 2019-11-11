package com.tencent.xwebview.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.xwalk.core.internal.Log;

public class CompatibilityUtil {
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_ZTE_VERSION_CODE = "ro.product.brand";
    private static final String PREFERENCE_KEY_IS_SPECIAL = "needAddSpecialView";
    private static final String SHARED_PREFERENCE_NAME = "CompatibilityPreference";
    private static final String TAG = "CompatibilityUtil";
    private static final String VALUE_ZTE_VERSION_CODE = "ZTE";
    private static boolean sIsChecked;
    private static boolean sIsMiUiOrZTE;
    private static boolean sIsMiUiOrZTEChecked;

    public CompatibilityUtil() {
        super();
    }

    static String access$000(String arg0) {
        return CompatibilityUtil.getSystemProperty(arg0);
    }

    static SharedPreferences access$100(Context arg0) {
        return CompatibilityUtil.getSharedPreference(arg0);
    }

    private static void checkMiUiOrZTE(Context arg4) {
        Class v0 = CompatibilityUtil.class;
        __monitor_enter(v0);
        try {
            SharedPreferences v1 = CompatibilityUtil.getSharedPreference(arg4);
            if(v1.contains("needAddSpecialView")) {
                CompatibilityUtil.sIsMiUiOrZTE = v1.getBoolean("needAddSpecialView", false);
                Log.i("CompatibilityUtil", "check MiUi or ZTE finished with result: " + CompatibilityUtil.sIsMiUiOrZTE);
            }
            else {
                CompatibilityUtil.sIsMiUiOrZTE = false;
                CompatibilityUtil.initPreference(arg4);
                Log.i("CompatibilityUtil", "check MiUi or ZTE fail due to no cached result.");
            }

            CompatibilityUtil.sIsMiUiOrZTEChecked = true;
        }
        catch(Throwable v4) {
            __monitor_exit(v0);
            throw v4;
        }

        __monitor_exit(v0);
    }

    private static SharedPreferences getSharedPreference(Context arg2) {
        return arg2.getSharedPreferences("CompatibilityPreference", 0);
    }

    private static String getSystemProperty(String arg4) {
        BufferedReader v1_1;
        String v0 = null;
        try {
            Runtime v1 = Runtime.getRuntime();
            StringBuilder v2 = new StringBuilder();
            v2.append("getprop ");
            v2.append(arg4);
            v1_1 = new BufferedReader(new InputStreamReader(v1.exec(v2.toString()).getInputStream()), 0x400);
        }
        catch(Throwable v4) {
            goto label_44;
        }
        catch(IOException v4_1) {
            v1_1 = ((BufferedReader)v0);
            goto label_31;
        }

        try {
            arg4 = v1_1.readLine();
            v1_1.close();
            if(v1_1 == null) {
                return arg4;
            }

            goto label_18;
        }
        catch(Throwable v4) {
        label_43:
            BufferedReader v0_1 = v1_1;
        }
        catch(IOException v4_1) {
            try {
            label_31:
                Log.e("CompatibilityUtil", "getSystemProperty ", ((Throwable)v4_1));
                if(v1_1 == null) {
                    return v0;
                }
            }
            catch(Throwable v4) {
                goto label_43;
            }

            try {
                v1_1.close();
            }
            catch(IOException v4_1) {
                Log.e("CompatibilityUtil", "getSystemProperty Exception while closing InputStream", ((Throwable)v4_1));
            }

            return v0;
        }

    label_44:
        if((((BufferedReader)v0)) != null) {
            try {
                ((BufferedReader)v0).close();
            }
            catch(IOException v0_2) {
                Log.e("CompatibilityUtil", "getSystemProperty Exception while closing InputStream", ((Throwable)v0_2));
            }
        }

        throw v4;
        try {
        label_18:
            v1_1.close();
        }
        catch(IOException v0_2) {
            Log.e("CompatibilityUtil", "getSystemProperty Exception while closing InputStream", ((Throwable)v0_2));
        }

        return arg4;
    }

    private static void initPreference(Context arg3) {
        Class v0 = CompatibilityUtil.class;
        __monitor_enter(v0);
        try {
            if(!CompatibilityUtil.sIsChecked) {
                CompatibilityUtil.sIsChecked = true;
                new Thread(new Runnable(arg3) {
                    public void run() {
                        int v0 = TextUtils.isEmpty(CompatibilityUtil.getSystemProperty("ro.miui.ui.version.code")) ^ 1;
                        boolean v1 = "ZTE".equals(CompatibilityUtil.getSystemProperty("ro.product.brand"));
                        CompatibilityUtil.getSharedPreference(this.val$context).edit().putBoolean("needAddSpecialView", v0 | (((int)v1))).apply();
                        Log.i("CompatibilityUtil", "check MiUi or ZTE from prop finished and will take effect after reboot. isMIUI:" + (((boolean)v0)) + ", isZTE:" + v1);
                    }
                }).start();
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    public static boolean needAddSpecialView(Context arg2) {
        boolean v2_1;
        Class v0 = CompatibilityUtil.class;
        __monitor_enter(v0);
        try {
            if(!CompatibilityUtil.sIsMiUiOrZTEChecked) {
                CompatibilityUtil.checkMiUiOrZTE(arg2);
            }

            v2_1 = CompatibilityUtil.sIsMiUiOrZTE;
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }
}

