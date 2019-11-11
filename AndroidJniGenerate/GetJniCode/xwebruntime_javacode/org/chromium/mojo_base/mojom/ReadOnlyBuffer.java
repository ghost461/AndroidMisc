package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ReadOnlyBuffer extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] buffer;

    static {
        ReadOnlyBuffer.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ReadOnlyBuffer.DEFAULT_STRUCT_INFO = ReadOnlyBuffer.VERSION_ARRAY[0];
    }

    public ReadOnlyBuffer() {
        this(0);
    }

    private ReadOnlyBuffer(int arg2) {
        super(16, arg2);
    }

    public static ReadOnlyBuffer decode(Decoder arg4) {
        ReadOnlyBuffer v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new ReadOnlyBuffer(arg4.readAndValidateDataHeader(ReadOnlyBuffer.VERSION_ARRAY).elementsOrVersion);
            v1.buffer = arg4.readBytes(8, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static ReadOnlyBuffer deserialize(ByteBuffer arg2) {
        return ReadOnlyBuffer.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ReadOnlyBuffer deserialize(Message arg1) {
        return ReadOnlyBuffer.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5.getEncoderAtDataOffset(ReadOnlyBuffer.DEFAULT_STRUCT_INFO).encode(this.buffer, 8, 0, -1);
    }
}

