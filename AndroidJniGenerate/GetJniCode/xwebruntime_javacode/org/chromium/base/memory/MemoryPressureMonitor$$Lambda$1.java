package org.chromium.base.memory;

import org.chromium.base.MemoryPressureListener;

final class MemoryPressureMonitor$$Lambda$1 implements MemoryPressureCallback {
    static final MemoryPressureCallback $instance;

    static {
        MemoryPressureMonitor$$Lambda$1.$instance = new MemoryPressureMonitor$$Lambda$1();
    }

    private MemoryPressureMonitor$$Lambda$1() {
        super();
    }

    public void onPressure(int arg1) {
        MemoryPressureListener.notifyMemoryPressure(arg1);
    }
}

