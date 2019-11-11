package org.chromium.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public class PowerMonitor {
    private boolean mIsBatteryPower;
    private static PowerMonitor sInstance;

    static {
    }

    private PowerMonitor() {
        super();
    }

    static void access$000(Intent arg0) {
        PowerMonitor.onBatteryChargingChanged(arg0);
    }

    public static void create() {
        ThreadUtils.assertOnUiThread();
        if(PowerMonitor.sInstance != null) {
            return;
        }

        Context v0 = ContextUtils.getApplicationContext();
        PowerMonitor.sInstance = new PowerMonitor();
        Intent v1 = v0.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if(v1 != null) {
            PowerMonitor.onBatteryChargingChanged(v1);
        }

        IntentFilter v1_1 = new IntentFilter();
        v1_1.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        v1_1.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        v0.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                PowerMonitor.onBatteryChargingChanged(arg2);
            }
        }, v1_1);
    }

    public static void createForTests() {
        PowerMonitor.sInstance = new PowerMonitor();
    }

    @CalledByNative private static boolean isBatteryPower() {
        if(PowerMonitor.sInstance == null) {
            PowerMonitor.create();
        }

        return PowerMonitor.sInstance.mIsBatteryPower;
    }

    private static native void nativeOnBatteryChargingChanged() {
    }

    private static void onBatteryChargingChanged(Intent arg3) {
        int v3 = arg3.getIntExtra("plugged", -1);
        PowerMonitor v0 = PowerMonitor.sInstance;
        boolean v2 = true;
        if(v3 == 2 || v3 == 1) {
            v2 = false;
        }
        else {
        }

        v0.mIsBatteryPower = v2;
        PowerMonitor.nativeOnBatteryChargingChanged();
    }
}

