package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class BigString16 extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public BigBuffer data;

    static {
        BigString16.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        BigString16.DEFAULT_STRUCT_INFO = BigString16.VERSION_ARRAY[0];
    }

    public BigString16() {
        this(0);
    }

    private BigString16(int arg2) {
        super(24, arg2);
    }

    public static BigString16 decode(Decoder arg2) {
        BigString16 v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new BigString16(arg2.readAndValidateDataHeader(BigString16.VERSION_ARRAY).elementsOrVersion);
            v1.data = BigBuffer.decode(arg2, 8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static BigString16 deserialize(ByteBuffer arg2) {
        return BigString16.deserialize(new Message(arg2, new ArrayList()));
    }

    public static BigString16 deserialize(Message arg1) {
        return BigString16.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(BigString16.DEFAULT_STRUCT_INFO).encode(this.data, 8, false);
    }
}

