package org.chromium.base.process_launcher;

final class ChildProcessConnection$$Lambda$3 implements Runnable {
    private final ChildProcessConnection arg$1;
    private final int arg$2;

    ChildProcessConnection$$Lambda$3(ChildProcessConnection arg1, int arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        this.arg$1.lambda$onMemoryPressure$2$ChildProcessConnection(this.arg$2);
    }
}

