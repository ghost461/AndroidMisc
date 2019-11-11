package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Point;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UnhandledTapInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int elementTextRunLength;
    public int fontSizeInPixels;
    public Point tappedPositionInViewport;

    static {
        UnhandledTapInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        UnhandledTapInfo.DEFAULT_STRUCT_INFO = UnhandledTapInfo.VERSION_ARRAY[0];
    }

    public UnhandledTapInfo() {
        this(0);
    }

    private UnhandledTapInfo(int arg2) {
        super(24, arg2);
        this.fontSizeInPixels = 0;
        this.elementTextRunLength = 0;
    }

    public static UnhandledTapInfo decode(Decoder arg3) {
        UnhandledTapInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new UnhandledTapInfo(arg3.readAndValidateDataHeader(UnhandledTapInfo.VERSION_ARRAY).elementsOrVersion);
            v1.tappedPositionInViewport = Point.decode(arg3.readPointer(8, false));
            v1.fontSizeInPixels = arg3.readInt(16);
            v1.elementTextRunLength = arg3.readInt(20);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static UnhandledTapInfo deserialize(ByteBuffer arg2) {
        return UnhandledTapInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UnhandledTapInfo deserialize(Message arg1) {
        return UnhandledTapInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(UnhandledTapInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.tappedPositionInViewport, 8, false);
        arg4.encode(this.fontSizeInPixels, 16);
        arg4.encode(this.elementTextRunLength, 20);
    }
}

