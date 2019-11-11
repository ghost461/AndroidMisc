package org.chromium.mojo.bindings;

import java.io.Closeable;
import org.chromium.mojo.system.Handle;

public interface HandleOwner extends Closeable {
    void close();

    Handle passHandle();
}

