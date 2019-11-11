package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class GpuMemoryBufferId extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int id;

    static {
        GpuMemoryBufferId.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        GpuMemoryBufferId.DEFAULT_STRUCT_INFO = GpuMemoryBufferId.VERSION_ARRAY[0];
    }

    public GpuMemoryBufferId() {
        this(0);
    }

    private GpuMemoryBufferId(int arg2) {
        super(16, arg2);
    }

    public static GpuMemoryBufferId decode(Decoder arg2) {
        GpuMemoryBufferId v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new GpuMemoryBufferId(arg2.readAndValidateDataHeader(GpuMemoryBufferId.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg2.readInt(8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static GpuMemoryBufferId deserialize(ByteBuffer arg2) {
        return GpuMemoryBufferId.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuMemoryBufferId deserialize(Message arg1) {
        return GpuMemoryBufferId.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(GpuMemoryBufferId.DEFAULT_STRUCT_INFO).encode(this.id, 8);
    }
}

