package org.chromium.device.battery;

import org.chromium.base.Log;
import org.chromium.device.mojom.BatteryMonitor$QueryNextStatusResponse;
import org.chromium.device.mojom.BatteryMonitor;
import org.chromium.device.mojom.BatteryStatus;
import org.chromium.mojo.system.MojoException;

public class BatteryMonitorImpl implements BatteryMonitor {
    private static final String TAG = "BatteryMonitorImpl";
    private QueryNextStatusResponse mCallback;
    private final BatteryMonitorFactory mFactory;
    private boolean mHasStatusToReport;
    private BatteryStatus mStatus;
    private boolean mSubscribed;

    public BatteryMonitorImpl(BatteryMonitorFactory arg1) {
        super();
        this.mFactory = arg1;
        this.mHasStatusToReport = false;
        this.mSubscribed = true;
    }

    public void close() {
        this.unsubscribe();
    }

    void didChange(BatteryStatus arg1) {
        this.mStatus = arg1;
        this.mHasStatusToReport = true;
        if(this.mCallback != null) {
            this.reportStatus();
        }
    }

    public void onConnectionError(MojoException arg1) {
        this.unsubscribe();
    }

    public void queryNextStatus(QueryNextStatusResponse arg3) {
        if(this.mCallback != null) {
            Log.e("BatteryMonitorImpl", "Overlapped call to queryNextStatus!", new Object[0]);
            this.unsubscribe();
            return;
        }

        this.mCallback = arg3;
        if(this.mHasStatusToReport) {
            this.reportStatus();
        }
    }

    void reportStatus() {
        this.mCallback.call(this.mStatus);
        this.mCallback = null;
        this.mHasStatusToReport = false;
    }

    private void unsubscribe() {
        if(this.mSubscribed) {
            this.mFactory.unsubscribe(this);
            this.mSubscribed = false;
        }
    }
}

