package org.chromium.mojo.system;

import java.io.Closeable;

public interface RunLoop extends Closeable {
    void close();

    void postDelayedTask(Runnable arg1, long arg2);

    void quit();

    void run();

    void runUntilIdle();
}

