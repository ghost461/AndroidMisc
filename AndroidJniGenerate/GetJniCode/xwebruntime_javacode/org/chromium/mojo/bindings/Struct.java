package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.Core;

public abstract class Struct {
    private final int mEncodedBaseSize;
    private final int mVersion;

    protected Struct(int arg1, int arg2) {
        super();
        this.mEncodedBaseSize = arg1;
        this.mVersion = arg2;
    }

    protected abstract void encode(Encoder arg1);

    public int getVersion() {
        return this.mVersion;
    }

    public ByteBuffer serialize() {
        Message v0 = this.serialize(null);
        if(!v0.getHandles().isEmpty()) {
            throw new UnsupportedOperationException("Handles are discarded.");
        }

        return v0.getData();
    }

    public Message serialize(Core arg3) {
        Encoder v0 = new Encoder(arg3, this.mEncodedBaseSize);
        this.encode(v0);
        return v0.getMessage();
    }

    public ServiceMessage serializeWithHeader(Core arg4, MessageHeader arg5) {
        Encoder v0 = new Encoder(arg4, this.mEncodedBaseSize + arg5.getSize());
        arg5.encode(v0);
        this.encode(v0);
        return new ServiceMessage(v0.getMessage(), arg5);
    }
}

