package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NativePixmapPlane extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public long modifier;
    public int offset;
    public long size;
    public int stride;

    static {
        NativePixmapPlane.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        NativePixmapPlane.DEFAULT_STRUCT_INFO = NativePixmapPlane.VERSION_ARRAY[0];
    }

    public NativePixmapPlane() {
        this(0);
    }

    private NativePixmapPlane(int arg2) {
        super(0x20, arg2);
    }

    public static NativePixmapPlane decode(Decoder arg4) {
        NativePixmapPlane v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new NativePixmapPlane(arg4.readAndValidateDataHeader(NativePixmapPlane.VERSION_ARRAY).elementsOrVersion);
            v1.stride = arg4.readInt(8);
            v1.offset = arg4.readInt(12);
            v1.size = arg4.readLong(16);
            v1.modifier = arg4.readLong(24);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static NativePixmapPlane deserialize(ByteBuffer arg2) {
        return NativePixmapPlane.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NativePixmapPlane deserialize(Message arg1) {
        return NativePixmapPlane.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(NativePixmapPlane.DEFAULT_STRUCT_INFO);
        arg4.encode(this.stride, 8);
        arg4.encode(this.offset, 12);
        arg4.encode(this.size, 16);
        arg4.encode(this.modifier, 24);
    }
}

