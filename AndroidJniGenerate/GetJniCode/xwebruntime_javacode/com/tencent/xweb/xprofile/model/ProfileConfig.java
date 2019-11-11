package com.tencent.xweb.xprofile.model;

public class ProfileConfig {
    public int enableWindowPerformanceSampleRatio;
    public TraceConfig traceConfig;

    public ProfileConfig() {
        super();
    }

    public String toString() {
        return "ProfileConfig{traceConfig=" + this.traceConfig + ", enableWindowPerformanceSampleRatio=" + this.enableWindowPerformanceSampleRatio + '}';
    }
}

