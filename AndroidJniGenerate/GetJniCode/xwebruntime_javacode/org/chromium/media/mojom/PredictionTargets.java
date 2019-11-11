package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PredictionTargets extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int framesDecoded;
    public int framesDecodedPowerEfficient;
    public int framesDropped;

    static {
        PredictionTargets.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        PredictionTargets.DEFAULT_STRUCT_INFO = PredictionTargets.VERSION_ARRAY[0];
    }

    public PredictionTargets() {
        this(0);
    }

    private PredictionTargets(int arg2) {
        super(24, arg2);
        this.framesDecoded = 0;
        this.framesDropped = 0;
        this.framesDecodedPowerEfficient = 0;
    }

    public static PredictionTargets decode(Decoder arg2) {
        PredictionTargets v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new PredictionTargets(arg2.readAndValidateDataHeader(PredictionTargets.VERSION_ARRAY).elementsOrVersion);
            v1.framesDecoded = arg2.readInt(8);
            v1.framesDropped = arg2.readInt(12);
            v1.framesDecodedPowerEfficient = arg2.readInt(16);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static PredictionTargets deserialize(ByteBuffer arg2) {
        return PredictionTargets.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PredictionTargets deserialize(Message arg1) {
        return PredictionTargets.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(PredictionTargets.DEFAULT_STRUCT_INFO);
        arg3.encode(this.framesDecoded, 8);
        arg3.encode(this.framesDropped, 12);
        arg3.encode(this.framesDecodedPowerEfficient, 16);
    }
}

