package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.PointF;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MasteringMetadata extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public float luminanceMax;
    public float luminanceMin;
    public PointF primaryB;
    public PointF primaryG;
    public PointF primaryR;
    public PointF whitePoint;

    static {
        MasteringMetadata.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        MasteringMetadata.DEFAULT_STRUCT_INFO = MasteringMetadata.VERSION_ARRAY[0];
    }

    public MasteringMetadata() {
        this(0);
    }

    private MasteringMetadata(int arg2) {
        super(0x30, arg2);
    }

    public static MasteringMetadata decode(Decoder arg3) {
        MasteringMetadata v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new MasteringMetadata(arg3.readAndValidateDataHeader(MasteringMetadata.VERSION_ARRAY).elementsOrVersion);
            v1.primaryR = PointF.decode(arg3.readPointer(8, false));
            v1.primaryG = PointF.decode(arg3.readPointer(16, false));
            v1.primaryB = PointF.decode(arg3.readPointer(24, false));
            v1.whitePoint = PointF.decode(arg3.readPointer(0x20, false));
            v1.luminanceMax = arg3.readFloat(40);
            v1.luminanceMin = arg3.readFloat(44);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static MasteringMetadata deserialize(ByteBuffer arg2) {
        return MasteringMetadata.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MasteringMetadata deserialize(Message arg1) {
        return MasteringMetadata.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(MasteringMetadata.DEFAULT_STRUCT_INFO);
        arg4.encode(this.primaryR, 8, false);
        arg4.encode(this.primaryG, 16, false);
        arg4.encode(this.primaryB, 24, false);
        arg4.encode(this.whitePoint, 0x20, false);
        arg4.encode(this.luminanceMax, 40);
        arg4.encode(this.luminanceMin, 44);
    }
}

