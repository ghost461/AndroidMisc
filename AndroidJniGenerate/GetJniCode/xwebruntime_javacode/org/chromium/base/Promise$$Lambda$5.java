package org.chromium.base;

final class Promise$$Lambda$5 implements Runnable {
    private final Callback arg$1;
    private final Object arg$2;

    Promise$$Lambda$5(Callback arg1, Object arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        Promise.lambda$postCallbackToLooper$3$Promise(this.arg$1, this.arg$2);
    }
}

