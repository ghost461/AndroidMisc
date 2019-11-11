package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class QueryVersionResult extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int version;

    static {
        QueryVersionResult.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        QueryVersionResult.DEFAULT_STRUCT_INFO = QueryVersionResult.VERSION_ARRAY[0];
    }

    public QueryVersionResult() {
        this(0);
    }

    private QueryVersionResult(int arg2) {
        super(16, arg2);
    }

    public static QueryVersionResult decode(Decoder arg2) {
        QueryVersionResult v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new QueryVersionResult(arg2.readAndValidateDataHeader(QueryVersionResult.VERSION_ARRAY).elementsOrVersion);
            v1.version = arg2.readInt(8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static QueryVersionResult deserialize(ByteBuffer arg2) {
        return QueryVersionResult.deserialize(new Message(arg2, new ArrayList()));
    }

    public static QueryVersionResult deserialize(Message arg1) {
        return QueryVersionResult.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(QueryVersionResult.DEFAULT_STRUCT_INFO).encode(this.version, 8);
    }
}

