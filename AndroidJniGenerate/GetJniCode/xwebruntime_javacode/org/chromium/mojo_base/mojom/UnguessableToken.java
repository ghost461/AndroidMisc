package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UnguessableToken extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public long high;
    public long low;

    static {
        UnguessableToken.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        UnguessableToken.DEFAULT_STRUCT_INFO = UnguessableToken.VERSION_ARRAY[0];
    }

    public UnguessableToken() {
        this(0);
    }

    private UnguessableToken(int arg2) {
        super(24, arg2);
    }

    public static UnguessableToken decode(Decoder arg4) {
        UnguessableToken v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new UnguessableToken(arg4.readAndValidateDataHeader(UnguessableToken.VERSION_ARRAY).elementsOrVersion);
            v1.high = arg4.readLong(8);
            v1.low = arg4.readLong(16);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static UnguessableToken deserialize(ByteBuffer arg2) {
        return UnguessableToken.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UnguessableToken deserialize(Message arg1) {
        return UnguessableToken.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(UnguessableToken.DEFAULT_STRUCT_INFO);
        arg4.encode(this.high, 8);
        arg4.encode(this.low, 16);
    }
}

