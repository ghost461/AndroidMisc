package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UrlResponseHead extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        UrlResponseHead.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        UrlResponseHead.DEFAULT_STRUCT_INFO = UrlResponseHead.VERSION_ARRAY[0];
    }

    public UrlResponseHead() {
        this(0);
    }

    private UrlResponseHead(int arg2) {
        super(8, arg2);
    }

    public static UrlResponseHead decode(Decoder arg2) {
        UrlResponseHead v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new UrlResponseHead(arg2.readAndValidateDataHeader(UrlResponseHead.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static UrlResponseHead deserialize(ByteBuffer arg2) {
        return UrlResponseHead.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UrlResponseHead deserialize(Message arg1) {
        return UrlResponseHead.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(UrlResponseHead.DEFAULT_STRUCT_INFO);
    }
}

