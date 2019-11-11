package org.chromium.mojo.native_types;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NativeStruct extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] data;
    public SerializedHandle[] handles;

    static {
        NativeStruct.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        NativeStruct.DEFAULT_STRUCT_INFO = NativeStruct.VERSION_ARRAY[0];
    }

    public NativeStruct() {
        this(0);
    }

    private NativeStruct(int arg2) {
        super(24, arg2);
    }

    public static NativeStruct decode(Decoder arg8) {
        NativeStruct v2;
        NativeStruct v0 = null;
        if(arg8 == null) {
            return v0;
        }

        arg8.increaseStackDepth();
        try {
            v2 = new NativeStruct(arg8.readAndValidateDataHeader(NativeStruct.VERSION_ARRAY).elementsOrVersion);
            int v1 = -1;
            int v4 = 8;
            v2.data = arg8.readBytes(v4, 0, v1);
            Decoder v5 = arg8.readPointer(16, true);
            if(v5 == null) {
                v2.handles = ((SerializedHandle[])v0);
            }
            else {
                DataHeader v0_2 = v5.readDataHeaderForPointerArray(v1);
                v2.handles = new SerializedHandle[v0_2.elementsOrVersion];
                for(v1 = 0; v1 < v0_2.elementsOrVersion; ++v1) {
                    v2.handles[v1] = SerializedHandle.decode(v5.readPointer(v1 * 8 + v4, false));
                }
            }
        }
        catch(Throwable v0_1) {
            arg8.decreaseStackDepth();
            throw v0_1;
        }

        arg8.decreaseStackDepth();
        return v2;
    }

    public static NativeStruct deserialize(ByteBuffer arg2) {
        return NativeStruct.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NativeStruct deserialize(Message arg1) {
        return NativeStruct.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(NativeStruct.DEFAULT_STRUCT_INFO);
        int v1 = -1;
        int v3 = 8;
        arg6.encode(this.data, v3, 0, v1);
        int v4 = 16;
        if(this.handles == null) {
            arg6.encodeNullPointer(v4, true);
        }
        else {
            arg6 = arg6.encodePointerArray(this.handles.length, v4, v1);
            int v0;
            for(v0 = 0; v0 < this.handles.length; ++v0) {
                arg6.encode(this.handles[v0], v0 * 8 + v3, false);
            }
        }
    }
}

