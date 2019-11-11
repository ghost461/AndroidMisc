package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NfcError extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int errorType;

    static {
        NfcError.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        NfcError.DEFAULT_STRUCT_INFO = NfcError.VERSION_ARRAY[0];
    }

    public NfcError() {
        this(0);
    }

    private NfcError(int arg2) {
        super(16, arg2);
    }

    public static NfcError decode(Decoder arg2) {
        NfcError v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new NfcError(arg2.readAndValidateDataHeader(NfcError.VERSION_ARRAY).elementsOrVersion);
            v1.errorType = arg2.readInt(8);
            NfcErrorType.validate(v1.errorType);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static NfcError deserialize(ByteBuffer arg2) {
        return NfcError.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NfcError deserialize(Message arg1) {
        return NfcError.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(NfcError.DEFAULT_STRUCT_INFO).encode(this.errorType, 8);
    }
}

