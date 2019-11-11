package org.chromium.base;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build$VERSION;
import android.os.Process;
import android.preference.PreferenceManager;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") public class ContextUtils {
    class Holder {
        private static SharedPreferences sSharedPreferences;

        static {
            Holder.sSharedPreferences = ContextUtils.fetchAppSharedPreferences();
        }

        private Holder() {
            super();
        }

        static SharedPreferences access$100() {
            return Holder.sSharedPreferences;
        }

        static SharedPreferences access$102(SharedPreferences arg0) {
            Holder.sSharedPreferences = arg0;
            return arg0;
        }
    }

    private static final String TAG = "ContextUtils";
    private static Context sApplicationContext;
    private static String sProcessName;

    public ContextUtils() {
        super();
    }

    static SharedPreferences access$000() {
        return ContextUtils.fetchAppSharedPreferences();
    }

    private static SharedPreferences fetchAppSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(ContextUtils.sApplicationContext);
    }

    public static SharedPreferences getAppSharedPreferences() {
        return Holder.access$100();
    }

    public static AssetManager getApplicationAssets() {
        Context v0;
        for(v0 = ContextUtils.getApplicationContext(); (v0 instanceof ContextWrapper); v0 = ((ContextWrapper)v0).getBaseContext()) {
        }

        return v0.getAssets();
    }

    public static Context getApplicationContext() {
        return ContextUtils.sApplicationContext;
    }

    public static String getProcessName() {
        if(ContextUtils.sProcessName != null) {
            return ContextUtils.sProcessName;
        }

        try {
            Class v0_1 = Class.forName("android.app.ActivityThread");
            ContextUtils.sProcessName = v0_1.getMethod("getProcessName").invoke(v0_1.getMethod("currentActivityThread").invoke(null));
            return ContextUtils.sProcessName;
        }
        catch(Exception v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }

    @MainDex public static void initApplicationContext(Context arg1) {
        if(ContextUtils.sApplicationContext != null && ContextUtils.sApplicationContext != arg1) {
            throw new RuntimeException("Attempting to set multiple global application contexts.");
        }

        ContextUtils.initJavaSideApplicationContext(arg1);
    }

    @VisibleForTesting public static void initApplicationContextForTests(Context arg1) {
        if((arg1 instanceof Application)) {
            ApplicationStatus.initialize(arg1);
        }

        ContextUtils.initJavaSideApplicationContext(arg1);
        Holder.access$102(ContextUtils.fetchAppSharedPreferences());
    }

    private static void initJavaSideApplicationContext(Context arg1) {
        if(arg1 == null) {
            throw new RuntimeException("Global application context cannot be set to null.");
        }

        ContextUtils.sApplicationContext = arg1;
    }

    public static boolean isIsolatedProcess() {
        try {
            return Process.class.getMethod("isIsolated").invoke(null).booleanValue();
        }
        catch(Exception v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }

    public static boolean isMainProcess() {
        return ContextUtils.getProcessName().contains(":") ^ 1;
    }

    public static void startForegroundService(Context arg2, Intent arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            arg2.startForegroundService(arg3);
        }
        else {
            arg2.startService(arg3);
        }
    }
}

