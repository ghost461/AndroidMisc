package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.BigBuffer;

public final class SerializedArrayBufferContents extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public BigBuffer contents;

    static {
        SerializedArrayBufferContents.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        SerializedArrayBufferContents.DEFAULT_STRUCT_INFO = SerializedArrayBufferContents.VERSION_ARRAY[0];
    }

    public SerializedArrayBufferContents() {
        this(0);
    }

    private SerializedArrayBufferContents(int arg2) {
        super(24, arg2);
    }

    public static SerializedArrayBufferContents decode(Decoder arg2) {
        SerializedArrayBufferContents v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new SerializedArrayBufferContents(arg2.readAndValidateDataHeader(SerializedArrayBufferContents.VERSION_ARRAY).elementsOrVersion);
            v1.contents = BigBuffer.decode(arg2, 8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static SerializedArrayBufferContents deserialize(ByteBuffer arg2) {
        return SerializedArrayBufferContents.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerializedArrayBufferContents deserialize(Message arg1) {
        return SerializedArrayBufferContents.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(SerializedArrayBufferContents.DEFAULT_STRUCT_INFO).encode(this.contents, 8, false);
    }
}

