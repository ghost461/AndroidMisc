package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Rect extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int height;
    public int width;
    public int x;
    public int y;

    static {
        Rect.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        Rect.DEFAULT_STRUCT_INFO = Rect.VERSION_ARRAY[0];
    }

    public Rect() {
        this(0);
    }

    private Rect(int arg2) {
        super(24, arg2);
    }

    public static Rect decode(Decoder arg2) {
        Rect v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Rect(arg2.readAndValidateDataHeader(Rect.VERSION_ARRAY).elementsOrVersion);
            v1.x = arg2.readInt(8);
            v1.y = arg2.readInt(12);
            v1.width = arg2.readInt(16);
            v1.height = arg2.readInt(20);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static Rect deserialize(ByteBuffer arg2) {
        return Rect.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Rect deserialize(Message arg1) {
        return Rect.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(Rect.DEFAULT_STRUCT_INFO);
        arg3.encode(this.x, 8);
        arg3.encode(this.y, 12);
        arg3.encode(this.width, 16);
        arg3.encode(this.height, 20);
    }
}

