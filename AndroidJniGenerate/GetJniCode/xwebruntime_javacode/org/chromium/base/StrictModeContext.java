package org.chromium.base;

import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode$VmPolicy;
import android.os.StrictMode;
import java.io.Closeable;

public final class StrictModeContext implements Closeable {
    private final StrictMode$ThreadPolicy mThreadPolicy;
    private final StrictMode$VmPolicy mVmPolicy;

    private StrictModeContext(StrictMode$ThreadPolicy arg2) {
        this(arg2, null);
    }

    private StrictModeContext(StrictMode$ThreadPolicy arg1, StrictMode$VmPolicy arg2) {
        super();
        this.mThreadPolicy = arg1;
        this.mVmPolicy = arg2;
    }

    private StrictModeContext(StrictMode$VmPolicy arg2) {
        this(null, arg2);
    }

    public static StrictModeContext allowAllVmPolicies() {
        StrictMode$VmPolicy v0 = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(StrictMode$VmPolicy.LAX);
        return new StrictModeContext(v0);
    }

    public static StrictModeContext allowDiskReads() {
        return new StrictModeContext(StrictMode.allowThreadDiskReads());
    }

    public static StrictModeContext allowDiskWrites() {
        return new StrictModeContext(StrictMode.allowThreadDiskWrites());
    }

    public static StrictModeContext allowSlowCalls() {
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(v0).permitCustomSlowCalls().build());
        return new StrictModeContext(v0);
    }

    public void close() {
        if(this.mThreadPolicy != null) {
            StrictMode.setThreadPolicy(this.mThreadPolicy);
        }

        if(this.mVmPolicy != null) {
            StrictMode.setVmPolicy(this.mVmPolicy);
        }
    }
}

