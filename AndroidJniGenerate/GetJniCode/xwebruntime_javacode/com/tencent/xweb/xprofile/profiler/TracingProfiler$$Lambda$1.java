package com.tencent.xweb.xprofile.profiler;

final class TracingProfiler$$Lambda$1 implements Runnable {
    private final TracingProfiler arg$1;
    private final String arg$2;

    TracingProfiler$$Lambda$1(TracingProfiler arg1, String arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        this.arg$1.lambda$startTracingInternal$1$TracingProfiler(this.arg$2);
    }
}

