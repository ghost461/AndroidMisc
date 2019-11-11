package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.UntypedHandle;

public final class NativePixmapHandle extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public UntypedHandle[] fds;
    public NativePixmapPlane[] planes;

    static {
        NativePixmapHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        NativePixmapHandle.DEFAULT_STRUCT_INFO = NativePixmapHandle.VERSION_ARRAY[0];
    }

    public NativePixmapHandle() {
        this(0);
    }

    private NativePixmapHandle(int arg2) {
        super(24, arg2);
    }

    public static NativePixmapHandle decode(Decoder arg8) {
        NativePixmapHandle v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new NativePixmapHandle(arg8.readAndValidateDataHeader(NativePixmapHandle.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            v1.fds = arg8.readUntypedHandles(v2, 0, -1);
            Decoder v4 = arg8.readPointer(16, false);
            DataHeader v0_1 = v4.readDataHeaderForPointerArray(-1);
            v1.planes = new NativePixmapPlane[v0_1.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v0_1.elementsOrVersion; ++v5) {
                v1.planes[v5] = NativePixmapPlane.decode(v4.readPointer(v5 * 8 + v2, false));
            }
        }
        catch(Throwable v0) {
            goto label_34;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_34:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static NativePixmapHandle deserialize(ByteBuffer arg2) {
        return NativePixmapHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NativePixmapHandle deserialize(Message arg1) {
        return NativePixmapHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(NativePixmapHandle.DEFAULT_STRUCT_INFO);
        int v1 = -1;
        int v2 = 8;
        arg6.encode(this.fds, v2, 0, v1);
        int v4 = 16;
        if(this.planes == null) {
            arg6.encodeNullPointer(v4, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.planes.length, v4, v1);
            int v0;
            for(v0 = 0; v0 < this.planes.length; ++v0) {
                arg6.encode(this.planes[v0], v0 * 8 + v2, false);
            }
        }
    }
}

