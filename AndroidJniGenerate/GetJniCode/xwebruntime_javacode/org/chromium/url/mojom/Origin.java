package org.chromium.url.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Origin extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String host;
    public short port;
    public String scheme;
    public boolean unique;

    static {
        Origin.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        Origin.DEFAULT_STRUCT_INFO = Origin.VERSION_ARRAY[0];
    }

    public Origin() {
        this(0);
    }

    private Origin(int arg2) {
        super(0x20, arg2);
    }

    public static Origin decode(Decoder arg3) {
        Origin v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new Origin(arg3.readAndValidateDataHeader(Origin.VERSION_ARRAY).elementsOrVersion);
            v1.scheme = arg3.readString(8, false);
            v1.host = arg3.readString(16, false);
            v1.port = arg3.readShort(24);
            v1.unique = arg3.readBoolean(26, 0);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static Origin deserialize(ByteBuffer arg2) {
        return Origin.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Origin deserialize(Message arg1) {
        return Origin.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(Origin.DEFAULT_STRUCT_INFO);
        arg4.encode(this.scheme, 8, false);
        arg4.encode(this.host, 16, false);
        arg4.encode(this.port, 24);
        arg4.encode(this.unique, 26, 0);
    }
}

