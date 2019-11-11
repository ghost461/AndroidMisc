package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CaLayerParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public CaLayerContent content;
    public boolean isEmpty;
    public Size pixelSize;
    public float scaleFactor;

    static {
        CaLayerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        CaLayerParams.DEFAULT_STRUCT_INFO = CaLayerParams.VERSION_ARRAY[0];
    }

    public CaLayerParams() {
        this(0);
    }

    private CaLayerParams(int arg2) {
        super(40, arg2);
    }

    public static CaLayerParams decode(Decoder arg3) {
        CaLayerParams v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new CaLayerParams(arg3.readAndValidateDataHeader(CaLayerParams.VERSION_ARRAY).elementsOrVersion);
            v1.isEmpty = arg3.readBoolean(8, 0);
            v1.scaleFactor = arg3.readFloat(12);
            v1.content = CaLayerContent.decode(arg3, 16);
            v1.pixelSize = Size.decode(arg3.readPointer(0x20, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static CaLayerParams deserialize(ByteBuffer arg2) {
        return CaLayerParams.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CaLayerParams deserialize(Message arg1) {
        return CaLayerParams.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(CaLayerParams.DEFAULT_STRUCT_INFO);
        arg4.encode(this.isEmpty, 8, 0);
        arg4.encode(this.scaleFactor, 12);
        arg4.encode(this.content, 16, false);
        arg4.encode(this.pixelSize, 0x20, false);
    }
}

