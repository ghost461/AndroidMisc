package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class PublicKeyCredentialRequestOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public TimeDelta adjustedTimeout;
    public PublicKeyCredentialDescriptor[] allowCredentials;
    public String appid;
    public byte[] challenge;
    public String relyingPartyId;
    public int userVerification;

    static {
        PublicKeyCredentialRequestOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        PublicKeyCredentialRequestOptions.DEFAULT_STRUCT_INFO = PublicKeyCredentialRequestOptions.VERSION_ARRAY[0];
    }

    public PublicKeyCredentialRequestOptions() {
        this(0);
    }

    private PublicKeyCredentialRequestOptions(int arg2) {
        super(56, arg2);
    }

    public static PublicKeyCredentialRequestOptions decode(Decoder arg8) {
        PublicKeyCredentialRequestOptions v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new PublicKeyCredentialRequestOptions(arg8.readAndValidateDataHeader(PublicKeyCredentialRequestOptions.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            v1.challenge = arg8.readBytes(v2, 0, -1);
            v1.adjustedTimeout = TimeDelta.decode(arg8.readPointer(16, false));
            v1.relyingPartyId = arg8.readString(24, false);
            Decoder v4 = arg8.readPointer(0x20, false);
            DataHeader v0_1 = v4.readDataHeaderForPointerArray(-1);
            v1.allowCredentials = new PublicKeyCredentialDescriptor[v0_1.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v0_1.elementsOrVersion; ++v5) {
                v1.allowCredentials[v5] = PublicKeyCredentialDescriptor.decode(v4.readPointer(v5 * 8 + v2, false));
            }

            v1.userVerification = arg8.readInt(40);
            UserVerificationRequirement.validate(v1.userVerification);
            v1.appid = arg8.readString(0x30, true);
        }
        catch(Throwable v0) {
            goto label_50;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_50:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static PublicKeyCredentialRequestOptions deserialize(ByteBuffer arg2) {
        return PublicKeyCredentialRequestOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PublicKeyCredentialRequestOptions deserialize(Message arg1) {
        return PublicKeyCredentialRequestOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(PublicKeyCredentialRequestOptions.DEFAULT_STRUCT_INFO);
        int v1 = -1;
        int v2 = 8;
        arg7.encode(this.challenge, v2, 0, v1);
        arg7.encode(this.adjustedTimeout, 16, false);
        arg7.encode(this.relyingPartyId, 24, false);
        int v4 = 0x20;
        if(this.allowCredentials == null) {
            arg7.encodeNullPointer(v4, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.allowCredentials.length, v4, v1);
            for(v1 = 0; v1 < this.allowCredentials.length; ++v1) {
                v0.encode(this.allowCredentials[v1], v1 * 8 + v2, false);
            }
        }

        arg7.encode(this.userVerification, 40);
        arg7.encode(this.appid, 0x30, true);
    }
}

