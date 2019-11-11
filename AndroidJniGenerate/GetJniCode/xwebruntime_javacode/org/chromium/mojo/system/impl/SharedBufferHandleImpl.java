package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.SharedBufferHandle$DuplicateOptions;
import org.chromium.mojo.system.SharedBufferHandle$MapFlags;
import org.chromium.mojo.system.SharedBufferHandle;

class SharedBufferHandleImpl extends HandleBase implements SharedBufferHandle {
    SharedBufferHandleImpl(CoreImpl arg1, int arg2) {
        super(arg1, arg2);
    }

    SharedBufferHandleImpl(HandleBase arg1) {
        super(arg1);
    }

    public SharedBufferHandle duplicate(DuplicateOptions arg2) {
        return this.mCore.duplicate(this, arg2);
    }

    public ByteBuffer map(long arg8, long arg10, MapFlags arg12) {
        return this.mCore.map(this, arg8, arg10, arg12);
    }

    public Handle pass() {
        return this.pass();
    }

    public SharedBufferHandle pass() {
        return new SharedBufferHandleImpl(((HandleBase)this));
    }

    public void unmap(ByteBuffer arg2) {
        this.mCore.unmap(arg2);
    }
}

