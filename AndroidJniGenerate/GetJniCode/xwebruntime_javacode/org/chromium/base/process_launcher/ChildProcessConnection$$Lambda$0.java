package org.chromium.base.process_launcher;

import org.chromium.base.memory.MemoryPressureCallback;

final class ChildProcessConnection$$Lambda$0 implements MemoryPressureCallback {
    private final ChildProcessConnection arg$1;

    ChildProcessConnection$$Lambda$0(ChildProcessConnection arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void onPressure(int arg2) {
        this.arg$1.bridge$lambda$0$ChildProcessConnection(arg2);
    }
}

