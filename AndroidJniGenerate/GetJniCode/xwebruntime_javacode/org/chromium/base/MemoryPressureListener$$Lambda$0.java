package org.chromium.base;

import org.chromium.base.memory.MemoryPressureCallback;

final class MemoryPressureListener$$Lambda$0 implements MemoryPressureCallback {
    static final MemoryPressureCallback $instance;

    static {
        MemoryPressureListener$$Lambda$0.$instance = new MemoryPressureListener$$Lambda$0();
    }

    private MemoryPressureListener$$Lambda$0() {
        super();
    }

    public void onPressure(int arg1) {
        MemoryPressureListener.bridge$lambda$0$MemoryPressureListener(arg1);
    }
}

