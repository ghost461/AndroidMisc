package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PipelineStatistics extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public long audioBytesDecoded;
    public long audioMemoryUsage;
    public long videoBytesDecoded;
    public int videoFramesDecoded;
    public int videoFramesDropped;
    public long videoMemoryUsage;

    static {
        PipelineStatistics.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        PipelineStatistics.DEFAULT_STRUCT_INFO = PipelineStatistics.VERSION_ARRAY[0];
    }

    public PipelineStatistics() {
        this(0);
    }

    private PipelineStatistics(int arg2) {
        super(0x30, arg2);
    }

    public static PipelineStatistics decode(Decoder arg4) {
        PipelineStatistics v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new PipelineStatistics(arg4.readAndValidateDataHeader(PipelineStatistics.VERSION_ARRAY).elementsOrVersion);
            v1.audioBytesDecoded = arg4.readLong(8);
            v1.videoBytesDecoded = arg4.readLong(16);
            v1.videoFramesDecoded = arg4.readInt(24);
            v1.videoFramesDropped = arg4.readInt(28);
            v1.audioMemoryUsage = arg4.readLong(0x20);
            v1.videoMemoryUsage = arg4.readLong(40);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static PipelineStatistics deserialize(ByteBuffer arg2) {
        return PipelineStatistics.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PipelineStatistics deserialize(Message arg1) {
        return PipelineStatistics.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PipelineStatistics.DEFAULT_STRUCT_INFO);
        arg4.encode(this.audioBytesDecoded, 8);
        arg4.encode(this.videoBytesDecoded, 16);
        arg4.encode(this.videoFramesDecoded, 24);
        arg4.encode(this.videoFramesDropped, 28);
        arg4.encode(this.audioMemoryUsage, 0x20);
        arg4.encode(this.videoMemoryUsage, 40);
    }
}

