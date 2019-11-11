package android.support.v4.app;

import android.app.Service;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ServiceCompat {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface StopForegroundFlags {
    }

    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    private ServiceCompat() {
        super();
    }

    public static void stopForeground(Service arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 24) {
            arg2.stopForeground(arg3);
        }
        else {
            boolean v0 = true;
            if((arg3 & 1) != 0) {
            }
            else {
                v0 = false;
            }

            arg2.stopForeground(v0);
        }
    }
}

