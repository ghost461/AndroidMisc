package org.chromium.mojo.common.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NullValue extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        NullValue.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        NullValue.DEFAULT_STRUCT_INFO = NullValue.VERSION_ARRAY[0];
    }

    public NullValue() {
        this(0);
    }

    private NullValue(int arg2) {
        super(8, arg2);
    }

    public static NullValue decode(Decoder arg2) {
        NullValue v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new NullValue(arg2.readAndValidateDataHeader(NullValue.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static NullValue deserialize(ByteBuffer arg2) {
        return NullValue.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NullValue deserialize(Message arg1) {
        return NullValue.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(NullValue.DEFAULT_STRUCT_INFO);
    }
}

