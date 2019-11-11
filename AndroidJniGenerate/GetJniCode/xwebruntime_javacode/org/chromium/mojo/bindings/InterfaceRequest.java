package org.chromium.mojo.bindings;

import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;

public class InterfaceRequest implements HandleOwner {
    private final MessagePipeHandle mHandle;

    InterfaceRequest(MessagePipeHandle arg1) {
        super();
        this.mHandle = arg1;
    }

    public static InterfaceRequest asInterfaceRequestUnsafe(MessagePipeHandle arg1) {
        return new InterfaceRequest(arg1);
    }

    public void close() {
        this.mHandle.close();
    }

    public MessagePipeHandle passHandle() {
        return this.mHandle.pass();
    }

    public Handle passHandle() {
        return this.passHandle();
    }
}

