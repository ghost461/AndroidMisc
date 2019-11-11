package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Capabilities extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        Capabilities.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        Capabilities.DEFAULT_STRUCT_INFO = Capabilities.VERSION_ARRAY[0];
    }

    public Capabilities() {
        this(0);
    }

    private Capabilities(int arg2) {
        super(8, arg2);
    }

    public static Capabilities decode(Decoder arg2) {
        Capabilities v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Capabilities(arg2.readAndValidateDataHeader(Capabilities.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static Capabilities deserialize(ByteBuffer arg2) {
        return Capabilities.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Capabilities deserialize(Message arg1) {
        return Capabilities.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(Capabilities.DEFAULT_STRUCT_INFO);
    }
}

