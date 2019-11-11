package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SerializedBlob extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public Blob blob;
    public String contentType;
    public long size;
    public String uuid;

    static {
        SerializedBlob.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        SerializedBlob.DEFAULT_STRUCT_INFO = SerializedBlob.VERSION_ARRAY[0];
    }

    public SerializedBlob() {
        this(0);
    }

    private SerializedBlob(int arg2) {
        super(40, arg2);
    }

    public static SerializedBlob decode(Decoder arg5) {
        SerializedBlob v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new SerializedBlob(arg5.readAndValidateDataHeader(SerializedBlob.VERSION_ARRAY).elementsOrVersion);
            v1.uuid = arg5.readString(8, false);
            v1.contentType = arg5.readString(16, false);
            v1.size = arg5.readLong(24);
            v1.blob = arg5.readServiceInterface(0x20, false, Blob.MANAGER);
        }
        catch(Throwable v0) {
            arg5.decreaseStackDepth();
            throw v0;
        }

        arg5.decreaseStackDepth();
        return v1;
    }

    public static SerializedBlob deserialize(ByteBuffer arg2) {
        return SerializedBlob.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerializedBlob deserialize(Message arg1) {
        return SerializedBlob.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(SerializedBlob.DEFAULT_STRUCT_INFO);
        arg5.encode(this.uuid, 8, false);
        arg5.encode(this.contentType, 16, false);
        arg5.encode(this.size, 24);
        arg5.encode(this.blob, 0x20, false, Blob.MANAGER);
    }
}

