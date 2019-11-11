package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NfcRecordTypeFilter extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int recordType;

    static {
        NfcRecordTypeFilter.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        NfcRecordTypeFilter.DEFAULT_STRUCT_INFO = NfcRecordTypeFilter.VERSION_ARRAY[0];
    }

    public NfcRecordTypeFilter() {
        this(0);
    }

    private NfcRecordTypeFilter(int arg2) {
        super(16, arg2);
    }

    public static NfcRecordTypeFilter decode(Decoder arg2) {
        NfcRecordTypeFilter v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new NfcRecordTypeFilter(arg2.readAndValidateDataHeader(NfcRecordTypeFilter.VERSION_ARRAY).elementsOrVersion);
            v1.recordType = arg2.readInt(8);
            NfcRecordType.validate(v1.recordType);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static NfcRecordTypeFilter deserialize(ByteBuffer arg2) {
        return NfcRecordTypeFilter.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NfcRecordTypeFilter deserialize(Message arg1) {
        return NfcRecordTypeFilter.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(NfcRecordTypeFilter.DEFAULT_STRUCT_INFO).encode(this.recordType, 8);
    }
}

