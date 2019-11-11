package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class WritableSharedMemoryRegion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public PlatformSharedMemoryRegion region;

    static {
        WritableSharedMemoryRegion.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        WritableSharedMemoryRegion.DEFAULT_STRUCT_INFO = WritableSharedMemoryRegion.VERSION_ARRAY[0];
    }

    public WritableSharedMemoryRegion() {
        this(0);
    }

    private WritableSharedMemoryRegion(int arg2) {
        super(16, arg2);
    }

    public static WritableSharedMemoryRegion decode(Decoder arg3) {
        WritableSharedMemoryRegion v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new WritableSharedMemoryRegion(arg3.readAndValidateDataHeader(WritableSharedMemoryRegion.VERSION_ARRAY).elementsOrVersion);
            v1.region = PlatformSharedMemoryRegion.decode(arg3.readPointer(8, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static WritableSharedMemoryRegion deserialize(ByteBuffer arg2) {
        return WritableSharedMemoryRegion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static WritableSharedMemoryRegion deserialize(Message arg1) {
        return WritableSharedMemoryRegion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(WritableSharedMemoryRegion.DEFAULT_STRUCT_INFO).encode(this.region, 8, false);
    }
}

