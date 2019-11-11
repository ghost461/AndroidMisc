package com.tencent.xweb.xprofile.profiler;

final class TracingProfiler$$Lambda$0 implements Runnable {
    private final TracingProfiler arg$1;

    TracingProfiler$$Lambda$0(TracingProfiler arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void run() {
        this.arg$1.lambda$startFrameCostTracingInternal$0$TracingProfiler();
    }
}

