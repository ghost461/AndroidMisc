package org.chromium.device.battery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.device.mojom.BatteryMonitor;
import org.chromium.device.mojom.BatteryStatus;
import org.chromium.mojo.bindings.Interface;
import org.chromium.services.service_manager.InterfaceFactory;

public class BatteryMonitorFactory implements InterfaceFactory {
    class org.chromium.device.battery.BatteryMonitorFactory$1 implements BatteryStatusCallback {
        org.chromium.device.battery.BatteryMonitorFactory$1(BatteryMonitorFactory arg1) {
            BatteryMonitorFactory.this = arg1;
            super();
        }

        public void onBatteryStatusChanged(BatteryStatus arg3) {
            ThreadUtils.assertOnUiThread();
            Iterator v0 = new ArrayList(BatteryMonitorFactory.this.mSubscribedMonitors).iterator();
            while(v0.hasNext()) {
                v0.next().didChange(arg3);
            }
        }
    }

    private static final String TAG = "BattMonitorFactory";
    private final BatteryStatusCallback mCallback;
    private final BatteryStatusManager mManager;
    private final HashSet mSubscribedMonitors;

    static {
    }

    public BatteryMonitorFactory() {
        super();
        this.mSubscribedMonitors = new HashSet();
        this.mCallback = new org.chromium.device.battery.BatteryMonitorFactory$1(this);
        this.mManager = new BatteryStatusManager(this.mCallback);
    }

    static HashSet access$000(BatteryMonitorFactory arg0) {
        return arg0.mSubscribedMonitors;
    }

    public BatteryMonitor createImpl() {
        ThreadUtils.assertOnUiThread();
        if((this.mSubscribedMonitors.isEmpty()) && !this.mManager.start()) {
            Log.e("BattMonitorFactory", "BatteryStatusManager failed to start.", new Object[0]);
        }

        BatteryMonitorImpl v0 = new BatteryMonitorImpl(this);
        this.mSubscribedMonitors.add(v0);
        return ((BatteryMonitor)v0);
    }

    public Interface createImpl() {
        return this.createImpl();
    }

    void unsubscribe(BatteryMonitorImpl arg2) {
        ThreadUtils.assertOnUiThread();
        this.mSubscribedMonitors.remove(arg2);
        if(this.mSubscribedMonitors.isEmpty()) {
            this.mManager.stop();
        }
    }
}

