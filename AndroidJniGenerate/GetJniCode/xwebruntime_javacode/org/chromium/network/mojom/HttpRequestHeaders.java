package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HttpRequestHeaders extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public HttpRequestHeaderKeyValuePair[] headers;

    static {
        HttpRequestHeaders.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        HttpRequestHeaders.DEFAULT_STRUCT_INFO = HttpRequestHeaders.VERSION_ARRAY[0];
    }

    public HttpRequestHeaders() {
        this(0);
    }

    private HttpRequestHeaders(int arg2) {
        super(16, arg2);
    }

    public static HttpRequestHeaders decode(Decoder arg8) {
        HttpRequestHeaders v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new HttpRequestHeaders(arg8.readAndValidateDataHeader(HttpRequestHeaders.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.headers = new HttpRequestHeaderKeyValuePair[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.headers[v5] = HttpRequestHeaderKeyValuePair.decode(v3.readPointer(v5 * 8 + v2, false));
            }
        }
        catch(Throwable v0) {
            goto label_31;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_31:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static HttpRequestHeaders deserialize(ByteBuffer arg2) {
        return HttpRequestHeaders.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HttpRequestHeaders deserialize(Message arg1) {
        return HttpRequestHeaders.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(HttpRequestHeaders.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.headers == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.headers.length, v2, -1);
            int v0;
            for(v0 = 0; v0 < this.headers.length; ++v0) {
                arg6.encode(this.headers[v0], v0 * 8 + v2, false);
            }
        }
    }
}

