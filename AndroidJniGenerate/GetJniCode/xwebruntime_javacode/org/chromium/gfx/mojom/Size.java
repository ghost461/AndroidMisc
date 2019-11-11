package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Size extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int height;
    public int width;

    static {
        Size.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Size.DEFAULT_STRUCT_INFO = Size.VERSION_ARRAY[0];
    }

    public Size() {
        this(0);
    }

    private Size(int arg2) {
        super(16, arg2);
    }

    public static Size decode(Decoder arg2) {
        Size v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Size(arg2.readAndValidateDataHeader(Size.VERSION_ARRAY).elementsOrVersion);
            v1.width = arg2.readInt(8);
            v1.height = arg2.readInt(12);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static Size deserialize(ByteBuffer arg2) {
        return Size.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Size deserialize(Message arg1) {
        return Size.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(Size.DEFAULT_STRUCT_INFO);
        arg3.encode(this.width, 8);
        arg3.encode(this.height, 12);
    }
}

