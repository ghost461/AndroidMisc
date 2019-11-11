package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class PublicKeyCredentialRpEntity extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public Url icon;
    public String id;
    public String name;

    static {
        PublicKeyCredentialRpEntity.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        PublicKeyCredentialRpEntity.DEFAULT_STRUCT_INFO = PublicKeyCredentialRpEntity.VERSION_ARRAY[0];
    }

    public PublicKeyCredentialRpEntity() {
        this(0);
    }

    private PublicKeyCredentialRpEntity(int arg2) {
        super(0x20, arg2);
    }

    public static PublicKeyCredentialRpEntity decode(Decoder arg3) {
        PublicKeyCredentialRpEntity v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PublicKeyCredentialRpEntity(arg3.readAndValidateDataHeader(PublicKeyCredentialRpEntity.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg3.readString(8, false);
            v1.name = arg3.readString(16, false);
            v1.icon = Url.decode(arg3.readPointer(24, true));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PublicKeyCredentialRpEntity deserialize(ByteBuffer arg2) {
        return PublicKeyCredentialRpEntity.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PublicKeyCredentialRpEntity deserialize(Message arg1) {
        return PublicKeyCredentialRpEntity.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PublicKeyCredentialRpEntity.DEFAULT_STRUCT_INFO);
        arg4.encode(this.id, 8, false);
        arg4.encode(this.name, 16, false);
        arg4.encode(this.icon, 24, true);
    }
}

