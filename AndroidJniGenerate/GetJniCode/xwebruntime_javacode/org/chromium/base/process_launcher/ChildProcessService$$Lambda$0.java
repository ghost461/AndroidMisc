package org.chromium.base.process_launcher;

final class ChildProcessService$$Lambda$0 implements Runnable {
    private final ChildProcessService arg$1;

    ChildProcessService$$Lambda$0(ChildProcessService arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void run() {
        this.arg$1.lambda$onBind$0$ChildProcessService();
    }
}

