package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RefCountedMemory extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public BigBuffer data;

    static {
        RefCountedMemory.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        RefCountedMemory.DEFAULT_STRUCT_INFO = RefCountedMemory.VERSION_ARRAY[0];
    }

    public RefCountedMemory() {
        this(0);
    }

    private RefCountedMemory(int arg2) {
        super(24, arg2);
    }

    public static RefCountedMemory decode(Decoder arg2) {
        RefCountedMemory v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RefCountedMemory(arg2.readAndValidateDataHeader(RefCountedMemory.VERSION_ARRAY).elementsOrVersion);
            v1.data = BigBuffer.decode(arg2, 8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RefCountedMemory deserialize(ByteBuffer arg2) {
        return RefCountedMemory.deserialize(new Message(arg2, new ArrayList()));
    }

    public static RefCountedMemory deserialize(Message arg1) {
        return RefCountedMemory.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(RefCountedMemory.DEFAULT_STRUCT_INFO).encode(this.data, 8, false);
    }
}

