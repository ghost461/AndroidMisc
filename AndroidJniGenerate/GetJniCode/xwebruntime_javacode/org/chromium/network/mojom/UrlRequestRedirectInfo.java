package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UrlRequestRedirectInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        UrlRequestRedirectInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        UrlRequestRedirectInfo.DEFAULT_STRUCT_INFO = UrlRequestRedirectInfo.VERSION_ARRAY[0];
    }

    public UrlRequestRedirectInfo() {
        this(0);
    }

    private UrlRequestRedirectInfo(int arg2) {
        super(8, arg2);
    }

    public static UrlRequestRedirectInfo decode(Decoder arg2) {
        UrlRequestRedirectInfo v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new UrlRequestRedirectInfo(arg2.readAndValidateDataHeader(UrlRequestRedirectInfo.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static UrlRequestRedirectInfo deserialize(ByteBuffer arg2) {
        return UrlRequestRedirectInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UrlRequestRedirectInfo deserialize(Message arg1) {
        return UrlRequestRedirectInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(UrlRequestRedirectInfo.DEFAULT_STRUCT_INFO);
    }
}

