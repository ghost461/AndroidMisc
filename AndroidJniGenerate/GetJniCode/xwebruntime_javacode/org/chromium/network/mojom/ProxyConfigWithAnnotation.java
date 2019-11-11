package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ProxyConfigWithAnnotation extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public MutableNetworkTrafficAnnotationTag trafficAnnotation;
    public ProxyConfig value;

    static {
        ProxyConfigWithAnnotation.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        ProxyConfigWithAnnotation.DEFAULT_STRUCT_INFO = ProxyConfigWithAnnotation.VERSION_ARRAY[0];
    }

    public ProxyConfigWithAnnotation() {
        this(0);
    }

    private ProxyConfigWithAnnotation(int arg2) {
        super(24, arg2);
    }

    public static ProxyConfigWithAnnotation decode(Decoder arg3) {
        ProxyConfigWithAnnotation v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ProxyConfigWithAnnotation(arg3.readAndValidateDataHeader(ProxyConfigWithAnnotation.VERSION_ARRAY).elementsOrVersion);
            v1.value = ProxyConfig.decode(arg3.readPointer(8, false));
            v1.trafficAnnotation = MutableNetworkTrafficAnnotationTag.decode(arg3.readPointer(16, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ProxyConfigWithAnnotation deserialize(ByteBuffer arg2) {
        return ProxyConfigWithAnnotation.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProxyConfigWithAnnotation deserialize(Message arg1) {
        return ProxyConfigWithAnnotation.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ProxyConfigWithAnnotation.DEFAULT_STRUCT_INFO);
        arg4.encode(this.value, 8, false);
        arg4.encode(this.trafficAnnotation, 16, false);
    }
}

