package org.chromium.device.geolocation;

import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class MockLocationProvider implements LocationProvider {
    private static final int UPDATE_LOCATION_MSG = 100;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mIsRunning;
    private final Object mLock;

    static {
    }

    public MockLocationProvider() {
        super();
        this.mLock = new Object();
    }

    static Object access$000(MockLocationProvider arg0) {
        return arg0.mLock;
    }

    static void access$100(MockLocationProvider arg0) {
        arg0.newLocation();
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    private void newLocation() {
        Location v0 = new Location("MockLocationProvider");
        v0.setTime(System.currentTimeMillis());
        v0.setAccuracy(0.5f);
        LocationProviderAdapter.onNewLocationAvailable(v0);
    }

    public void start(boolean arg3) {
        if(this.mIsRunning) {
            return;
        }

        if(this.mHandlerThread == null) {
            this.startMockLocationProviderThread();
        }

        this.mIsRunning = true;
        Object v3 = this.mLock;
        __monitor_enter(v3);
        try {
            this.mHandler.sendEmptyMessage(100);
            __monitor_exit(v3);
            return;
        label_16:
            __monitor_exit(v3);
        }
        catch(Throwable v0) {
            goto label_16;
        }

        throw v0;
    }

    private void startMockLocationProviderThread() {
        this.mHandlerThread = new HandlerThread("MockLocationProviderImpl");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(Message arg5) {
                Object v0 = MockLocationProvider.this.mLock;
                __monitor_enter(v0);
                try {
                    int v1 = 100;
                    if(arg5.what == v1) {
                        MockLocationProvider.this.newLocation();
                        this.sendEmptyMessageDelayed(v1, 0xFA);
                    }

                    __monitor_exit(v0);
                    return;
                label_13:
                    __monitor_exit(v0);
                }
                catch(Throwable v5) {
                    goto label_13;
                }

                throw v5;
            }
        };
    }

    public void stop() {
        if(!this.mIsRunning) {
            return;
        }

        this.mIsRunning = false;
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.mHandler.removeMessages(100);
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_13;
        }

        throw v1;
    }

    public void stopUpdates() {
        if(this.mHandlerThread != null) {
            this.mHandlerThread.quit();
        }
    }
}

