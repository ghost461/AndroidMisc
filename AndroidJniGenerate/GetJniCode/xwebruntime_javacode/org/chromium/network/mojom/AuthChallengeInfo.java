package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class AuthChallengeInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        AuthChallengeInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        AuthChallengeInfo.DEFAULT_STRUCT_INFO = AuthChallengeInfo.VERSION_ARRAY[0];
    }

    public AuthChallengeInfo() {
        this(0);
    }

    private AuthChallengeInfo(int arg2) {
        super(8, arg2);
    }

    public static AuthChallengeInfo decode(Decoder arg2) {
        AuthChallengeInfo v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new AuthChallengeInfo(arg2.readAndValidateDataHeader(AuthChallengeInfo.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static AuthChallengeInfo deserialize(ByteBuffer arg2) {
        return AuthChallengeInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AuthChallengeInfo deserialize(Message arg1) {
        return AuthChallengeInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(AuthChallengeInfo.DEFAULT_STRUCT_INFO);
    }
}

