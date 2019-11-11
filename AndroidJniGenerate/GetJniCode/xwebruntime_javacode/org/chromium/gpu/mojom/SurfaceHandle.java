package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SurfaceHandle extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public long surfaceHandle;

    static {
        SurfaceHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SurfaceHandle.DEFAULT_STRUCT_INFO = SurfaceHandle.VERSION_ARRAY[0];
    }

    public SurfaceHandle() {
        this(0);
    }

    private SurfaceHandle(int arg2) {
        super(16, arg2);
    }

    public static SurfaceHandle decode(Decoder arg4) {
        SurfaceHandle v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new SurfaceHandle(arg4.readAndValidateDataHeader(SurfaceHandle.VERSION_ARRAY).elementsOrVersion);
            v1.surfaceHandle = arg4.readLong(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static SurfaceHandle deserialize(ByteBuffer arg2) {
        return SurfaceHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SurfaceHandle deserialize(Message arg1) {
        return SurfaceHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(SurfaceHandle.DEFAULT_STRUCT_INFO).encode(this.surfaceHandle, 8);
    }
}

