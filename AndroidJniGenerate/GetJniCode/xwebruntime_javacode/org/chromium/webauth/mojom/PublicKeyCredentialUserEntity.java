package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class PublicKeyCredentialUserEntity extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public String displayName;
    public Url icon;
    public byte[] id;
    public String name;

    static {
        PublicKeyCredentialUserEntity.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        PublicKeyCredentialUserEntity.DEFAULT_STRUCT_INFO = PublicKeyCredentialUserEntity.VERSION_ARRAY[0];
    }

    public PublicKeyCredentialUserEntity() {
        this(0);
    }

    private PublicKeyCredentialUserEntity(int arg2) {
        super(40, arg2);
    }

    public static PublicKeyCredentialUserEntity decode(Decoder arg4) {
        PublicKeyCredentialUserEntity v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new PublicKeyCredentialUserEntity(arg4.readAndValidateDataHeader(PublicKeyCredentialUserEntity.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg4.readBytes(8, 0, -1);
            v1.name = arg4.readString(16, false);
            v1.icon = Url.decode(arg4.readPointer(24, true));
            v1.displayName = arg4.readString(0x20, false);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static PublicKeyCredentialUserEntity deserialize(ByteBuffer arg2) {
        return PublicKeyCredentialUserEntity.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PublicKeyCredentialUserEntity deserialize(Message arg1) {
        return PublicKeyCredentialUserEntity.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(PublicKeyCredentialUserEntity.DEFAULT_STRUCT_INFO);
        arg5.encode(this.id, 8, 0, -1);
        arg5.encode(this.name, 16, false);
        arg5.encode(this.icon, 24, true);
        arg5.encode(this.displayName, 0x20, false);
    }
}

