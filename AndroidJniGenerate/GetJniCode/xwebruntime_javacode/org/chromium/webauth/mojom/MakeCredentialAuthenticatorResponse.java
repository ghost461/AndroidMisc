package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MakeCredentialAuthenticatorResponse extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] attestationObject;
    public CommonCredentialInfo info;

    static {
        MakeCredentialAuthenticatorResponse.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        MakeCredentialAuthenticatorResponse.DEFAULT_STRUCT_INFO = MakeCredentialAuthenticatorResponse.VERSION_ARRAY[0];
    }

    public MakeCredentialAuthenticatorResponse() {
        this(0);
    }

    private MakeCredentialAuthenticatorResponse(int arg2) {
        super(24, arg2);
    }

    public static MakeCredentialAuthenticatorResponse decode(Decoder arg4) {
        MakeCredentialAuthenticatorResponse v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new MakeCredentialAuthenticatorResponse(arg4.readAndValidateDataHeader(MakeCredentialAuthenticatorResponse.VERSION_ARRAY).elementsOrVersion);
            v1.info = CommonCredentialInfo.decode(arg4.readPointer(8, false));
            v1.attestationObject = arg4.readBytes(16, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static MakeCredentialAuthenticatorResponse deserialize(ByteBuffer arg2) {
        return MakeCredentialAuthenticatorResponse.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MakeCredentialAuthenticatorResponse deserialize(Message arg1) {
        return MakeCredentialAuthenticatorResponse.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(MakeCredentialAuthenticatorResponse.DEFAULT_STRUCT_INFO);
        arg5.encode(this.info, 8, false);
        arg5.encode(this.attestationObject, 16, 0, -1);
    }
}

