package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UrlRequest extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        UrlRequest.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        UrlRequest.DEFAULT_STRUCT_INFO = UrlRequest.VERSION_ARRAY[0];
    }

    public UrlRequest() {
        this(0);
    }

    private UrlRequest(int arg2) {
        super(8, arg2);
    }

    public static UrlRequest decode(Decoder arg2) {
        UrlRequest v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new UrlRequest(arg2.readAndValidateDataHeader(UrlRequest.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static UrlRequest deserialize(ByteBuffer arg2) {
        return UrlRequest.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UrlRequest deserialize(Message arg1) {
        return UrlRequest.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(UrlRequest.DEFAULT_STRUCT_INFO);
    }
}

