package org.chromium.base.library_loader;

final class LibraryLoader$$Lambda$0 implements Runnable {
    static final Runnable $instance;

    static {
        LibraryLoader$$Lambda$0.$instance = new LibraryLoader$$Lambda$0();
    }

    private LibraryLoader$$Lambda$0() {
        super();
    }

    public void run() {
        LibraryLoader.bridge$lambda$0$LibraryLoader();
    }
}

