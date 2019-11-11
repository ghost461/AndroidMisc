package org.chromium.proxy_resolver.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ProxyInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public ProxyServer[] proxyServers;

    static {
        ProxyInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ProxyInfo.DEFAULT_STRUCT_INFO = ProxyInfo.VERSION_ARRAY[0];
    }

    public ProxyInfo() {
        this(0);
    }

    private ProxyInfo(int arg2) {
        super(16, arg2);
    }

    public static ProxyInfo decode(Decoder arg8) {
        ProxyInfo v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new ProxyInfo(arg8.readAndValidateDataHeader(ProxyInfo.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.proxyServers = new ProxyServer[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.proxyServers[v5] = ProxyServer.decode(v3.readPointer(v5 * 8 + v2, false));
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

    public static ProxyInfo deserialize(ByteBuffer arg2) {
        return ProxyInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProxyInfo deserialize(Message arg1) {
        return ProxyInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(ProxyInfo.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.proxyServers == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.proxyServers.length, v2, -1);
            int v0;
            for(v0 = 0; v0 < this.proxyServers.length; ++v0) {
                arg6.encode(this.proxyServers[v0], v0 * 8 + v2, false);
            }
        }
    }
}

