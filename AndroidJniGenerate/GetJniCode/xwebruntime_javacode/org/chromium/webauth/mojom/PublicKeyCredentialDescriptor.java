package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PublicKeyCredentialDescriptor extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] id;
    public int[] transports;
    public int type;

    static {
        PublicKeyCredentialDescriptor.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        PublicKeyCredentialDescriptor.DEFAULT_STRUCT_INFO = PublicKeyCredentialDescriptor.VERSION_ARRAY[0];
    }

    public PublicKeyCredentialDescriptor() {
        this(0);
    }

    private PublicKeyCredentialDescriptor(int arg2) {
        super(0x20, arg2);
    }

    public static PublicKeyCredentialDescriptor decode(Decoder arg4) {
        PublicKeyCredentialDescriptor v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new PublicKeyCredentialDescriptor(arg4.readAndValidateDataHeader(PublicKeyCredentialDescriptor.VERSION_ARRAY).elementsOrVersion);
            v1.type = arg4.readInt(8);
            PublicKeyCredentialType.validate(v1.type);
            int v3 = 0;
            v1.id = arg4.readBytes(16, 0, -1);
            v1.transports = arg4.readInts(24, 0, -1);
            while(v3 < v1.transports.length) {
                AuthenticatorTransport.validate(v1.transports[v3]);
                ++v3;
            }
        }
        catch(Throwable v0) {
            goto label_33;
        }

        arg4.decreaseStackDepth();
        return v1;
    label_33:
        arg4.decreaseStackDepth();
        throw v0;
    }

    public static PublicKeyCredentialDescriptor deserialize(ByteBuffer arg2) {
        return PublicKeyCredentialDescriptor.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PublicKeyCredentialDescriptor deserialize(Message arg1) {
        return PublicKeyCredentialDescriptor.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(PublicKeyCredentialDescriptor.DEFAULT_STRUCT_INFO);
        arg5.encode(this.type, 8);
        arg5.encode(this.id, 16, 0, -1);
        arg5.encode(this.transports, 24, 0, -1);
    }
}

