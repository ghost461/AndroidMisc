package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CertVerifyResult extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        CertVerifyResult.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        CertVerifyResult.DEFAULT_STRUCT_INFO = CertVerifyResult.VERSION_ARRAY[0];
    }

    public CertVerifyResult() {
        this(0);
    }

    private CertVerifyResult(int arg2) {
        super(8, arg2);
    }

    public static CertVerifyResult decode(Decoder arg2) {
        CertVerifyResult v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new CertVerifyResult(arg2.readAndValidateDataHeader(CertVerifyResult.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static CertVerifyResult deserialize(ByteBuffer arg2) {
        return CertVerifyResult.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CertVerifyResult deserialize(Message arg1) {
        return CertVerifyResult.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(CertVerifyResult.DEFAULT_STRUCT_INFO);
    }
}

