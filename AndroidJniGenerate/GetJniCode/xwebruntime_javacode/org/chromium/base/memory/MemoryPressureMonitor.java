package org.chromium.base.memory;

import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;
import org.chromium.base.Supplier;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.metrics.CachedMetrics$Count1MHistogramSample;

@MainDex public class MemoryPressureMonitor {
    private static final int DEFAULT_THROTTLING_INTERVAL_MS = 60000;
    public static final MemoryPressureMonitor INSTANCE;
    private Supplier mCurrentPressureSupplier;
    private boolean mIsInsideThrottlingInterval;
    private int mLastReportedPressure;
    private boolean mPollingEnabled;
    private MemoryPressureCallback mReportingCallback;
    private Integer mThrottledPressure;
    private final int mThrottlingIntervalMs;
    private final Runnable mThrottlingIntervalTask;
    private static final Count1MHistogramSample sGetMyMemoryStateFailedTime;
    private static final Count1MHistogramSample sGetMyMemoryStateSucceededTime;

    static {
        MemoryPressureMonitor.sGetMyMemoryStateSucceededTime = new Count1MHistogramSample("Android.MemoryPressureMonitor.GetMyMemoryState.Succeeded.Time");
        MemoryPressureMonitor.sGetMyMemoryStateFailedTime = new Count1MHistogramSample("Android.MemoryPressureMonitor.GetMyMemoryState.Failed.Time");
        MemoryPressureMonitor.INSTANCE = new MemoryPressureMonitor(60000);
    }

    @VisibleForTesting protected MemoryPressureMonitor(int arg2) {
        super();
        this.mLastReportedPressure = 0;
        this.mCurrentPressureSupplier = MemoryPressureMonitor$$Lambda$0.$instance;
        this.mReportingCallback = MemoryPressureMonitor$$Lambda$1.$instance;
        this.mThrottlingIntervalTask = new MemoryPressureMonitor$$Lambda$2(this);
        this.mThrottlingIntervalMs = arg2;
    }

    static final Integer bridge$lambda$0$MemoryPressureMonitor() {
        return MemoryPressureMonitor.getCurrentMemoryPressure();
    }

    final void bridge$lambda$1$MemoryPressureMonitor() {
        this.onThrottlingIntervalFinished();
    }

    public void disablePolling() {
        ThreadUtils.assertOnUiThread();
        if(!this.mPollingEnabled) {
            return;
        }

        this.mPollingEnabled = false;
    }

    private static long elapsedRealtimeNanos() {
        if(Build$VERSION.SDK_INT >= 17) {
            return SystemClock.elapsedRealtimeNanos();
        }

        return SystemClock.elapsedRealtime() * 1000000;
    }

    public void enablePolling() {
        ThreadUtils.assertOnUiThread();
        if(this.mPollingEnabled) {
            return;
        }

        this.mPollingEnabled = true;
        if(!this.mIsInsideThrottlingInterval) {
            this.reportCurrentPressure();
        }
    }

    private static Integer getCurrentMemoryPressure() {
        long v0 = MemoryPressureMonitor.elapsedRealtimeNanos();
        try {
            ActivityManager$RunningAppProcessInfo v2 = new ActivityManager$RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(v2);
            MemoryPressureMonitor.recordRealtimeNanosDuration(MemoryPressureMonitor.sGetMyMemoryStateSucceededTime, v0);
            return MemoryPressureMonitor.memoryPressureFromTrimLevel(v2.lastTrimLevel);
        }
        catch(Exception ) {
            MemoryPressureMonitor.recordRealtimeNanosDuration(MemoryPressureMonitor.sGetMyMemoryStateFailedTime, v0);
            return null;
        }
    }

    public int getLastReportedPressure() {
        ThreadUtils.assertOnUiThread();
        return this.mLastReportedPressure;
    }

    final void lambda$notifyPressure$0$MemoryPressureMonitor(int arg1) {
        this.notifyPressureOnUiThread(arg1);
    }

    @VisibleForTesting public static Integer memoryPressureFromTrimLevel(int arg1) {
        if(arg1 < 80) {
            if(arg1 == 15) {
            }
            else if(arg1 >= 40) {
                return Integer.valueOf(1);
            }
            else {
                return null;
            }
        }

        return Integer.valueOf(2);
    }

    public void notifyPressure(int arg2) {
        if(ThreadUtils.runningOnUiThread()) {
            this.notifyPressureOnUiThread(arg2);
        }
        else {
            ThreadUtils.postOnUiThread(new MemoryPressureMonitor$$Lambda$3(this, arg2));
        }
    }

    private void notifyPressureOnUiThread(int arg2) {
        if(this.mIsInsideThrottlingInterval) {
            this.mThrottledPressure = Integer.valueOf(arg2);
            return;
        }

        this.reportPressure(arg2);
    }

    private void onThrottlingIntervalFinished() {
        this.mIsInsideThrottlingInterval = false;
        if(this.mThrottledPressure != null && this.mLastReportedPressure != this.mThrottledPressure.intValue()) {
            int v0 = this.mThrottledPressure.intValue();
            this.mThrottledPressure = null;
            this.reportPressure(v0);
            return;
        }

        if((this.mPollingEnabled) && this.mLastReportedPressure == 2) {
            this.reportCurrentPressure();
        }
    }

    private static void recordRealtimeNanosDuration(Count1MHistogramSample arg5, long arg6) {
        arg5.record(((int)Math.min(TimeUnit.NANOSECONDS.toMicros(MemoryPressureMonitor.elapsedRealtimeNanos() - arg6), 0x7FFFFFFF)));
    }

    public void registerComponentCallbacks() {
        ThreadUtils.assertOnUiThread();
        ContextUtils.getApplicationContext().registerComponentCallbacks(new ComponentCallbacks2() {
            public void onConfigurationChanged(Configuration arg1) {
            }

            public void onLowMemory() {
                MemoryPressureMonitor.this.notifyPressure(2);
            }

            public void onTrimMemory(int arg2) {
                Integer v2 = MemoryPressureMonitor.memoryPressureFromTrimLevel(arg2);
                if(v2 != null) {
                    MemoryPressureMonitor.this.notifyPressure(v2.intValue());
                }
            }
        });
    }

    private void reportCurrentPressure() {
        Object v0 = this.mCurrentPressureSupplier.get();
        if(v0 != null) {
            this.reportPressure(((Integer)v0).intValue());
        }
    }

    private void reportPressure(int arg2) {
        this.startThrottlingInterval();
        this.mLastReportedPressure = arg2;
        this.mReportingCallback.onPressure(arg2);
    }

    @VisibleForTesting public void setCurrentPressureSupplierForTesting(Supplier arg1) {
        this.mCurrentPressureSupplier = arg1;
    }

    @VisibleForTesting public void setReportingCallbackForTesting(MemoryPressureCallback arg1) {
        this.mReportingCallback = arg1;
    }

    private void startThrottlingInterval() {
        ThreadUtils.postOnUiThreadDelayed(this.mThrottlingIntervalTask, ((long)this.mThrottlingIntervalMs));
        this.mIsInsideThrottlingInterval = true;
    }
}

