package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HdrMetadata extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public MasteringMetadata masteringMetadata;
    public int maxContentLightLevel;
    public int maxFrameAverageLightLevel;

    static {
        HdrMetadata.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        HdrMetadata.DEFAULT_STRUCT_INFO = HdrMetadata.VERSION_ARRAY[0];
    }

    public HdrMetadata() {
        this(0);
    }

    private HdrMetadata(int arg2) {
        super(24, arg2);
    }

    public static HdrMetadata decode(Decoder arg3) {
        HdrMetadata v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new HdrMetadata(arg3.readAndValidateDataHeader(HdrMetadata.VERSION_ARRAY).elementsOrVersion);
            v1.masteringMetadata = MasteringMetadata.decode(arg3.readPointer(8, false));
            v1.maxContentLightLevel = arg3.readInt(16);
            v1.maxFrameAverageLightLevel = arg3.readInt(20);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static HdrMetadata deserialize(ByteBuffer arg2) {
        return HdrMetadata.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HdrMetadata deserialize(Message arg1) {
        return HdrMetadata.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(HdrMetadata.DEFAULT_STRUCT_INFO);
        arg4.encode(this.masteringMetadata, 8, false);
        arg4.encode(this.maxContentLightLevel, 16);
        arg4.encode(this.maxFrameAverageLightLevel, 20);
    }
}

