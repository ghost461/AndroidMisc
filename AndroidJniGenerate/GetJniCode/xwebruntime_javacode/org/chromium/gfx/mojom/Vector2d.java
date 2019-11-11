package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Vector2d extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int x;
    public int y;

    static {
        Vector2d.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Vector2d.DEFAULT_STRUCT_INFO = Vector2d.VERSION_ARRAY[0];
    }

    public Vector2d() {
        this(0);
    }

    private Vector2d(int arg2) {
        super(16, arg2);
    }

    public static Vector2d decode(Decoder arg2) {
        Vector2d v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Vector2d(arg2.readAndValidateDataHeader(Vector2d.VERSION_ARRAY).elementsOrVersion);
            v1.x = arg2.readInt(8);
            v1.y = arg2.readInt(12);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static Vector2d deserialize(ByteBuffer arg2) {
        return Vector2d.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Vector2d deserialize(Message arg1) {
        return Vector2d.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(Vector2d.DEFAULT_STRUCT_INFO);
        arg3.encode(this.x, 8);
        arg3.encode(this.y, 12);
    }
}

