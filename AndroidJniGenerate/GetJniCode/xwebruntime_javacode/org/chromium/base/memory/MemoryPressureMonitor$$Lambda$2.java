package org.chromium.base.memory;

final class MemoryPressureMonitor$$Lambda$2 implements Runnable {
    private final MemoryPressureMonitor arg$1;

    MemoryPressureMonitor$$Lambda$2(MemoryPressureMonitor arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void run() {
        this.arg$1.bridge$lambda$1$MemoryPressureMonitor();
    }
}

