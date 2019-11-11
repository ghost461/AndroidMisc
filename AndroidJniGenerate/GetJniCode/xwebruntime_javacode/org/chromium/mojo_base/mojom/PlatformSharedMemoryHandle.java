package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.UntypedHandle;

public final class PlatformSharedMemoryHandle extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public UntypedHandle handleValue;

    static {
        PlatformSharedMemoryHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        PlatformSharedMemoryHandle.DEFAULT_STRUCT_INFO = PlatformSharedMemoryHandle.VERSION_ARRAY[0];
    }

    public PlatformSharedMemoryHandle() {
        this(0);
    }

    private PlatformSharedMemoryHandle(int arg2) {
        super(16, arg2);
        this.handleValue = InvalidHandle.INSTANCE;
    }

    public static PlatformSharedMemoryHandle decode(Decoder arg3) {
        PlatformSharedMemoryHandle v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PlatformSharedMemoryHandle(arg3.readAndValidateDataHeader(PlatformSharedMemoryHandle.VERSION_ARRAY).elementsOrVersion);
            v1.handleValue = arg3.readUntypedHandle(8, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PlatformSharedMemoryHandle deserialize(ByteBuffer arg2) {
        return PlatformSharedMemoryHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PlatformSharedMemoryHandle deserialize(Message arg1) {
        return PlatformSharedMemoryHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(PlatformSharedMemoryHandle.DEFAULT_STRUCT_INFO).encode(this.handleValue, 8, false);
    }
}

