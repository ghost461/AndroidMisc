package org.chromium.base.memory;

import org.chromium.base.Supplier;

final class MemoryPressureMonitor$$Lambda$0 implements Supplier {
    static final Supplier $instance;

    static {
        MemoryPressureMonitor$$Lambda$0.$instance = new MemoryPressureMonitor$$Lambda$0();
    }

    private MemoryPressureMonitor$$Lambda$0() {
        super();
    }

    public Object get() {
        return MemoryPressureMonitor.bridge$lambda$0$MemoryPressureMonitor();
    }
}

