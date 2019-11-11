package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class ProxyConfig extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean autoDetect;
    public boolean pacMandatory;
    public Url pacUrl;
    public ProxyRules proxyRules;

    static {
        ProxyConfig.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        ProxyConfig.DEFAULT_STRUCT_INFO = ProxyConfig.VERSION_ARRAY[0];
    }

    public ProxyConfig() {
        this(0);
    }

    private ProxyConfig(int arg2) {
        super(0x20, arg2);
    }

    public static ProxyConfig decode(Decoder arg4) {
        ProxyConfig v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new ProxyConfig(arg4.readAndValidateDataHeader(ProxyConfig.VERSION_ARRAY).elementsOrVersion);
            v1.autoDetect = arg4.readBoolean(8, 0);
            v1.pacMandatory = arg4.readBoolean(8, 1);
            v1.pacUrl = Url.decode(arg4.readPointer(16, false));
            v1.proxyRules = ProxyRules.decode(arg4.readPointer(24, false));
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static ProxyConfig deserialize(ByteBuffer arg2) {
        return ProxyConfig.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProxyConfig deserialize(Message arg1) {
        return ProxyConfig.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(ProxyConfig.DEFAULT_STRUCT_INFO);
        arg5.encode(this.autoDetect, 8, 0);
        arg5.encode(this.pacMandatory, 8, 1);
        arg5.encode(this.pacUrl, 16, false);
        arg5.encode(this.proxyRules, 24, false);
    }
}

