package org.chromium.base.process_launcher;

import org.chromium.base.memory.MemoryPressureCallback;

final class ChildProcessConnection$$Lambda$1 implements Runnable {
    private final MemoryPressureCallback arg$1;

    ChildProcessConnection$$Lambda$1(MemoryPressureCallback arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void run() {
        ChildProcessConnection.lambda$onServiceConnectedOnLauncherThread$0$ChildProcessConnection(this.arg$1);
    }
}

