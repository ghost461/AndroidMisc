package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class X509Certificate extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        X509Certificate.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        X509Certificate.DEFAULT_STRUCT_INFO = X509Certificate.VERSION_ARRAY[0];
    }

    public X509Certificate() {
        this(0);
    }

    private X509Certificate(int arg2) {
        super(8, arg2);
    }

    public static X509Certificate decode(Decoder arg2) {
        X509Certificate v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new X509Certificate(arg2.readAndValidateDataHeader(X509Certificate.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static X509Certificate deserialize(ByteBuffer arg2) {
        return X509Certificate.deserialize(new Message(arg2, new ArrayList()));
    }

    public static X509Certificate deserialize(Message arg1) {
        return X509Certificate.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(X509Certificate.DEFAULT_STRUCT_INFO);
    }
}

