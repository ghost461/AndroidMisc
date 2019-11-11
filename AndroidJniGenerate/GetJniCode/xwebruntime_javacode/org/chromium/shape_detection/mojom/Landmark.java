package org.chromium.shape_detection.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.PointF;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Landmark extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public PointF location;
    public int type;

    static {
        Landmark.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        Landmark.DEFAULT_STRUCT_INFO = Landmark.VERSION_ARRAY[0];
    }

    public Landmark() {
        this(0);
    }

    private Landmark(int arg2) {
        super(24, arg2);
    }

    public static Landmark decode(Decoder arg3) {
        Landmark v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new Landmark(arg3.readAndValidateDataHeader(Landmark.VERSION_ARRAY).elementsOrVersion);
            v1.location = PointF.decode(arg3.readPointer(8, false));
            v1.type = arg3.readInt(16);
            LandmarkType.validate(v1.type);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static Landmark deserialize(ByteBuffer arg2) {
        return Landmark.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Landmark deserialize(Message arg1) {
        return Landmark.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(Landmark.DEFAULT_STRUCT_INFO);
        arg4.encode(this.location, 8, false);
        arg4.encode(this.type, 16);
    }
}

