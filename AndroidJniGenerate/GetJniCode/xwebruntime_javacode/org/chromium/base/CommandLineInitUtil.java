package org.chromium.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build$VERSION;
import android.provider.Settings$Global;
import android.provider.Settings$System;
import android.support.annotation.Nullable;
import java.io.File;

public final class CommandLineInitUtil {
    private static final String COMMAND_LINE_FILE_PATH = "/data/local";
    private static final String COMMAND_LINE_FILE_PATH_DEBUG_APP = "/data/local/tmp";

    static {
    }

    private CommandLineInitUtil() {
        super();
    }

    @SuppressLint(value={"NewApi"}) private static String getDebugAppJBMR1(Context arg3) {
        int v1 = 1;
        if(Settings$Global.getInt(arg3.getContentResolver(), "adb_enabled", 0) == 1) {
        }
        else {
            v1 = 0;
        }

        if(v1 != 0) {
            return Settings$Global.getString(arg3.getContentResolver(), "debug_app");
        }

        return null;
    }

    private static String getDebugAppPreJBMR1(Context arg3) {
        int v1 = 1;
        if(Settings$System.getInt(arg3.getContentResolver(), "adb_enabled", 0) == 1) {
        }
        else {
            v1 = 0;
        }

        if(v1 != 0) {
            return Settings$System.getString(arg3.getContentResolver(), "debug_app");
        }

        return null;
    }

    public static void initCommandLine(String arg1) {
        CommandLineInitUtil.initCommandLine(arg1, null);
    }

    public static void initCommandLine(String arg2, @Nullable Supplier arg3) {
        File v0 = new File("/data/local/tmp", arg2);
        if(!v0.exists() || !CommandLineInitUtil.shouldUseDebugCommandLine(arg3)) {
            v0 = new File("/data/local", arg2);
        }

        CommandLine.initFromFile(v0.getPath());
    }

    private static boolean shouldUseDebugCommandLine(@Nullable Supplier arg3) {
        boolean v0 = true;
        if(arg3 != null && (arg3.get().booleanValue())) {
            return 1;
        }

        Context v3 = ContextUtils.getApplicationContext();
        String v1 = Build$VERSION.SDK_INT < 17 ? CommandLineInitUtil.getDebugAppPreJBMR1(v3) : CommandLineInitUtil.getDebugAppJBMR1(v3);
        if(!v3.getPackageName().equals(v1)) {
            if(BuildInfo.isDebugAndroid()) {
            }
            else {
                v0 = false;
            }
        }

        return v0;
    }
}

