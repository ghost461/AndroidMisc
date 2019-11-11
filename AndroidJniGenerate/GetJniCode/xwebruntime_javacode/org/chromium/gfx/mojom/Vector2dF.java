package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Vector2dF extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public float x;
    public float y;

    static {
        Vector2dF.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Vector2dF.DEFAULT_STRUCT_INFO = Vector2dF.VERSION_ARRAY[0];
    }

    public Vector2dF() {
        this(0);
    }

    private Vector2dF(int arg2) {
        super(16, arg2);
    }

    public static Vector2dF decode(Decoder arg2) {
        Vector2dF v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Vector2dF(arg2.readAndValidateDataHeader(Vector2dF.VERSION_ARRAY).elementsOrVersion);
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

    public static Vector2dF deserialize(ByteBuffer arg2) {
        return Vector2dF.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Vector2dF deserialize(Message arg1) {
        return Vector2dF.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(Vector2dF.DEFAULT_STRUCT_INFO);
        arg3.encode(this.x, 8);
        arg3.encode(this.y, 12);
    }
}

