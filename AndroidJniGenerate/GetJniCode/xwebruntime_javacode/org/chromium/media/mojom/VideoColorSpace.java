package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoColorSpace extends Struct {
    public final class MatrixId {
        private static final boolean IS_EXTENSIBLE = false;

        private MatrixId() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(MatrixId.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public final class PrimaryId {
        private static final boolean IS_EXTENSIBLE = false;

        private PrimaryId() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(PrimaryId.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public final class RangeId {
        private static final boolean IS_EXTENSIBLE = false;

        private RangeId() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(RangeId.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public final class TransferId {
        private static final boolean IS_EXTENSIBLE = false;

        private TransferId() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(TransferId.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int matrix;
    public int primaries;
    public int range;
    public int transfer;

    static {
        VideoColorSpace.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        VideoColorSpace.DEFAULT_STRUCT_INFO = VideoColorSpace.VERSION_ARRAY[0];
    }

    public VideoColorSpace() {
        this(0);
    }

    private VideoColorSpace(int arg2) {
        super(24, arg2);
    }

    public static VideoColorSpace decode(Decoder arg2) {
        VideoColorSpace v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new VideoColorSpace(arg2.readAndValidateDataHeader(VideoColorSpace.VERSION_ARRAY).elementsOrVersion);
            v1.primaries = arg2.readInt(8);
            PrimaryId.validate(v1.primaries);
            v1.transfer = arg2.readInt(12);
            TransferId.validate(v1.transfer);
            v1.matrix = arg2.readInt(16);
            MatrixId.validate(v1.matrix);
            v1.range = arg2.readInt(20);
            RangeId.validate(v1.range);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static VideoColorSpace deserialize(ByteBuffer arg2) {
        return VideoColorSpace.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoColorSpace deserialize(Message arg1) {
        return VideoColorSpace.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(VideoColorSpace.DEFAULT_STRUCT_INFO);
        arg3.encode(this.primaries, 8);
        arg3.encode(this.transfer, 12);
        arg3.encode(this.matrix, 16);
        arg3.encode(this.range, 20);
    }
}

