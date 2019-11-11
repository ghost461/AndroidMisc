package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class QueryVersion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        QueryVersion.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        QueryVersion.DEFAULT_STRUCT_INFO = QueryVersion.VERSION_ARRAY[0];
    }

    public QueryVersion() {
        this(0);
    }

    private QueryVersion(int arg2) {
        super(8, arg2);
    }

    public static QueryVersion decode(Decoder arg2) {
        QueryVersion v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new QueryVersion(arg2.readAndValidateDataHeader(QueryVersion.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static QueryVersion deserialize(ByteBuffer arg2) {
        return QueryVersion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static QueryVersion deserialize(Message arg1) {
        return QueryVersion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(QueryVersion.DEFAULT_STRUCT_INFO);
    }
}

