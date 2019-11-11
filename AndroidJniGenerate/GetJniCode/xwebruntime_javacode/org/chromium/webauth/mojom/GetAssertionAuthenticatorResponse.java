package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class GetAssertionAuthenticatorResponse extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] authenticatorData;
    public boolean echoAppidExtension;
    public CommonCredentialInfo info;
    public byte[] signature;
    public byte[] userHandle;

    static {
        GetAssertionAuthenticatorResponse.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        GetAssertionAuthenticatorResponse.DEFAULT_STRUCT_INFO = GetAssertionAuthenticatorResponse.VERSION_ARRAY[0];
    }

    public GetAssertionAuthenticatorResponse() {
        this(0);
    }

    private GetAssertionAuthenticatorResponse(int arg2) {
        super(0x30, arg2);
    }

    public static GetAssertionAuthenticatorResponse decode(Decoder arg5) {
        GetAssertionAuthenticatorResponse v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new GetAssertionAuthenticatorResponse(arg5.readAndValidateDataHeader(GetAssertionAuthenticatorResponse.VERSION_ARRAY).elementsOrVersion);
            v1.info = CommonCredentialInfo.decode(arg5.readPointer(8, false));
            v1.authenticatorData = arg5.readBytes(16, 0, -1);
            v1.signature = arg5.readBytes(24, 0, -1);
            v1.userHandle = arg5.readBytes(0x20, 1, -1);
            v1.echoAppidExtension = arg5.readBoolean(40, 0);
        }
        catch(Throwable v0) {
            arg5.decreaseStackDepth();
            throw v0;
        }

        arg5.decreaseStackDepth();
        return v1;
    }

    public static GetAssertionAuthenticatorResponse deserialize(ByteBuffer arg2) {
        return GetAssertionAuthenticatorResponse.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GetAssertionAuthenticatorResponse deserialize(Message arg1) {
        return GetAssertionAuthenticatorResponse.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(GetAssertionAuthenticatorResponse.DEFAULT_STRUCT_INFO);
        arg6.encode(this.info, 8, false);
        arg6.encode(this.authenticatorData, 16, 0, -1);
        arg6.encode(this.signature, 24, 0, -1);
        arg6.encode(this.userHandle, 0x20, 1, -1);
        arg6.encode(this.echoAppidExtension, 40, 0);
    }
}

