package org.chromium.base;

final class Promise$$Lambda$0 implements Callback {
    static final Callback $instance;

    static {
        Promise$$Lambda$0.$instance = new Promise$$Lambda$0();
    }

    private Promise$$Lambda$0() {
        super();
    }

    public void onResult(Object arg1) {
        Promise.lambda$then$0$Promise(((Exception)arg1));
    }
}

