package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CdmConfig extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        CdmConfig.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        CdmConfig.DEFAULT_STRUCT_INFO = CdmConfig.VERSION_ARRAY[0];
    }

    public CdmConfig() {
        this(0);
    }

    private CdmConfig(int arg2) {
        super(8, arg2);
    }

    public static CdmConfig decode(Decoder arg2) {
        CdmConfig v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new CdmConfig(arg2.readAndValidateDataHeader(CdmConfig.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static CdmConfig deserialize(ByteBuffer arg2) {
        return CdmConfig.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CdmConfig deserialize(Message arg1) {
        return CdmConfig.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(CdmConfig.DEFAULT_STRUCT_INFO);
    }
}

