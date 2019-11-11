package org.chromium.shape_detection.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.PointF;
import org.chromium.gfx.mojom.RectF;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class TextDetectionResult extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public RectF boundingBox;
    public PointF[] cornerPoints;
    public String rawValue;

    static {
        TextDetectionResult.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        TextDetectionResult.DEFAULT_STRUCT_INFO = TextDetectionResult.VERSION_ARRAY[0];
    }

    public TextDetectionResult() {
        this(0);
    }

    private TextDetectionResult(int arg2) {
        super(0x20, arg2);
    }

    public static TextDetectionResult decode(Decoder arg8) {
        TextDetectionResult v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new TextDetectionResult(arg8.readAndValidateDataHeader(TextDetectionResult.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.rawValue = arg8.readString(v0_1, false);
            v1.boundingBox = RectF.decode(arg8.readPointer(16, false));
            Decoder v3 = arg8.readPointer(24, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.cornerPoints = new PointF[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.cornerPoints[v5] = PointF.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_38;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_38:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static TextDetectionResult deserialize(ByteBuffer arg2) {
        return TextDetectionResult.deserialize(new Message(arg2, new ArrayList()));
    }

    public static TextDetectionResult deserialize(Message arg1) {
        return TextDetectionResult.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(TextDetectionResult.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.rawValue, v1, false);
        arg6.encode(this.boundingBox, 16, false);
        int v3 = 24;
        if(this.cornerPoints == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.cornerPoints.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.cornerPoints.length; ++v0) {
                arg6.encode(this.cornerPoints[v0], v0 * 8 + v1, false);
            }
        }
    }
}

