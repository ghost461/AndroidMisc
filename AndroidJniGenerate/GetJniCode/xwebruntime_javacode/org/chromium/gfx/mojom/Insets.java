package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Insets extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int bottom;
    public int left;
    public int right;
    public int top;

    static {
        Insets.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        Insets.DEFAULT_STRUCT_INFO = Insets.VERSION_ARRAY[0];
    }

    public Insets() {
        this(0);
    }

    private Insets(int arg2) {
        super(24, arg2);
    }

    public static Insets decode(Decoder arg2) {
        Insets v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new Insets(arg2.readAndValidateDataHeader(Insets.VERSION_ARRAY).elementsOrVersion);
            v1.top = arg2.readInt(8);
            v1.left = arg2.readInt(12);
            v1.bottom = arg2.readInt(16);
            v1.right = arg2.readInt(20);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static Insets deserialize(ByteBuffer arg2) {
        return Insets.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Insets deserialize(Message arg1) {
        return Insets.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(Insets.DEFAULT_STRUCT_INFO);
        arg3.encode(this.top, 8);
        arg3.encode(this.left, 12);
        arg3.encode(this.bottom, 16);
        arg3.encode(this.right, 20);
    }
}

