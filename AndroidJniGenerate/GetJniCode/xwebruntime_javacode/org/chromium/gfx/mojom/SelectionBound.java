package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SelectionBound extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public PointF edgeBottom;
    public PointF edgeTop;
    public int type;
    public boolean visible;

    static {
        SelectionBound.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        SelectionBound.DEFAULT_STRUCT_INFO = SelectionBound.VERSION_ARRAY[0];
    }

    public SelectionBound() {
        this(0);
    }

    private SelectionBound(int arg2) {
        super(0x20, arg2);
    }

    public static SelectionBound decode(Decoder arg3) {
        SelectionBound v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SelectionBound(arg3.readAndValidateDataHeader(SelectionBound.VERSION_ARRAY).elementsOrVersion);
            v1.type = arg3.readInt(8);
            SelectionBoundType.validate(v1.type);
            v1.visible = arg3.readBoolean(12, 0);
            v1.edgeTop = PointF.decode(arg3.readPointer(16, false));
            v1.edgeBottom = PointF.decode(arg3.readPointer(24, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SelectionBound deserialize(ByteBuffer arg2) {
        return SelectionBound.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SelectionBound deserialize(Message arg1) {
        return SelectionBound.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SelectionBound.DEFAULT_STRUCT_INFO);
        arg4.encode(this.type, 8);
        arg4.encode(this.visible, 12, 0);
        arg4.encode(this.edgeTop, 16, false);
        arg4.encode(this.edgeBottom, 24, false);
    }
}

