package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PredictionFeatures extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int framesPerSec;
    public int profile;
    public Size videoSize;

    static {
        PredictionFeatures.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        PredictionFeatures.DEFAULT_STRUCT_INFO = PredictionFeatures.VERSION_ARRAY[0];
    }

    public PredictionFeatures() {
        this(0);
    }

    private PredictionFeatures(int arg2) {
        super(24, arg2);
        this.framesPerSec = 0;
    }

    public static PredictionFeatures decode(Decoder arg3) {
        PredictionFeatures v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PredictionFeatures(arg3.readAndValidateDataHeader(PredictionFeatures.VERSION_ARRAY).elementsOrVersion);
            v1.profile = arg3.readInt(8);
            VideoCodecProfile.validate(v1.profile);
            v1.framesPerSec = arg3.readInt(12);
            v1.videoSize = Size.decode(arg3.readPointer(16, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PredictionFeatures deserialize(ByteBuffer arg2) {
        return PredictionFeatures.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PredictionFeatures deserialize(Message arg1) {
        return PredictionFeatures.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PredictionFeatures.DEFAULT_STRUCT_INFO);
        arg4.encode(this.profile, 8);
        arg4.encode(this.framesPerSec, 12);
        arg4.encode(this.videoSize, 16, false);
    }
}

