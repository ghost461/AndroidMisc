package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class BufferUsageAndFormat extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int format;
    public int usage;

    static {
        BufferUsageAndFormat.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        BufferUsageAndFormat.DEFAULT_STRUCT_INFO = BufferUsageAndFormat.VERSION_ARRAY[0];
    }

    public BufferUsageAndFormat() {
        this(0);
    }

    private BufferUsageAndFormat(int arg2) {
        super(16, arg2);
    }

    public static BufferUsageAndFormat decode(Decoder arg2) {
        BufferUsageAndFormat v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new BufferUsageAndFormat(arg2.readAndValidateDataHeader(BufferUsageAndFormat.VERSION_ARRAY).elementsOrVersion);
            v1.usage = arg2.readInt(8);
            BufferUsage.validate(v1.usage);
            v1.format = arg2.readInt(12);
            BufferFormat.validate(v1.format);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static BufferUsageAndFormat deserialize(ByteBuffer arg2) {
        return BufferUsageAndFormat.deserialize(new Message(arg2, new ArrayList()));
    }

    public static BufferUsageAndFormat deserialize(Message arg1) {
        return BufferUsageAndFormat.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(BufferUsageAndFormat.DEFAULT_STRUCT_INFO);
        arg3.encode(this.usage, 8);
        arg3.encode(this.format, 12);
    }
}

