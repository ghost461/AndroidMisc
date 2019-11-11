package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class FlushForTesting extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        FlushForTesting.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        FlushForTesting.DEFAULT_STRUCT_INFO = FlushForTesting.VERSION_ARRAY[0];
    }

    public FlushForTesting() {
        this(0);
    }

    private FlushForTesting(int arg2) {
        super(8, arg2);
    }

    public static FlushForTesting decode(Decoder arg2) {
        FlushForTesting v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new FlushForTesting(arg2.readAndValidateDataHeader(FlushForTesting.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static FlushForTesting deserialize(ByteBuffer arg2) {
        return FlushForTesting.deserialize(new Message(arg2, new ArrayList()));
    }

    public static FlushForTesting deserialize(Message arg1) {
        return FlushForTesting.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(FlushForTesting.DEFAULT_STRUCT_INFO);
    }
}

