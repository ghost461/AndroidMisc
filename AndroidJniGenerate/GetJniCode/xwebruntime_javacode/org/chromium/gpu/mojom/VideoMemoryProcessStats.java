package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoMemoryProcessStats extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean hasDuplicates;
    public long videoMemoryBytes;

    static {
        VideoMemoryProcessStats.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        VideoMemoryProcessStats.DEFAULT_STRUCT_INFO = VideoMemoryProcessStats.VERSION_ARRAY[0];
    }

    public VideoMemoryProcessStats() {
        this(0);
    }

    private VideoMemoryProcessStats(int arg2) {
        super(24, arg2);
    }

    public static VideoMemoryProcessStats decode(Decoder arg4) {
        VideoMemoryProcessStats v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new VideoMemoryProcessStats(arg4.readAndValidateDataHeader(VideoMemoryProcessStats.VERSION_ARRAY).elementsOrVersion);
            v1.videoMemoryBytes = arg4.readLong(8);
            v1.hasDuplicates = arg4.readBoolean(16, 0);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static VideoMemoryProcessStats deserialize(ByteBuffer arg2) {
        return VideoMemoryProcessStats.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoMemoryProcessStats deserialize(Message arg1) {
        return VideoMemoryProcessStats.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(VideoMemoryProcessStats.DEFAULT_STRUCT_INFO);
        arg4.encode(this.videoMemoryBytes, 8);
        arg4.encode(this.hasDuplicates, 16, 0);
    }
}

