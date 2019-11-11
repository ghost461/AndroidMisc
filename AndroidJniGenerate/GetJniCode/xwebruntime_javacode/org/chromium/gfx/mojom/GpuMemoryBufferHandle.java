package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

public final class GpuMemoryBufferHandle extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public GpuMemoryBufferId id;
    public UntypedHandle machPort;
    public NativePixmapHandle nativePixmapHandle;
    public int offset;
    public SharedBufferHandle sharedMemoryHandle;
    public int stride;
    public int type;

    static {
        GpuMemoryBufferHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        GpuMemoryBufferHandle.DEFAULT_STRUCT_INFO = GpuMemoryBufferHandle.VERSION_ARRAY[0];
    }

    public GpuMemoryBufferHandle() {
        this(0);
    }

    private GpuMemoryBufferHandle(int arg2) {
        super(0x30, arg2);
        this.sharedMemoryHandle = InvalidHandle.INSTANCE;
        this.machPort = InvalidHandle.INSTANCE;
    }

    public static GpuMemoryBufferHandle decode(Decoder arg4) {
        GpuMemoryBufferHandle v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new GpuMemoryBufferHandle(arg4.readAndValidateDataHeader(GpuMemoryBufferHandle.VERSION_ARRAY).elementsOrVersion);
            v1.type = arg4.readInt(8);
            GpuMemoryBufferType.validate(v1.type);
            v1.sharedMemoryHandle = arg4.readSharedBufferHandle(12, true);
            v1.id = GpuMemoryBufferId.decode(arg4.readPointer(16, false));
            v1.offset = arg4.readInt(24);
            v1.stride = arg4.readInt(28);
            v1.nativePixmapHandle = NativePixmapHandle.decode(arg4.readPointer(0x20, true));
            v1.machPort = arg4.readUntypedHandle(40, true);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static GpuMemoryBufferHandle deserialize(ByteBuffer arg2) {
        return GpuMemoryBufferHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuMemoryBufferHandle deserialize(Message arg1) {
        return GpuMemoryBufferHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(GpuMemoryBufferHandle.DEFAULT_STRUCT_INFO);
        arg5.encode(this.type, 8);
        arg5.encode(this.sharedMemoryHandle, 12, true);
        arg5.encode(this.id, 16, false);
        arg5.encode(this.offset, 24);
        arg5.encode(this.stride, 28);
        arg5.encode(this.nativePixmapHandle, 0x20, true);
        arg5.encode(this.machPort, 40, true);
    }
}

