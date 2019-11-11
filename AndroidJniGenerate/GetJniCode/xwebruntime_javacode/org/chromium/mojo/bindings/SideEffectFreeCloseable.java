package org.chromium.mojo.bindings;

import java.io.Closeable;

public class SideEffectFreeCloseable implements Closeable {
    public SideEffectFreeCloseable() {
        super();
    }

    public void close() {
    }
}

