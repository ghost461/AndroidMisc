package org.chromium.content.browser;

final class MemoryDumperAndroid$$Lambda$0 implements Runnable {
    static final Runnable $instance;

    static {
        MemoryDumperAndroid$$Lambda$0.$instance = new MemoryDumperAndroid$$Lambda$0();
    }

    private MemoryDumperAndroid$$Lambda$0() {
        super();
    }

    public void run() {
        MemoryDumperAndroid.bridge$lambda$0$MemoryDumperAndroid();
    }
}

