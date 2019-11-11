package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class OverlayInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        OverlayInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        OverlayInfo.DEFAULT_STRUCT_INFO = OverlayInfo.VERSION_ARRAY[0];
    }

    public OverlayInfo() {
        this(0);
    }

    private OverlayInfo(int arg2) {
        super(8, arg2);
    }

    public static OverlayInfo decode(Decoder arg2) {
        OverlayInfo v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new OverlayInfo(arg2.readAndValidateDataHeader(OverlayInfo.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static OverlayInfo deserialize(ByteBuffer arg2) {
        return OverlayInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static OverlayInfo deserialize(Message arg1) {
        return OverlayInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(OverlayInfo.DEFAULT_STRUCT_INFO);
    }
}

