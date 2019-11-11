package org.chromium.device.battery;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build$VERSION;
import android.os.Build;
import javax.annotation.Nullable;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.device.mojom.BatteryStatus;

class BatteryStatusManager {
    class org.chromium.device.battery.BatteryStatusManager$1 extends BroadcastReceiver {
        org.chromium.device.battery.BatteryStatusManager$1(BatteryStatusManager arg1) {
            BatteryStatusManager.this = arg1;
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            BatteryStatusManager.this.onReceive(arg2);
        }
    }

    @VisibleForTesting class AndroidBatteryManagerWrapper {
        private final BatteryManager mBatteryManager;

        protected AndroidBatteryManagerWrapper(BatteryManager arg1) {
            super();
            this.mBatteryManager = arg1;
        }

        @TargetApi(value=21) public int getIntProperty(int arg2) {
            return this.mBatteryManager.getIntProperty(arg2);
        }
    }

    interface BatteryStatusCallback {
        void onBatteryStatusChanged(BatteryStatus arg1);
    }

    private static final String TAG = "BatteryStatusManager";
    private AndroidBatteryManagerWrapper mAndroidBatteryManager;
    private final BatteryStatusCallback mCallback;
    private boolean mEnabled;
    private final IntentFilter mFilter;
    private final boolean mIgnoreBatteryPresentState;
    private final BroadcastReceiver mReceiver;

    static {
    }

    BatteryStatusManager(BatteryStatusCallback arg5) {
        boolean v0 = Build.MODEL.equals("Galaxy Nexus");
        AndroidBatteryManagerWrapper v1 = Build$VERSION.SDK_INT >= 21 ? new AndroidBatteryManagerWrapper(ContextUtils.getApplicationContext().getSystemService("batterymanager")) : null;
        this(arg5, v0, v1);
    }

    private BatteryStatusManager(BatteryStatusCallback arg3, boolean arg4, @Nullable AndroidBatteryManagerWrapper arg5) {
        super();
        this.mFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.mReceiver = new org.chromium.device.battery.BatteryStatusManager$1(this);
        this.mCallback = arg3;
        this.mIgnoreBatteryPresentState = arg4;
        this.mAndroidBatteryManager = arg5;
    }

    static BatteryStatusManager createBatteryStatusManagerForTesting(Context arg1, BatteryStatusCallback arg2, @Nullable AndroidBatteryManagerWrapper arg3) {
        return new BatteryStatusManager(arg2, false, arg3);
    }

    @VisibleForTesting void onReceive(Intent arg13) {
        int v1 = 0;
        if(!arg13.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
            Log.e("BatteryStatusManager", "Unexpected intent.", new Object[0]);
            return;
        }

        boolean v0 = this.mIgnoreBatteryPresentState ? true : arg13.getBooleanExtra("present", false);
        int v4 = -1;
        int v3 = arg13.getIntExtra("plugged", v4);
        if(v0) {
            if(v3 == v4) {
            }
            else {
                double v6 = (((double)arg13.getIntExtra("level", v4))) / (((double)arg13.getIntExtra("scale", v4)));
                double v8 = 0;
                double v10 = 1;
                if(Double.compare(v6, v8) < 0 || v6 > v10) {
                    v6 = v10;
                }

                v0 = v3 != 0 ? true : false;
                if(arg13.getIntExtra("status", v4) == 5) {
                    v1 = 1;
                }

                double v2 = Infinity;
                if(!v0 || v1 == 0) {
                    v8 = v2;
                }
                else {
                }

                BatteryStatus v13 = new BatteryStatus();
                v13.charging = v0;
                v13.chargingTime = v8;
                v13.dischargingTime = v2;
                v13.level = v6;
                if(this.mAndroidBatteryManager != null) {
                    this.updateBatteryStatusForLollipop(v13);
                }

                this.mCallback.onBatteryStatusChanged(v13);
                return;
            }
        }

        this.mCallback.onBatteryStatusChanged(new BatteryStatus());
    }

    boolean start() {
        if(!this.mEnabled && ContextUtils.getApplicationContext().registerReceiver(this.mReceiver, this.mFilter) != null) {
            this.mEnabled = true;
        }

        return this.mEnabled;
    }

    void stop() {
        if(this.mEnabled) {
            ContextUtils.getApplicationContext().unregisterReceiver(this.mReceiver);
            this.mEnabled = false;
        }
    }

    private void updateBatteryStatusForLollipop(BatteryStatus arg18) {
        BatteryStatus v1 = arg18;
        double v2 = (((double)this.mAndroidBatteryManager.getIntProperty(4))) / 100;
        double v4 = ((double)this.mAndroidBatteryManager.getIntProperty(1));
        double v6 = ((double)this.mAndroidBatteryManager.getIntProperty(3));
        double v9 = 3600;
        double v11 = 0;
        if(v1.charging) {
            if(v1.chargingTime == Infinity && v6 > v11) {
                v1.chargingTime = Math.ceil((1 - v2) * (v4 / v6) * v9);
            }
        }
        else if(v6 < v11) {
            v1.dischargingTime = Math.floor(v2 * (v4 / -v6) * v9);
        }
    }
}

