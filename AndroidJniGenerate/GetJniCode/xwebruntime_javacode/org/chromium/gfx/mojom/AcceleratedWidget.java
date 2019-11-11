package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class AcceleratedWidget extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public long widget;

    static {
        AcceleratedWidget.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        AcceleratedWidget.DEFAULT_STRUCT_INFO = AcceleratedWidget.VERSION_ARRAY[0];
    }

    public AcceleratedWidget() {
        this(0);
    }

    private AcceleratedWidget(int arg2) {
        super(16, arg2);
    }

    public static AcceleratedWidget decode(Decoder arg4) {
        AcceleratedWidget v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new AcceleratedWidget(arg4.readAndValidateDataHeader(AcceleratedWidget.VERSION_ARRAY).elementsOrVersion);
            v1.widget = arg4.readLong(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static AcceleratedWidget deserialize(ByteBuffer arg2) {
        return AcceleratedWidget.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AcceleratedWidget deserialize(Message arg1) {
        return AcceleratedWidget.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(AcceleratedWidget.DEFAULT_STRUCT_INFO).encode(this.widget, 8);
    }
}

