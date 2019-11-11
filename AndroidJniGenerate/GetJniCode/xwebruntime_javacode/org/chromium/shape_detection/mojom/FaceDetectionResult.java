package org.chromium.shape_detection.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.RectF;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class FaceDetectionResult extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public RectF boundingBox;
    public Landmark[] landmarks;

    static {
        FaceDetectionResult.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        FaceDetectionResult.DEFAULT_STRUCT_INFO = FaceDetectionResult.VERSION_ARRAY[0];
    }

    public FaceDetectionResult() {
        this(0);
    }

    private FaceDetectionResult(int arg2) {
        super(24, arg2);
    }

    public static FaceDetectionResult decode(Decoder arg8) {
        FaceDetectionResult v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new FaceDetectionResult(arg8.readAndValidateDataHeader(FaceDetectionResult.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.boundingBox = RectF.decode(arg8.readPointer(v0_1, false));
            Decoder v3 = arg8.readPointer(16, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.landmarks = new Landmark[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.landmarks[v5] = Landmark.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_35;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_35:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static FaceDetectionResult deserialize(ByteBuffer arg2) {
        return FaceDetectionResult.deserialize(new Message(arg2, new ArrayList()));
    }

    public static FaceDetectionResult deserialize(Message arg1) {
        return FaceDetectionResult.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(FaceDetectionResult.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.boundingBox, v1, false);
        int v3 = 16;
        if(this.landmarks == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.landmarks.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.landmarks.length; ++v0) {
                arg6.encode(this.landmarks[v0], v0 * 8 + v1, false);
            }
        }
    }
}

