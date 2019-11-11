package android.support.v4.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.app.AppOpsManagerCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface PermissionResult {
    }

    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED;

    private PermissionChecker() {
        super();
    }

    public static int checkCallingOrSelfPermission(@NonNull Context arg3, @NonNull String arg4) {
        String v0 = Binder.getCallingPid() == Process.myPid() ? arg3.getPackageName() : null;
        return PermissionChecker.checkPermission(arg3, arg4, Binder.getCallingPid(), Binder.getCallingUid(), v0);
    }

    public static int checkCallingPermission(@NonNull Context arg2, @NonNull String arg3, String arg4) {
        if(Binder.getCallingPid() == Process.myPid()) {
            return -1;
        }

        return PermissionChecker.checkPermission(arg2, arg3, Binder.getCallingPid(), Binder.getCallingUid(), arg4);
    }

    public static int checkPermission(@NonNull Context arg1, @NonNull String arg2, int arg3, int arg4, String arg5) {
        int v0 = -1;
        if(arg1.checkPermission(arg2, arg3, arg4) == v0) {
            return v0;
        }

        arg2 = AppOpsManagerCompat.permissionToOp(arg2);
        if(arg2 == null) {
            return 0;
        }

        if(arg5 == null) {
            String[] v4 = arg1.getPackageManager().getPackagesForUid(arg4);
            if(v4 != null) {
                if(v4.length <= 0) {
                }
                else {
                    arg5 = v4[0];
                    goto label_18;
                }
            }

            return v0;
        }

    label_18:
        if(AppOpsManagerCompat.noteProxyOp(arg1, arg2, arg5) != 0) {
            return -2;
        }

        return 0;
    }

    public static int checkSelfPermission(@NonNull Context arg3, @NonNull String arg4) {
        return PermissionChecker.checkPermission(arg3, arg4, Process.myPid(), Process.myUid(), arg3.getPackageName());
    }
}

