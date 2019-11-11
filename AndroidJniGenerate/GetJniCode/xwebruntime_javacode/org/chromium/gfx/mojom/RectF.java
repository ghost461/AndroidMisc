package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RectF extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public float height;
    public float width;
    public float x;
    public float y;

    static {
        RectF.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        RectF.DEFAULT_STRUCT_INFO = RectF.VERSION_ARRAY[0];
    }

    public RectF() {
        this(0);
    }

    private RectF(int arg2) {
        super(24, arg2);
    }

    public static RectF decode(Decoder arg2) {
        RectF v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RectF(arg2.readAndValidateDataHeader(RectF.VERSION_ARRAY).elementsOrVersion);
            v1.x = arg2.readFloat(8);
            v1.y = arg2.readFloat(12);
            v1.width = arg2.readFloat(16);
            v1.height = arg2.readFloat(20);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RectF deserialize(ByteBuffer arg2) {
        return RectF.deserialize(new Message(arg2, new ArrayList()));
    }

    public static RectF deserialize(Message arg1) {
        return RectF.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(RectF.DEFAULT_STRUCT_INFO);
        arg3.encode(this.x, 8);
        arg3.encode(this.y, 12);
        arg3.encode(this.width, 16);
        arg3.encode(this.height, 20);
    }
}

