package org.chromium.base;

final class Promise$$Lambda$7 implements Callback {
    private final Promise arg$1;

    private Promise$$Lambda$7(Promise arg1) {
        super();
        this.arg$1 = arg1;
    }

    static Callback get$Lambda(Promise arg1) {
        return new Promise$$Lambda$7(arg1);
    }

    public void onResult(Object arg2) {
        this.arg$1.reject(((Exception)arg2));
    }
}

