package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class DataElementBlob extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public Blob blob;
    public long length;
    public long offset;

    static {
        DataElementBlob.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        DataElementBlob.DEFAULT_STRUCT_INFO = DataElementBlob.VERSION_ARRAY[0];
    }

    public DataElementBlob() {
        this(0);
    }

    private DataElementBlob(int arg2) {
        super(0x20, arg2);
    }

    public static DataElementBlob decode(Decoder arg4) {
        DataElementBlob v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new DataElementBlob(arg4.readAndValidateDataHeader(DataElementBlob.VERSION_ARRAY).elementsOrVersion);
            v1.blob = arg4.readServiceInterface(8, false, Blob.MANAGER);
            v1.offset = arg4.readLong(16);
            v1.length = arg4.readLong(24);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static DataElementBlob deserialize(ByteBuffer arg2) {
        return DataElementBlob.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DataElementBlob deserialize(Message arg1) {
        return DataElementBlob.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(DataElementBlob.DEFAULT_STRUCT_INFO);
        arg5.encode(this.blob, 8, false, Blob.MANAGER);
        arg5.encode(this.offset, 16);
        arg5.encode(this.length, 24);
    }
}

