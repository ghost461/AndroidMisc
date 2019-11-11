package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat$EditorCompat;

public class AppLaunchChecker {
    private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";
    private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";

    public AppLaunchChecker() {
        super();
    }

    public static boolean hasStartedFromLauncher(Context arg2) {
        return arg2.getSharedPreferences("android.support.AppLaunchChecker", 0).getBoolean("startedFromLauncher", false);
    }

    public static void onActivityCreate(Activity arg3) {
        SharedPreferences v0 = arg3.getSharedPreferences("android.support.AppLaunchChecker", 0);
        if(v0.getBoolean("startedFromLauncher", false)) {
            return;
        }

        Intent v3 = arg3.getIntent();
        if(v3 == null) {
            return;
        }

        if(("android.intent.action.MAIN".equals(v3.getAction())) && ((v3.hasCategory("android.intent.category.LAUNCHER")) || (v3.hasCategory("android.intent.category.LEANBACK_LAUNCHER")))) {
            EditorCompat.getInstance().apply(v0.edit().putBoolean("startedFromLauncher", true));
        }
    }
}

