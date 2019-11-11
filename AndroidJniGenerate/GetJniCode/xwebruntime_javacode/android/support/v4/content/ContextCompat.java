package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

public class ContextCompat {
    private static final String TAG = "ContextCompat";
    private static final Object sLock;
    private static TypedValue sTempValue;

    static {
        ContextCompat.sLock = new Object();
    }

    protected ContextCompat() {
        super();
    }

    private static File buildPath(File arg4, String[] arg5) {
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            String v2 = arg5[v1];
            if(arg4 == null) {
                arg4 = new File(v2);
            }
            else if(v2 != null) {
                arg4 = new File(arg4, v2);
            }
        }

        return arg4;
    }

    public static int checkSelfPermission(@NonNull Context arg2, @NonNull String arg3) {
        if(arg3 == null) {
            throw new IllegalArgumentException("permission is null");
        }

        return arg2.checkPermission(arg3, Process.myPid(), Process.myUid());
    }

    public static Context createDeviceProtectedStorageContext(Context arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return arg2.createDeviceProtectedStorageContext();
        }

        return null;
    }

    private static File createFilesDir(File arg4) {
        Class v0 = ContextCompat.class;
        __monitor_enter(v0);
        try {
            if(!arg4.exists() && !arg4.mkdirs()) {
                if(arg4.exists()) {
                    goto label_8;
                }
                else {
                    goto label_10;
                }
            }

            goto label_22;
        }
        catch(Throwable v4) {
            goto label_25;
        }

    label_8:
        __monitor_exit(v0);
        return arg4;
        try {
        label_10:
            Log.w("ContextCompat", "Unable to create files subdir " + arg4.getPath());
        }
        catch(Throwable v4) {
        label_25:
            __monitor_exit(v0);
            throw v4;
        }

        __monitor_exit(v0);
        return null;
    label_22:
        __monitor_exit(v0);
        return arg4;
    }

    public static File getCodeCacheDir(Context arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getCodeCacheDir();
        }

        return ContextCompat.createFilesDir(new File(arg2.getApplicationInfo().dataDir, "code_cache"));
    }

    @ColorInt public static final int getColor(Context arg2, @ColorRes int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColor(arg3);
        }

        return arg2.getResources().getColor(arg3);
    }

    public static final ColorStateList getColorStateList(Context arg2, @ColorRes int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColorStateList(arg3);
        }

        return arg2.getResources().getColorStateList(arg3);
    }

    public static File getDataDir(Context arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return arg2.getDataDir();
        }

        String v2 = arg2.getApplicationInfo().dataDir;
        File v0 = v2 != null ? new File(v2) : null;
        return v0;
    }

    public static final Drawable getDrawable(Context arg4, @DrawableRes int arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg4.getDrawable(arg5);
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return arg4.getResources().getDrawable(arg5);
        }

        Object v0 = ContextCompat.sLock;
        __monitor_enter(v0);
        try {
            if(ContextCompat.sTempValue == null) {
                ContextCompat.sTempValue = new TypedValue();
            }

            arg4.getResources().getValue(arg5, ContextCompat.sTempValue, true);
            arg5 = ContextCompat.sTempValue.resourceId;
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            try {
            label_29:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_29;
            }

            throw v4;
        }

        return arg4.getResources().getDrawable(arg5);
    }

    public static File[] getExternalCacheDirs(Context arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getExternalCacheDirs();
        }

        return new File[]{arg2.getExternalCacheDir()};
    }

    public static File[] getExternalFilesDirs(Context arg2, String arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getExternalFilesDirs(arg3);
        }

        return new File[]{arg2.getExternalFilesDir(arg3)};
    }

    public static final File getNoBackupFilesDir(Context arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getNoBackupFilesDir();
        }

        return ContextCompat.createFilesDir(new File(arg2.getApplicationInfo().dataDir, "no_backup"));
    }

    public static File[] getObbDirs(Context arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getObbDirs();
        }

        return new File[]{arg2.getObbDir()};
    }

    public static boolean isDeviceProtectedStorage(Context arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return arg2.isDeviceProtectedStorage();
        }

        return 0;
    }

    public static boolean startActivities(Context arg2, Intent[] arg3, Bundle arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.startActivities(arg3, arg4);
        }
        else {
            arg2.startActivities(arg3);
        }

        return 1;
    }

    public static boolean startActivities(Context arg1, Intent[] arg2) {
        return ContextCompat.startActivities(arg1, arg2, null);
    }

    public static void startActivity(Context arg2, Intent arg3, @Nullable Bundle arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.startActivity(arg3, arg4);
        }
        else {
            arg2.startActivity(arg3);
        }
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

