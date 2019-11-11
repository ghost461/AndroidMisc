package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class AuthenticatorSelectionCriteria extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int authenticatorAttachment;
    public boolean requireResidentKey;
    public int userVerification;

    static {
        AuthenticatorSelectionCriteria.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        AuthenticatorSelectionCriteria.DEFAULT_STRUCT_INFO = AuthenticatorSelectionCriteria.VERSION_ARRAY[0];
    }

    public AuthenticatorSelectionCriteria() {
        this(0);
    }

    private AuthenticatorSelectionCriteria(int arg2) {
        super(24, arg2);
    }

    public static AuthenticatorSelectionCriteria decode(Decoder arg3) {
        AuthenticatorSelectionCriteria v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new AuthenticatorSelectionCriteria(arg3.readAndValidateDataHeader(AuthenticatorSelectionCriteria.VERSION_ARRAY).elementsOrVersion);
            v1.authenticatorAttachment = arg3.readInt(8);
            AuthenticatorAttachment.validate(v1.authenticatorAttachment);
            v1.requireResidentKey = arg3.readBoolean(12, 0);
            v1.userVerification = arg3.readInt(16);
            UserVerificationRequirement.validate(v1.userVerification);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static AuthenticatorSelectionCriteria deserialize(ByteBuffer arg2) {
        return AuthenticatorSelectionCriteria.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AuthenticatorSelectionCriteria deserialize(Message arg1) {
        return AuthenticatorSelectionCriteria.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(AuthenticatorSelectionCriteria.DEFAULT_STRUCT_INFO);
        arg4.encode(this.authenticatorAttachment, 8);
        arg4.encode(this.requireResidentKey, 12, 0);
        arg4.encode(this.userVerification, 16);
    }
}

