package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SslInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        SslInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        SslInfo.DEFAULT_STRUCT_INFO = SslInfo.VERSION_ARRAY[0];
    }

    public SslInfo() {
        this(0);
    }

    private SslInfo(int arg2) {
        super(8, arg2);
    }

    public static SslInfo decode(Decoder arg2) {
        SslInfo v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new SslInfo(arg2.readAndValidateDataHeader(SslInfo.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static SslInfo deserialize(ByteBuffer arg2) {
        return SslInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SslInfo deserialize(Message arg1) {
        return SslInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(SslInfo.DEFAULT_STRUCT_INFO);
    }
}

