package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ProxyList extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public String[] proxies;

    static {
        ProxyList.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ProxyList.DEFAULT_STRUCT_INFO = ProxyList.VERSION_ARRAY[0];
    }

    public ProxyList() {
        this(0);
    }

    private ProxyList(int arg2) {
        super(16, arg2);
    }

    public static ProxyList decode(Decoder arg8) {
        ProxyList v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new ProxyList(arg8.readAndValidateDataHeader(ProxyList.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.proxies = new String[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.proxies[v5] = v3.readString(v5 * 8 + v2, false);
            }
        }
        catch(Throwable v0) {
            goto label_30;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_30:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static ProxyList deserialize(ByteBuffer arg2) {
        return ProxyList.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProxyList deserialize(Message arg1) {
        return ProxyList.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(ProxyList.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.proxies == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.proxies.length, v2, -1);
            int v0;
            for(v0 = 0; v0 < this.proxies.length; ++v0) {
                arg6.encode(this.proxies[v0], v0 * 8 + v2, false);
            }
        }
    }
}

