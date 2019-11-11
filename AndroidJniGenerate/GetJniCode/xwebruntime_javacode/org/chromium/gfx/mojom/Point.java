package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Point extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int x;
    public int y;

    static {
        Point.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Point.DEFAULT_STRUCT_INFO = Point.VERSION_ARRAY[0];
    }

    public Point() {
        this(0);
    }

    private Point(int arg2) {
        super(16, arg2);
    }

    public static Point decode(Decoder arg2) {
        Point v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Point(arg2.readAndValidateDataHeader(Point.VERSION_ARRAY).elementsOrVersion);
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

    public static Point deserialize(ByteBuffer arg2) {
        return Point.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Point deserialize(Message arg1) {
        return Point.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(Point.DEFAULT_STRUCT_INFO);
        arg3.encode(this.x, 8);
        arg3.encode(this.y, 12);
    }
}

