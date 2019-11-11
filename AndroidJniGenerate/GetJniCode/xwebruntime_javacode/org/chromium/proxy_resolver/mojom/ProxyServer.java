package org.chromium.proxy_resolver.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ProxyServer extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public String host;
    public short port;
    public int scheme;

    static {
        ProxyServer.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        ProxyServer.DEFAULT_STRUCT_INFO = ProxyServer.VERSION_ARRAY[0];
    }

    public ProxyServer() {
        this(0);
    }

    private ProxyServer(int arg2) {
        super(24, arg2);
    }

    public static ProxyServer decode(Decoder arg3) {
        ProxyServer v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ProxyServer(arg3.readAndValidateDataHeader(ProxyServer.VERSION_ARRAY).elementsOrVersion);
            v1.scheme = arg3.readInt(8);
            ProxyScheme.validate(v1.scheme);
            v1.port = arg3.readShort(12);
            v1.host = arg3.readString(16, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ProxyServer deserialize(ByteBuffer arg2) {
        return ProxyServer.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProxyServer deserialize(Message arg1) {
        return ProxyServer.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ProxyServer.DEFAULT_STRUCT_INFO);
        arg4.encode(this.scheme, 8);
        arg4.encode(this.port, 12);
        arg4.encode(this.host, 16, false);
    }
}

