package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NfcRecord extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] data;
    public String mediaType;
    public int recordType;

    static {
        NfcRecord.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        NfcRecord.DEFAULT_STRUCT_INFO = NfcRecord.VERSION_ARRAY[0];
    }

    public NfcRecord() {
        this(0);
    }

    private NfcRecord(int arg2) {
        super(0x20, arg2);
    }

    public static NfcRecord decode(Decoder arg4) {
        NfcRecord v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new NfcRecord(arg4.readAndValidateDataHeader(NfcRecord.VERSION_ARRAY).elementsOrVersion);
            v1.recordType = arg4.readInt(8);
            NfcRecordType.validate(v1.recordType);
            v1.mediaType = arg4.readString(16, true);
            v1.data = arg4.readBytes(24, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static NfcRecord deserialize(ByteBuffer arg2) {
        return NfcRecord.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NfcRecord deserialize(Message arg1) {
        return NfcRecord.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(NfcRecord.DEFAULT_STRUCT_INFO);
        arg5.encode(this.recordType, 8);
        arg5.encode(this.mediaType, 16, true);
        arg5.encode(this.data, 24, 0, -1);
    }
}

