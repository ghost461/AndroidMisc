package org.chromium.ui;

import android.content.Context;
import android.os.Handler;
import android.view.Choreographer$FrameCallback;
import android.view.Choreographer;
import org.chromium.base.TraceEvent;

public class VSyncMonitor {
    public interface Listener {
        void onVSync(VSyncMonitor arg1, long arg2);
    }

    private static final long NANOSECONDS_PER_MICROSECOND = 1000;
    private static final long NANOSECONDS_PER_SECOND = 1000000000;
    private final Choreographer mChoreographer;
    private boolean mConsecutiveVSync;
    private long mGoodStartingPointNano;
    private final Handler mHandler;
    private boolean mHaveRequestInFlight;
    private boolean mInsideVSync;
    private Listener mListener;
    private long mRefreshPeriodNano;
    private final Choreographer$FrameCallback mVSyncFrameCallback;

    static {
    }

    public VSyncMonitor(Context arg3, Listener arg4) {
        super();
        this.mHandler = new Handler();
        this.mListener = arg4;
        float v3 = arg3.getSystemService("window").getDefaultDisplay().getRefreshRate();
        boolean v4 = v3 < 30f ? true : false;
        if(v3 <= 0f) {
            v3 = 60f;
        }

        this.mRefreshPeriodNano = ((long)(1000000000f / v3));
        this.mChoreographer = Choreographer.getInstance();
        this.mVSyncFrameCallback = new Choreographer$FrameCallback(v4) {
            public void doFrame(long arg11) {
                TraceEvent.begin("VSync");
                if((this.val$useEstimatedRefreshPeriod) && (VSyncMonitor.access$000(VSyncMonitor.this))) {
                    VSyncMonitor.access$202(VSyncMonitor.this, VSyncMonitor.access$200(VSyncMonitor.this) + (((long)((((float)(arg11 - VSyncMonitor.access$100(VSyncMonitor.this) - VSyncMonitor.access$200(VSyncMonitor.this)))) * 0.1f))));
                }

                VSyncMonitor.access$102(VSyncMonitor.this, arg11);
                VSyncMonitor.access$400(VSyncMonitor.this, arg11, VSyncMonitor.access$300(VSyncMonitor.this));
                TraceEvent.end("VSync");
            }
        };
        this.mGoodStartingPointNano = this.getCurrentNanoTime();
    }

    static boolean access$000(VSyncMonitor arg0) {
        return arg0.mConsecutiveVSync;
    }

    static long access$100(VSyncMonitor arg2) {
        return arg2.mGoodStartingPointNano;
    }

    static long access$102(VSyncMonitor arg0, long arg1) {
        arg0.mGoodStartingPointNano = arg1;
        return arg1;
    }

    static long access$200(VSyncMonitor arg2) {
        return arg2.mRefreshPeriodNano;
    }

    static long access$202(VSyncMonitor arg0, long arg1) {
        arg0.mRefreshPeriodNano = arg1;
        return arg1;
    }

    static long access$300(VSyncMonitor arg2) {
        return arg2.getCurrentNanoTime();
    }

    static void access$400(VSyncMonitor arg0, long arg1, long arg3) {
        arg0.onVSyncCallback(arg1, arg3);
    }

    private long getCurrentNanoTime() {
        return System.nanoTime();
    }

    public long getVSyncPeriodInMicroseconds() {
        return this.mRefreshPeriodNano / 1000;
    }

    public boolean isInsideVSync() {
        return this.mInsideVSync;
    }

    private void onVSyncCallback(long arg3, long arg5) {
        this.mInsideVSync = true;
        this.mHaveRequestInFlight = false;
        try {
            if(this.mListener != null) {
                this.mListener.onVSync(this, arg3 / 1000);
            }
        }
        catch(Throwable v3) {
            this.mInsideVSync = false;
            throw v3;
        }

        this.mInsideVSync = false;
    }

    private void postCallback() {
        if(this.mHaveRequestInFlight) {
            return;
        }

        this.mHaveRequestInFlight = true;
        this.mConsecutiveVSync = this.mInsideVSync;
        this.mChoreographer.postFrameCallback(this.mVSyncFrameCallback);
    }

    public void requestUpdate() {
        this.postCallback();
    }
}

