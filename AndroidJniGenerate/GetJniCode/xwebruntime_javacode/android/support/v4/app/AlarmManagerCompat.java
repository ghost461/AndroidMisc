package android.support.v4.app;

import android.app.AlarmManager$AlarmClockInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build$VERSION;

public final class AlarmManagerCompat {
    private AlarmManagerCompat() {
        super();
    }

    public static void setAlarmClock(AlarmManager arg2, long arg3, PendingIntent arg5, PendingIntent arg6) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setAlarmClock(new AlarmManager$AlarmClockInfo(arg3, arg5), arg6);
        }
        else {
            AlarmManagerCompat.setExact(arg2, 0, arg3, arg6);
        }
    }

    public static void setAndAllowWhileIdle(AlarmManager arg2, int arg3, long arg4, PendingIntent arg6) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg2.setAndAllowWhileIdle(arg3, arg4, arg6);
        }
        else {
            arg2.set(arg3, arg4, arg6);
        }
    }

    public static void setExact(AlarmManager arg2, int arg3, long arg4, PendingIntent arg6) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.setExact(arg3, arg4, arg6);
        }
        else {
            arg2.set(arg3, arg4, arg6);
        }
    }

    public static void setExactAndAllowWhileIdle(AlarmManager arg2, int arg3, long arg4, PendingIntent arg6) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg2.setExactAndAllowWhileIdle(arg3, arg4, arg6);
        }
        else {
            AlarmManagerCompat.setExact(arg2, arg3, arg4, arg6);
        }
    }
}

