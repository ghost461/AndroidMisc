package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;

public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_IGNORED = 1;

    private AppOpsManagerCompat() {
        super();
    }

    public static int noteOp(@NonNull Context arg2, @NonNull String arg3, int arg4, @NonNull String arg5) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getSystemService(AppOpsManager.class).noteOp(arg3, arg4, arg5);
        }

        return 1;
    }

    public static int noteProxyOp(@NonNull Context arg2, @NonNull String arg3, @NonNull String arg4) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getSystemService(AppOpsManager.class).noteProxyOp(arg3, arg4);
        }

        return 1;
    }

    public static String permissionToOp(@NonNull String arg2) {
        if(Build$VERSION.SDK_INT >= 23) {
            return AppOpsManager.permissionToOp(arg2);
        }

        return null;
    }
}

