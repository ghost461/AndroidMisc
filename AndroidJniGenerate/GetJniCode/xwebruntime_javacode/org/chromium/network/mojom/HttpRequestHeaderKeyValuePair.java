package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HttpRequestHeaderKeyValuePair extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public String key;
    public String value;

    static {
        HttpRequestHeaderKeyValuePair.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        HttpRequestHeaderKeyValuePair.DEFAULT_STRUCT_INFO = HttpRequestHeaderKeyValuePair.VERSION_ARRAY[0];
    }

    public HttpRequestHeaderKeyValuePair() {
        this(0);
    }

    private HttpRequestHeaderKeyValuePair(int arg2) {
        super(24, arg2);
    }

    public static HttpRequestHeaderKeyValuePair decode(Decoder arg3) {
        HttpRequestHeaderKeyValuePair v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new HttpRequestHeaderKeyValuePair(arg3.readAndValidateDataHeader(HttpRequestHeaderKeyValuePair.VERSION_ARRAY).elementsOrVersion);
            v1.key = arg3.readString(8, false);
            v1.value = arg3.readString(16, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static HttpRequestHeaderKeyValuePair deserialize(ByteBuffer arg2) {
        return HttpRequestHeaderKeyValuePair.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HttpRequestHeaderKeyValuePair deserialize(Message arg1) {
        return HttpRequestHeaderKeyValuePair.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(HttpRequestHeaderKeyValuePair.DEFAULT_STRUCT_INFO);
        arg4.encode(this.key, 8, false);
        arg4.encode(this.value, 16, false);
    }
}

