package org.chromium.url.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Url extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public String url;

    static {
        Url.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Url.DEFAULT_STRUCT_INFO = Url.VERSION_ARRAY[0];
    }

    public Url() {
        this(0);
    }

    private Url(int arg2) {
        super(16, arg2);
    }

    public static Url decode(Decoder arg3) {
        Url v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new Url(arg3.readAndValidateDataHeader(Url.VERSION_ARRAY).elementsOrVersion);
            v1.url = arg3.readString(8, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static Url deserialize(ByteBuffer arg2) {
        return Url.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Url deserialize(Message arg1) {
        return Url.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(Url.DEFAULT_STRUCT_INFO).encode(this.url, 8, false);
    }
}

