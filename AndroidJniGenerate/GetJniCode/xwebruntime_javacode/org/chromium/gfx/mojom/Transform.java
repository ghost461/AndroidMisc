package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Transform extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public float[] matrix;

    static {
        Transform.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Transform.DEFAULT_STRUCT_INFO = Transform.VERSION_ARRAY[0];
    }

    public Transform() {
        this(0);
    }

    private Transform(int arg2) {
        super(16, arg2);
    }

    public static Transform decode(Decoder arg4) {
        Transform v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new Transform(arg4.readAndValidateDataHeader(Transform.VERSION_ARRAY).elementsOrVersion);
            v1.matrix = arg4.readFloats(8, 1, 16);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static Transform deserialize(ByteBuffer arg2) {
        return Transform.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Transform deserialize(Message arg1) {
        return Transform.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5.getEncoderAtDataOffset(Transform.DEFAULT_STRUCT_INFO).encode(this.matrix, 8, 1, 16);
    }
}

