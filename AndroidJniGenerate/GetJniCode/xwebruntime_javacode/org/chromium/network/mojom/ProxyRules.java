package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ProxyRules extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public ProxyBypassRules bypassRules;
    public ProxyList fallbackProxies;
    public ProxyList proxiesForFtp;
    public ProxyList proxiesForHttp;
    public ProxyList proxiesForHttps;
    public boolean reverseBypass;
    public ProxyList singleProxies;
    public int type;

    static {
        ProxyRules.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        ProxyRules.DEFAULT_STRUCT_INFO = ProxyRules.VERSION_ARRAY[0];
    }

    public ProxyRules() {
        this(0);
    }

    private ProxyRules(int arg2) {
        super(0x40, arg2);
    }

    public static ProxyRules decode(Decoder arg3) {
        ProxyRules v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ProxyRules(arg3.readAndValidateDataHeader(ProxyRules.VERSION_ARRAY).elementsOrVersion);
            v1.bypassRules = ProxyBypassRules.decode(arg3.readPointer(8, false));
            v1.reverseBypass = arg3.readBoolean(16, 0);
            v1.type = arg3.readInt(20);
            ProxyRulesType.validate(v1.type);
            v1.singleProxies = ProxyList.decode(arg3.readPointer(24, false));
            v1.proxiesForHttp = ProxyList.decode(arg3.readPointer(0x20, false));
            v1.proxiesForHttps = ProxyList.decode(arg3.readPointer(40, false));
            v1.proxiesForFtp = ProxyList.decode(arg3.readPointer(0x30, false));
            v1.fallbackProxies = ProxyList.decode(arg3.readPointer(56, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ProxyRules deserialize(ByteBuffer arg2) {
        return ProxyRules.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProxyRules deserialize(Message arg1) {
        return ProxyRules.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ProxyRules.DEFAULT_STRUCT_INFO);
        arg4.encode(this.bypassRules, 8, false);
        arg4.encode(this.reverseBypass, 16, 0);
        arg4.encode(this.type, 20);
        arg4.encode(this.singleProxies, 24, false);
        arg4.encode(this.proxiesForHttp, 0x20, false);
        arg4.encode(this.proxiesForHttps, 40, false);
        arg4.encode(this.proxiesForFtp, 0x30, false);
        arg4.encode(this.fallbackProxies, 56, false);
    }
}

