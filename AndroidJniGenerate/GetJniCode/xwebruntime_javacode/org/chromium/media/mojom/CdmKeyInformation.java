package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CdmKeyInformation extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] keyId;
    public int status;
    public int systemCode;

    static {
        CdmKeyInformation.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        CdmKeyInformation.DEFAULT_STRUCT_INFO = CdmKeyInformation.VERSION_ARRAY[0];
    }

    public CdmKeyInformation() {
        this(0);
    }

    private CdmKeyInformation(int arg2) {
        super(24, arg2);
    }

    public static CdmKeyInformation decode(Decoder arg4) {
        CdmKeyInformation v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new CdmKeyInformation(arg4.readAndValidateDataHeader(CdmKeyInformation.VERSION_ARRAY).elementsOrVersion);
            v1.keyId = arg4.readBytes(8, 0, -1);
            v1.status = arg4.readInt(16);
            CdmKeyStatus.validate(v1.status);
            v1.systemCode = arg4.readInt(20);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static CdmKeyInformation deserialize(ByteBuffer arg2) {
        return CdmKeyInformation.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CdmKeyInformation deserialize(Message arg1) {
        return CdmKeyInformation.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(CdmKeyInformation.DEFAULT_STRUCT_INFO);
        arg5.encode(this.keyId, 8, 0, -1);
        arg5.encode(this.status, 16);
        arg5.encode(this.systemCode, 20);
    }
}

