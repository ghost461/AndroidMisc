package org.chromium.base.memory;

final class MemoryPressureMonitor$$Lambda$3 implements Runnable {
    private final MemoryPressureMonitor arg$1;
    private final int arg$2;

    MemoryPressureMonitor$$Lambda$3(MemoryPressureMonitor arg1, int arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        this.arg$1.lambda$notifyPressure$0$MemoryPressureMonitor(this.arg$2);
    }
}

