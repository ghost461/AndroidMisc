package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class String16 extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public short[] data;

    static {
        String16.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        String16.DEFAULT_STRUCT_INFO = String16.VERSION_ARRAY[0];
    }

    public String16() {
        this(0);
    }

    private String16(int arg2) {
        super(16, arg2);
    }

    public static String16 decode(Decoder arg4) {
        String16 v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new String16(arg4.readAndValidateDataHeader(String16.VERSION_ARRAY).elementsOrVersion);
            v1.data = arg4.readShorts(8, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static String16 deserialize(ByteBuffer arg2) {
        return String16.deserialize(new Message(arg2, new ArrayList()));
    }

    public static String16 deserialize(Message arg1) {
        return String16.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5.getEncoderAtDataOffset(String16.DEFAULT_STRUCT_INFO).encode(this.data, 8, 0, -1);
    }
}

