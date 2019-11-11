package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ScrollOffset extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public float x;
    public float y;

    static {
        ScrollOffset.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ScrollOffset.DEFAULT_STRUCT_INFO = ScrollOffset.VERSION_ARRAY[0];
    }

    public ScrollOffset() {
        this(0);
    }

    private ScrollOffset(int arg2) {
        super(16, arg2);
    }

    public static ScrollOffset decode(Decoder arg2) {
        ScrollOffset v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new ScrollOffset(arg2.readAndValidateDataHeader(ScrollOffset.VERSION_ARRAY).elementsOrVersion);
            v1.x = arg2.readFloat(8);
            v1.y = arg2.readFloat(12);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static ScrollOffset deserialize(ByteBuffer arg2) {
        return ScrollOffset.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ScrollOffset deserialize(Message arg1) {
        return ScrollOffset.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(ScrollOffset.DEFAULT_STRUCT_INFO);
        arg3.encode(this.x, 8);
        arg3.encode(this.y, 12);
    }
}

