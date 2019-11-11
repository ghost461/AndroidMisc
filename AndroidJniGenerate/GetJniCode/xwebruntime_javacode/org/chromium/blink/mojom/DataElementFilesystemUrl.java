package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.Time;
import org.chromium.url.mojom.Url;

public final class DataElementFilesystemUrl extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public Time expectedModificationTime;
    public long length;
    public long offset;
    public Url url;

    static {
        DataElementFilesystemUrl.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        DataElementFilesystemUrl.DEFAULT_STRUCT_INFO = DataElementFilesystemUrl.VERSION_ARRAY[0];
    }

    public DataElementFilesystemUrl() {
        this(0);
    }

    private DataElementFilesystemUrl(int arg2) {
        super(40, arg2);
    }

    public static DataElementFilesystemUrl decode(Decoder arg4) {
        DataElementFilesystemUrl v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new DataElementFilesystemUrl(arg4.readAndValidateDataHeader(DataElementFilesystemUrl.VERSION_ARRAY).elementsOrVersion);
            v1.url = Url.decode(arg4.readPointer(8, false));
            v1.offset = arg4.readLong(16);
            v1.length = arg4.readLong(24);
            v1.expectedModificationTime = Time.decode(arg4.readPointer(0x20, true));
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static DataElementFilesystemUrl deserialize(ByteBuffer arg2) {
        return DataElementFilesystemUrl.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DataElementFilesystemUrl deserialize(Message arg1) {
        return DataElementFilesystemUrl.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(DataElementFilesystemUrl.DEFAULT_STRUCT_INFO);
        arg4.encode(this.url, 8, false);
        arg4.encode(this.offset, 16);
        arg4.encode(this.length, 24);
        arg4.encode(this.expectedModificationTime, 0x20, true);
    }
}

