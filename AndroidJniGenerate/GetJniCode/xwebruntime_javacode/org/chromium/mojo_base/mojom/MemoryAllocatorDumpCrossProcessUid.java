package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MemoryAllocatorDumpCrossProcessUid extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public long value;

    static {
        MemoryAllocatorDumpCrossProcessUid.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        MemoryAllocatorDumpCrossProcessUid.DEFAULT_STRUCT_INFO = MemoryAllocatorDumpCrossProcessUid.VERSION_ARRAY[0];
    }

    public MemoryAllocatorDumpCrossProcessUid() {
        this(0);
    }

    private MemoryAllocatorDumpCrossProcessUid(int arg2) {
        super(16, arg2);
    }

    public static MemoryAllocatorDumpCrossProcessUid decode(Decoder arg4) {
        MemoryAllocatorDumpCrossProcessUid v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new MemoryAllocatorDumpCrossProcessUid(arg4.readAndValidateDataHeader(MemoryAllocatorDumpCrossProcessUid.VERSION_ARRAY).elementsOrVersion);
            v1.value = arg4.readLong(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static MemoryAllocatorDumpCrossProcessUid deserialize(ByteBuffer arg2) {
        return MemoryAllocatorDumpCrossProcessUid.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MemoryAllocatorDumpCrossProcessUid deserialize(Message arg1) {
        return MemoryAllocatorDumpCrossProcessUid.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(MemoryAllocatorDumpCrossProcessUid.DEFAULT_STRUCT_INFO).encode(this.value, 8);
    }
}

