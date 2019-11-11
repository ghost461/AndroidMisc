package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;

public final class SharedBufferVideoFrameData extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public SharedBufferHandle frameData;
    public long frameDataSize;
    public long uOffset;
    public int uStride;
    public long vOffset;
    public int vStride;
    public long yOffset;
    public int yStride;

    static {
        SharedBufferVideoFrameData.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        SharedBufferVideoFrameData.DEFAULT_STRUCT_INFO = SharedBufferVideoFrameData.VERSION_ARRAY[0];
    }

    public SharedBufferVideoFrameData() {
        this(0);
    }

    private SharedBufferVideoFrameData(int arg2) {
        super(56, arg2);
        this.frameData = InvalidHandle.INSTANCE;
    }

    public static SharedBufferVideoFrameData decode(Decoder arg4) {
        SharedBufferVideoFrameData v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new SharedBufferVideoFrameData(arg4.readAndValidateDataHeader(SharedBufferVideoFrameData.VERSION_ARRAY).elementsOrVersion);
            v1.frameData = arg4.readSharedBufferHandle(8, false);
            v1.yStride = arg4.readInt(12);
            v1.frameDataSize = arg4.readLong(16);
            v1.uStride = arg4.readInt(24);
            v1.vStride = arg4.readInt(28);
            v1.yOffset = arg4.readLong(0x20);
            v1.uOffset = arg4.readLong(40);
            v1.vOffset = arg4.readLong(0x30);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static SharedBufferVideoFrameData deserialize(ByteBuffer arg2) {
        return SharedBufferVideoFrameData.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SharedBufferVideoFrameData deserialize(Message arg1) {
        return SharedBufferVideoFrameData.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SharedBufferVideoFrameData.DEFAULT_STRUCT_INFO);
        arg4.encode(this.frameData, 8, false);
        arg4.encode(this.yStride, 12);
        arg4.encode(this.frameDataSize, 16);
        arg4.encode(this.uStride, 24);
        arg4.encode(this.vStride, 28);
        arg4.encode(this.yOffset, 0x20);
        arg4.encode(this.uOffset, 40);
        arg4.encode(this.vOffset, 0x30);
    }
}

