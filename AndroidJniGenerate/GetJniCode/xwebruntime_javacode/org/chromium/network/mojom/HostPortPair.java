package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HostPortPair extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        HostPortPair.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        HostPortPair.DEFAULT_STRUCT_INFO = HostPortPair.VERSION_ARRAY[0];
    }

    public HostPortPair() {
        this(0);
    }

    private HostPortPair(int arg2) {
        super(8, arg2);
    }

    public static HostPortPair decode(Decoder arg2) {
        HostPortPair v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new HostPortPair(arg2.readAndValidateDataHeader(HostPortPair.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static HostPortPair deserialize(ByteBuffer arg2) {
        return HostPortPair.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HostPortPair deserialize(Message arg1) {
        return HostPortPair.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(HostPortPair.DEFAULT_STRUCT_INFO);
    }
}

