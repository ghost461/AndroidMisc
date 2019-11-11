package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class WebSocketHandshakeResponse extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public HttpHeader[] headers;
    public String headersText;
    public int statusCode;
    public String statusText;
    public Url url;

    static {
        WebSocketHandshakeResponse.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        WebSocketHandshakeResponse.DEFAULT_STRUCT_INFO = WebSocketHandshakeResponse.VERSION_ARRAY[0];
    }

    public WebSocketHandshakeResponse() {
        this(0);
    }

    private WebSocketHandshakeResponse(int arg2) {
        super(0x30, arg2);
    }

    public static WebSocketHandshakeResponse decode(Decoder arg8) {
        WebSocketHandshakeResponse v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new WebSocketHandshakeResponse(arg8.readAndValidateDataHeader(WebSocketHandshakeResponse.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.url = Url.decode(arg8.readPointer(v0_1, false));
            v1.statusCode = arg8.readInt(16);
            v1.statusText = arg8.readString(24, false);
            Decoder v3 = arg8.readPointer(0x20, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.headers = new HttpHeader[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.headers[v5] = HttpHeader.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }

            v1.headersText = arg8.readString(40, false);
        }
        catch(Throwable v0) {
            goto label_44;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_44:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static WebSocketHandshakeResponse deserialize(ByteBuffer arg2) {
        return WebSocketHandshakeResponse.deserialize(new Message(arg2, new ArrayList()));
    }

    public static WebSocketHandshakeResponse deserialize(Message arg1) {
        return WebSocketHandshakeResponse.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(WebSocketHandshakeResponse.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg7.encode(this.url, v1, false);
        arg7.encode(this.statusCode, 16);
        arg7.encode(this.statusText, 24, false);
        int v3 = 0x20;
        if(this.headers == null) {
            arg7.encodeNullPointer(v3, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.headers.length, v3, -1);
            for(v3 = 0; v3 < this.headers.length; ++v3) {
                v0.encode(this.headers[v3], v3 * 8 + v1, false);
            }
        }

        arg7.encode(this.headersText, 40, false);
    }
}

