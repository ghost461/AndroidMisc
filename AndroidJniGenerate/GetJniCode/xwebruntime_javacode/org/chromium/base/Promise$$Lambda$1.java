package org.chromium.base;

final class Promise$$Lambda$1 implements Callback {
    private final Promise arg$1;
    private final Function arg$2;

    Promise$$Lambda$1(Promise arg1, Function arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void onResult(Object arg3) {
        Promise.lambda$then$1$Promise(this.arg$1, this.arg$2, arg3);
    }
}

