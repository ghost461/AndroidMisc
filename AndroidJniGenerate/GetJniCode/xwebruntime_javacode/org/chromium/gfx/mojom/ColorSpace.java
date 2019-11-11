package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ColorSpace extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        ColorSpace.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        ColorSpace.DEFAULT_STRUCT_INFO = ColorSpace.VERSION_ARRAY[0];
    }

    public ColorSpace() {
        this(0);
    }

    private ColorSpace(int arg2) {
        super(8, arg2);
    }

    public static ColorSpace decode(Decoder arg2) {
        ColorSpace v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new ColorSpace(arg2.readAndValidateDataHeader(ColorSpace.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static ColorSpace deserialize(ByteBuffer arg2) {
        return ColorSpace.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ColorSpace deserialize(Message arg1) {
        return ColorSpace.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(ColorSpace.DEFAULT_STRUCT_INFO);
    }
}

