package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PublicKeyCredentialParameters extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int algorithmIdentifier;
    public int type;

    static {
        PublicKeyCredentialParameters.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        PublicKeyCredentialParameters.DEFAULT_STRUCT_INFO = PublicKeyCredentialParameters.VERSION_ARRAY[0];
    }

    public PublicKeyCredentialParameters() {
        this(0);
    }

    private PublicKeyCredentialParameters(int arg2) {
        super(16, arg2);
    }

    public static PublicKeyCredentialParameters decode(Decoder arg2) {
        PublicKeyCredentialParameters v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new PublicKeyCredentialParameters(arg2.readAndValidateDataHeader(PublicKeyCredentialParameters.VERSION_ARRAY).elementsOrVersion);
            v1.type = arg2.readInt(8);
            PublicKeyCredentialType.validate(v1.type);
            v1.algorithmIdentifier = arg2.readInt(12);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static PublicKeyCredentialParameters deserialize(ByteBuffer arg2) {
        return PublicKeyCredentialParameters.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PublicKeyCredentialParameters deserialize(Message arg1) {
        return PublicKeyCredentialParameters.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(PublicKeyCredentialParameters.DEFAULT_STRUCT_INFO);
        arg3.encode(this.type, 8);
        arg3.encode(this.algorithmIdentifier, 12);
    }
}

