package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class EosVideoFrameData extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        EosVideoFrameData.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        EosVideoFrameData.DEFAULT_STRUCT_INFO = EosVideoFrameData.VERSION_ARRAY[0];
    }

    public EosVideoFrameData() {
        this(0);
    }

    private EosVideoFrameData(int arg2) {
        super(8, arg2);
    }

    public static EosVideoFrameData decode(Decoder arg2) {
        EosVideoFrameData v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new EosVideoFrameData(arg2.readAndValidateDataHeader(EosVideoFrameData.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static EosVideoFrameData deserialize(ByteBuffer arg2) {
        return EosVideoFrameData.deserialize(new Message(arg2, new ArrayList()));
    }

    public static EosVideoFrameData deserialize(Message arg1) {
        return EosVideoFrameData.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(EosVideoFrameData.DEFAULT_STRUCT_INFO);
    }
}

