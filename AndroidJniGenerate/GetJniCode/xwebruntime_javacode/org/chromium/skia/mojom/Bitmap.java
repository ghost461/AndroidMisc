package org.chromium.skia.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Bitmap extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public int alphaType;
    public int colorType;
    public int height;
    public byte[] pixelData;
    public int profileType;
    public long rowBytes;
    public int width;

    static {
        Bitmap.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        Bitmap.DEFAULT_STRUCT_INFO = Bitmap.VERSION_ARRAY[0];
    }

    public Bitmap() {
        this(0);
    }

    private Bitmap(int arg2) {
        super(0x30, arg2);
    }

    public static Bitmap decode(Decoder arg4) {
        Bitmap v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new Bitmap(arg4.readAndValidateDataHeader(Bitmap.VERSION_ARRAY).elementsOrVersion);
            v1.colorType = arg4.readInt(8);
            ColorType.validate(v1.colorType);
            v1.alphaType = arg4.readInt(12);
            AlphaType.validate(v1.alphaType);
            v1.profileType = arg4.readInt(16);
            ColorProfileType.validate(v1.profileType);
            v1.width = arg4.readInt(20);
            v1.height = arg4.readInt(24);
            v1.rowBytes = arg4.readLong(0x20);
            v1.pixelData = arg4.readBytes(40, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static Bitmap deserialize(ByteBuffer arg2) {
        return Bitmap.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Bitmap deserialize(Message arg1) {
        return Bitmap.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(Bitmap.DEFAULT_STRUCT_INFO);
        arg5.encode(this.colorType, 8);
        arg5.encode(this.alphaType, 12);
        arg5.encode(this.profileType, 16);
        arg5.encode(this.width, 20);
        arg5.encode(this.height, 24);
        arg5.encode(this.rowBytes, 0x20);
        arg5.encode(this.pixelData, 40, 0, -1);
    }
}

