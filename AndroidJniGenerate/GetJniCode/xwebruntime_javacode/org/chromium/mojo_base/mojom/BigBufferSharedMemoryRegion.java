package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;

public final class BigBufferSharedMemoryRegion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public SharedBufferHandle bufferHandle;
    public int size;

    static {
        BigBufferSharedMemoryRegion.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        BigBufferSharedMemoryRegion.DEFAULT_STRUCT_INFO = BigBufferSharedMemoryRegion.VERSION_ARRAY[0];
    }

    public BigBufferSharedMemoryRegion() {
        this(0);
    }

    private BigBufferSharedMemoryRegion(int arg2) {
        super(16, arg2);
        this.bufferHandle = InvalidHandle.INSTANCE;
    }

    public static BigBufferSharedMemoryRegion decode(Decoder arg3) {
        BigBufferSharedMemoryRegion v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new BigBufferSharedMemoryRegion(arg3.readAndValidateDataHeader(BigBufferSharedMemoryRegion.VERSION_ARRAY).elementsOrVersion);
            v1.bufferHandle = arg3.readSharedBufferHandle(8, false);
            v1.size = arg3.readInt(12);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static BigBufferSharedMemoryRegion deserialize(ByteBuffer arg2) {
        return BigBufferSharedMemoryRegion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static BigBufferSharedMemoryRegion deserialize(Message arg1) {
        return BigBufferSharedMemoryRegion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(BigBufferSharedMemoryRegion.DEFAULT_STRUCT_INFO);
        arg4.encode(this.bufferHandle, 8, false);
        arg4.encode(this.size, 12);
    }
}

