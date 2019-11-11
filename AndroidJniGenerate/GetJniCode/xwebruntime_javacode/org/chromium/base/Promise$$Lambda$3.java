package org.chromium.base;

final class Promise$$Lambda$3 implements Callback {
    private final AsyncFunction arg$1;
    private final Promise arg$2;

    Promise$$Lambda$3(AsyncFunction arg1, Promise arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void onResult(Object arg3) {
        Promise.lambda$then$2$Promise(this.arg$1, this.arg$2, arg3);
    }
}

