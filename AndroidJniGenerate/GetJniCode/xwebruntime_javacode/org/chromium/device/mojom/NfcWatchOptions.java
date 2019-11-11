package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NfcWatchOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public String mediaType;
    public int mode;
    public NfcRecordTypeFilter recordFilter;
    public String url;

    static {
        NfcWatchOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        NfcWatchOptions.DEFAULT_STRUCT_INFO = NfcWatchOptions.VERSION_ARRAY[0];
    }

    public NfcWatchOptions() {
        this(0);
    }

    private NfcWatchOptions(int arg2) {
        super(40, arg2);
    }

    public static NfcWatchOptions decode(Decoder arg3) {
        NfcWatchOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new NfcWatchOptions(arg3.readAndValidateDataHeader(NfcWatchOptions.VERSION_ARRAY).elementsOrVersion);
            v1.url = arg3.readString(8, true);
            v1.recordFilter = NfcRecordTypeFilter.decode(arg3.readPointer(16, true));
            v1.mediaType = arg3.readString(24, true);
            v1.mode = arg3.readInt(0x20);
            NfcWatchMode.validate(v1.mode);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static NfcWatchOptions deserialize(ByteBuffer arg2) {
        return NfcWatchOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NfcWatchOptions deserialize(Message arg1) {
        return NfcWatchOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(NfcWatchOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.url, 8, true);
        arg4.encode(this.recordFilter, 16, true);
        arg4.encode(this.mediaType, 24, true);
        arg4.encode(this.mode, 0x20);
    }
}

