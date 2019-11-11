package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class DataElementBytes extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    public static final long MAXIMUM_EMBEDDED_DATA_SIZE = 256000;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public BytesProvider data;
    public byte[] embeddedData;
    public long length;

    static {
        DataElementBytes.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        DataElementBytes.DEFAULT_STRUCT_INFO = DataElementBytes.VERSION_ARRAY[0];
    }

    public DataElementBytes() {
        this(0);
    }

    private DataElementBytes(int arg2) {
        super(0x20, arg2);
    }

    public static DataElementBytes decode(Decoder arg4) {
        DataElementBytes v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new DataElementBytes(arg4.readAndValidateDataHeader(DataElementBytes.VERSION_ARRAY).elementsOrVersion);
            v1.length = arg4.readLong(8);
            v1.embeddedData = arg4.readBytes(16, 1, -1);
            v1.data = arg4.readServiceInterface(24, false, BytesProvider.MANAGER);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static DataElementBytes deserialize(ByteBuffer arg2) {
        return DataElementBytes.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DataElementBytes deserialize(Message arg1) {
        return DataElementBytes.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(DataElementBytes.DEFAULT_STRUCT_INFO);
        arg5.encode(this.length, 8);
        arg5.encode(this.embeddedData, 16, 1, -1);
        arg5.encode(this.data, 24, false, BytesProvider.MANAGER);
    }
}

