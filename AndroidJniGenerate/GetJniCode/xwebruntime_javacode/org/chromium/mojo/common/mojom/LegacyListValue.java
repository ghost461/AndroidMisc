package org.chromium.mojo.common.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class LegacyListValue extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        LegacyListValue.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        LegacyListValue.DEFAULT_STRUCT_INFO = LegacyListValue.VERSION_ARRAY[0];
    }

    public LegacyListValue() {
        this(0);
    }

    private LegacyListValue(int arg2) {
        super(8, arg2);
    }

    public static LegacyListValue decode(Decoder arg2) {
        LegacyListValue v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new LegacyListValue(arg2.readAndValidateDataHeader(LegacyListValue.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static LegacyListValue deserialize(ByteBuffer arg2) {
        return LegacyListValue.deserialize(new Message(arg2, new ArrayList()));
    }

    public static LegacyListValue deserialize(Message arg1) {
        return LegacyListValue.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(LegacyListValue.DEFAULT_STRUCT_INFO);
    }
}

