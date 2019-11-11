package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UnsafeSharedMemoryRegion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public PlatformSharedMemoryRegion region;

    static {
        UnsafeSharedMemoryRegion.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        UnsafeSharedMemoryRegion.DEFAULT_STRUCT_INFO = UnsafeSharedMemoryRegion.VERSION_ARRAY[0];
    }

    public UnsafeSharedMemoryRegion() {
        this(0);
    }

    private UnsafeSharedMemoryRegion(int arg2) {
        super(16, arg2);
    }

    public static UnsafeSharedMemoryRegion decode(Decoder arg3) {
        UnsafeSharedMemoryRegion v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new UnsafeSharedMemoryRegion(arg3.readAndValidateDataHeader(UnsafeSharedMemoryRegion.VERSION_ARRAY).elementsOrVersion);
            v1.region = PlatformSharedMemoryRegion.decode(arg3.readPointer(8, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static UnsafeSharedMemoryRegion deserialize(ByteBuffer arg2) {
        return UnsafeSharedMemoryRegion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UnsafeSharedMemoryRegion deserialize(Message arg1) {
        return UnsafeSharedMemoryRegion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(UnsafeSharedMemoryRegion.DEFAULT_STRUCT_INFO).encode(this.region, 8, false);
    }
}

