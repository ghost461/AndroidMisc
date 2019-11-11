package org.chromium.device.time_zone_monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") class TimeZoneMonitor {
    class org.chromium.device.time_zone_monitor.TimeZoneMonitor$1 extends BroadcastReceiver {
        org.chromium.device.time_zone_monitor.TimeZoneMonitor$1(TimeZoneMonitor arg1) {
            TimeZoneMonitor.this = arg1;
            super();
        }

        public void onReceive(Context arg3, Intent arg4) {
            if(!arg4.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
                Log.e("cr_TimeZoneMonitor", "unexpected intent", new Object[0]);
                return;
            }

            TimeZoneMonitor.this.nativeTimeZoneChangedFromJava(TimeZoneMonitor.this.mNativePtr);
        }
    }

    private static final String TAG = "cr_TimeZoneMonitor";
    private final BroadcastReceiver mBroadcastReceiver;
    private final IntentFilter mFilter;
    private long mNativePtr;

    private TimeZoneMonitor(long arg3) {
        super();
        this.mFilter = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
        this.mBroadcastReceiver = new org.chromium.device.time_zone_monitor.TimeZoneMonitor$1(this);
        this.mNativePtr = arg3;
        ContextUtils.getApplicationContext().registerReceiver(this.mBroadcastReceiver, this.mFilter);
    }

    static long access$000(TimeZoneMonitor arg2) {
        return arg2.mNativePtr;
    }

    static void access$100(TimeZoneMonitor arg0, long arg1) {
        arg0.nativeTimeZoneChangedFromJava(arg1);
    }

    @CalledByNative static TimeZoneMonitor getInstance(long arg1) {
        return new TimeZoneMonitor(arg1);
    }

    private native void nativeTimeZoneChangedFromJava(long arg1) {
    }

    @CalledByNative void stop() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.mBroadcastReceiver);
        this.mNativePtr = 0;
    }
}

