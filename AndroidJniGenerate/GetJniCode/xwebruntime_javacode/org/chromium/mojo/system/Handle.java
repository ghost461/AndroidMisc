package org.chromium.mojo.system;

import java.io.Closeable;

public interface Handle extends Closeable {
    void close();

    Core getCore();

    boolean isValid();

    Handle pass();

    HandleSignalsState querySignalsState();

    int releaseNativeHandle();

    UntypedHandle toUntypedHandle();
}

