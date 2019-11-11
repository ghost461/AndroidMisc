package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class InsetsF extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public float bottom;
    public float left;
    public float right;
    public float top;

    static {
        InsetsF.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        InsetsF.DEFAULT_STRUCT_INFO = InsetsF.VERSION_ARRAY[0];
    }

    public InsetsF() {
        this(0);
    }

    private InsetsF(int arg2) {
        super(24, arg2);
    }

    public static InsetsF decode(Decoder arg2) {
        InsetsF v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new InsetsF(arg2.readAndValidateDataHeader(InsetsF.VERSION_ARRAY).elementsOrVersion);
            v1.top = arg2.readFloat(8);
            v1.left = arg2.readFloat(12);
            v1.bottom = arg2.readFloat(16);
            v1.right = arg2.readFloat(20);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static InsetsF deserialize(ByteBuffer arg2) {
        return InsetsF.deserialize(new Message(arg2, new ArrayList()));
    }

    public static InsetsF deserialize(Message arg1) {
        return InsetsF.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(InsetsF.DEFAULT_STRUCT_INFO);
        arg3.encode(this.top, 8);
        arg3.encode(this.left, 12);
        arg3.encode(this.bottom, 16);
        arg3.encode(this.right, 20);
    }
}

