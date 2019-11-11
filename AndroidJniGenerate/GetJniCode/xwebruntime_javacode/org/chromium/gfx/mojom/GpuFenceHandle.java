package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.UntypedHandle;

public final class GpuFenceHandle extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public UntypedHandle nativeFd;
    public int type;

    static {
        GpuFenceHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        GpuFenceHandle.DEFAULT_STRUCT_INFO = GpuFenceHandle.VERSION_ARRAY[0];
    }

    public GpuFenceHandle() {
        this(0);
    }

    private GpuFenceHandle(int arg2) {
        super(16, arg2);
        this.nativeFd = InvalidHandle.INSTANCE;
    }

    public static GpuFenceHandle decode(Decoder arg3) {
        GpuFenceHandle v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new GpuFenceHandle(arg3.readAndValidateDataHeader(GpuFenceHandle.VERSION_ARRAY).elementsOrVersion);
            v1.type = arg3.readInt(8);
            GpuFenceHandleType.validate(v1.type);
            v1.nativeFd = arg3.readUntypedHandle(12, true);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static GpuFenceHandle deserialize(ByteBuffer arg2) {
        return GpuFenceHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuFenceHandle deserialize(Message arg1) {
        return GpuFenceHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(GpuFenceHandle.DEFAULT_STRUCT_INFO);
        arg4.encode(this.type, 8);
        arg4.encode(this.nativeFd, 12, true);
    }
}

