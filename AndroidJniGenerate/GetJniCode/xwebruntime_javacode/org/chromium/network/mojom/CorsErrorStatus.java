package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CorsErrorStatus extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        CorsErrorStatus.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        CorsErrorStatus.DEFAULT_STRUCT_INFO = CorsErrorStatus.VERSION_ARRAY[0];
    }

    public CorsErrorStatus() {
        this(0);
    }

    private CorsErrorStatus(int arg2) {
        super(8, arg2);
    }

    public static CorsErrorStatus decode(Decoder arg2) {
        CorsErrorStatus v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new CorsErrorStatus(arg2.readAndValidateDataHeader(CorsErrorStatus.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static CorsErrorStatus deserialize(ByteBuffer arg2) {
        return CorsErrorStatus.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CorsErrorStatus deserialize(Message arg1) {
        return CorsErrorStatus.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(CorsErrorStatus.DEFAULT_STRUCT_INFO);
    }
}

