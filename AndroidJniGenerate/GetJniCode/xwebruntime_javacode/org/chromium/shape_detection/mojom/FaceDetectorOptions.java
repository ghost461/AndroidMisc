package org.chromium.shape_detection.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class FaceDetectorOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean fastMode;
    public int maxDetectedFaces;

    static {
        FaceDetectorOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        FaceDetectorOptions.DEFAULT_STRUCT_INFO = FaceDetectorOptions.VERSION_ARRAY[0];
    }

    public FaceDetectorOptions() {
        this(0);
    }

    private FaceDetectorOptions(int arg2) {
        super(16, arg2);
    }

    public static FaceDetectorOptions decode(Decoder arg3) {
        FaceDetectorOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new FaceDetectorOptions(arg3.readAndValidateDataHeader(FaceDetectorOptions.VERSION_ARRAY).elementsOrVersion);
            v1.maxDetectedFaces = arg3.readInt(8);
            v1.fastMode = arg3.readBoolean(12, 0);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static FaceDetectorOptions deserialize(ByteBuffer arg2) {
        return FaceDetectorOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static FaceDetectorOptions deserialize(Message arg1) {
        return FaceDetectorOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(FaceDetectorOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.maxDetectedFaces, 8);
        arg4.encode(this.fastMode, 12, 0);
    }
}

