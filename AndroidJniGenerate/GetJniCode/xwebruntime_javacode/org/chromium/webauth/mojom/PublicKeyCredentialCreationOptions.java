package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class PublicKeyCredentialCreationOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 72;
    private static final DataHeader[] VERSION_ARRAY;
    public TimeDelta adjustedTimeout;
    public int attestation;
    public AuthenticatorSelectionCriteria authenticatorSelection;
    public byte[] challenge;
    public PublicKeyCredentialDescriptor[] excludeCredentials;
    public PublicKeyCredentialParameters[] publicKeyParameters;
    public PublicKeyCredentialRpEntity relyingParty;
    public PublicKeyCredentialUserEntity user;

    static {
        PublicKeyCredentialCreationOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(72, 0)};
        PublicKeyCredentialCreationOptions.DEFAULT_STRUCT_INFO = PublicKeyCredentialCreationOptions.VERSION_ARRAY[0];
    }

    public PublicKeyCredentialCreationOptions() {
        this(0);
    }

    private PublicKeyCredentialCreationOptions(int arg2) {
        super(72, arg2);
    }

    public static PublicKeyCredentialCreationOptions decode(Decoder arg9) {
        PublicKeyCredentialCreationOptions v1;
        if(arg9 == null) {
            return null;
        }

        arg9.increaseStackDepth();
        try {
            v1 = new PublicKeyCredentialCreationOptions(arg9.readAndValidateDataHeader(PublicKeyCredentialCreationOptions.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.relyingParty = PublicKeyCredentialRpEntity.decode(arg9.readPointer(v0_1, false));
            v1.user = PublicKeyCredentialUserEntity.decode(arg9.readPointer(16, false));
            int v4 = -1;
            v1.challenge = arg9.readBytes(24, 0, v4);
            Decoder v3 = arg9.readPointer(0x20, false);
            DataHeader v5 = v3.readDataHeaderForPointerArray(v4);
            v1.publicKeyParameters = new PublicKeyCredentialParameters[v5.elementsOrVersion];
            int v6;
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.publicKeyParameters[v6] = PublicKeyCredentialParameters.decode(v3.readPointer(v6 * 8 + v0_1, false));
            }

            v1.adjustedTimeout = TimeDelta.decode(arg9.readPointer(40, false));
            v3 = arg9.readPointer(0x30, false);
            DataHeader v4_1 = v3.readDataHeaderForPointerArray(v4);
            v1.excludeCredentials = new PublicKeyCredentialDescriptor[v4_1.elementsOrVersion];
            int v5_1;
            for(v5_1 = 0; v5_1 < v4_1.elementsOrVersion; ++v5_1) {
                v1.excludeCredentials[v5_1] = PublicKeyCredentialDescriptor.decode(v3.readPointer(v5_1 * 8 + v0_1, false));
            }

            v1.authenticatorSelection = AuthenticatorSelectionCriteria.decode(arg9.readPointer(56, true));
            v1.attestation = arg9.readInt(0x40);
            AttestationConveyancePreference.validate(v1.attestation);
        }
        catch(Throwable v0) {
            goto label_73;
        }

        arg9.decreaseStackDepth();
        return v1;
    label_73:
        arg9.decreaseStackDepth();
        throw v0;
    }

    public static PublicKeyCredentialCreationOptions deserialize(ByteBuffer arg2) {
        return PublicKeyCredentialCreationOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PublicKeyCredentialCreationOptions deserialize(Message arg1) {
        return PublicKeyCredentialCreationOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg8) {
        Encoder v0;
        arg8 = arg8.getEncoderAtDataOffset(PublicKeyCredentialCreationOptions.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg8.encode(this.relyingParty, v1, false);
        arg8.encode(this.user, 16, false);
        int v3 = -1;
        arg8.encode(this.challenge, 24, 0, v3);
        int v4 = 0x20;
        if(this.publicKeyParameters == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.publicKeyParameters.length, v4, v3);
            for(v4 = 0; v4 < this.publicKeyParameters.length; ++v4) {
                v0.encode(this.publicKeyParameters[v4], v4 * 8 + v1, false);
            }
        }

        arg8.encode(this.adjustedTimeout, 40, false);
        v4 = 0x30;
        if(this.excludeCredentials == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.excludeCredentials.length, v4, v3);
            for(v3 = 0; v3 < this.excludeCredentials.length; ++v3) {
                v0.encode(this.excludeCredentials[v3], v3 * 8 + v1, false);
            }
        }

        arg8.encode(this.authenticatorSelection, 56, true);
        arg8.encode(this.attestation, 0x40);
    }
}

