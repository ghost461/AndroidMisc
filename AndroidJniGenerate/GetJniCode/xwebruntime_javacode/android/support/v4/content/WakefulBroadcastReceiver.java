package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager$WakeLock;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
    private static int mNextId = 1;
    private static final SparseArray sActiveWakeLocks;

    static {
        WakefulBroadcastReceiver.sActiveWakeLocks = new SparseArray();
    }

    public WakefulBroadcastReceiver() {
        super();
    }

    public static boolean completeWakefulIntent(Intent arg5) {
        int v5 = arg5.getIntExtra("android.support.content.wakelockid", 0);
        if(v5 == 0) {
            return 0;
        }

        SparseArray v0 = WakefulBroadcastReceiver.sActiveWakeLocks;
        __monitor_enter(v0);
        try {
            Object v1 = WakefulBroadcastReceiver.sActiveWakeLocks.get(v5);
            if(v1 != null) {
                ((PowerManager$WakeLock)v1).release();
                WakefulBroadcastReceiver.sActiveWakeLocks.remove(v5);
                __monitor_exit(v0);
                return 1;
            }

            Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + v5);
            __monitor_exit(v0);
            return 1;
        label_27:
            __monitor_exit(v0);
        }
        catch(Throwable v5_1) {
            goto label_27;
        }

        throw v5_1;
    }

    public static ComponentName startWakefulService(Context arg5, Intent arg6) {
        SparseArray v0 = WakefulBroadcastReceiver.sActiveWakeLocks;
        __monitor_enter(v0);
        try {
            int v1 = WakefulBroadcastReceiver.mNextId;
            ++WakefulBroadcastReceiver.mNextId;
            if(WakefulBroadcastReceiver.mNextId <= 0) {
                WakefulBroadcastReceiver.mNextId = 1;
            }

            arg6.putExtra("android.support.content.wakelockid", v1);
            ComponentName v6 = arg5.startService(arg6);
            if(v6 == null) {
                __monitor_exit(v0);
                return null;
            }

            Object v5_1 = arg5.getSystemService("power");
            StringBuilder v2 = new StringBuilder();
            v2.append("wake:");
            v2.append(v6.flattenToShortString());
            PowerManager$WakeLock v5_2 = ((PowerManager)v5_1).newWakeLock(1, v2.toString());
            v5_2.setReferenceCounted(false);
            v5_2.acquire(60000);
            WakefulBroadcastReceiver.sActiveWakeLocks.put(v1, v5_2);
            __monitor_exit(v0);
            return v6;
        label_36:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_36;
        }

        throw v5;
    }
}

