package org.chromium.webauth.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CommonCredentialInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] clientDataJson;
    public String id;
    public byte[] rawId;

    static {
        CommonCredentialInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        CommonCredentialInfo.DEFAULT_STRUCT_INFO = CommonCredentialInfo.VERSION_ARRAY[0];
    }

    public CommonCredentialInfo() {
        this(0);
    }

    private CommonCredentialInfo(int arg2) {
        super(0x20, arg2);
    }

    public static CommonCredentialInfo decode(Decoder arg4) {
        CommonCredentialInfo v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new CommonCredentialInfo(arg4.readAndValidateDataHeader(CommonCredentialInfo.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg4.readString(8, false);
            v1.rawId = arg4.readBytes(16, 0, -1);
            v1.clientDataJson = arg4.readBytes(24, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static CommonCredentialInfo deserialize(ByteBuffer arg2) {
        return CommonCredentialInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CommonCredentialInfo deserialize(Message arg1) {
        return CommonCredentialInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(CommonCredentialInfo.DEFAULT_STRUCT_INFO);
        arg5.encode(this.id, 8, false);
        arg5.encode(this.rawId, 16, 0, -1);
        arg5.encode(this.clientDataJson, 24, 0, -1);
    }
}

