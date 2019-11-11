package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SizeF extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public float height;
    public float width;

    static {
        SizeF.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SizeF.DEFAULT_STRUCT_INFO = SizeF.VERSION_ARRAY[0];
    }

    public SizeF() {
        this(0);
    }

    private SizeF(int arg2) {
        super(16, arg2);
    }

    public static SizeF decode(Decoder arg2) {
        SizeF v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new SizeF(arg2.readAndValidateDataHeader(SizeF.VERSION_ARRAY).elementsOrVersion);
            v1.width = arg2.readFloat(8);
            v1.height = arg2.readFloat(12);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static SizeF deserialize(ByteBuffer arg2) {
        return SizeF.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SizeF deserialize(Message arg1) {
        return SizeF.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(SizeF.DEFAULT_STRUCT_INFO);
        arg3.encode(this.width, 8);
        arg3.encode(this.height, 12);
    }
}

