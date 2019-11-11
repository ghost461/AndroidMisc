package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class BitstreamBuffer extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public int id;
    public String iv;
    public String keyId;
    public SharedBufferHandle memoryHandle;
    public long offset;
    public int size;
    public SubsampleEntry[] subsamples;
    public TimeDelta timestamp;

    static {
        BitstreamBuffer.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        BitstreamBuffer.DEFAULT_STRUCT_INFO = BitstreamBuffer.VERSION_ARRAY[0];
    }

    public BitstreamBuffer() {
        this(0);
    }

    private BitstreamBuffer(int arg2) {
        super(0x40, arg2);
        this.memoryHandle = InvalidHandle.INSTANCE;
    }

    public static BitstreamBuffer decode(Decoder arg8) {
        BitstreamBuffer v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new BitstreamBuffer(arg8.readAndValidateDataHeader(BitstreamBuffer.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.id = arg8.readInt(v0_1);
            v1.memoryHandle = arg8.readSharedBufferHandle(12, false);
            v1.size = arg8.readInt(16);
            v1.offset = arg8.readLong(24);
            v1.timestamp = TimeDelta.decode(arg8.readPointer(0x20, false));
            v1.keyId = arg8.readString(40, false);
            v1.iv = arg8.readString(0x30, false);
            Decoder v2 = arg8.readPointer(56, false);
            DataHeader v4 = v2.readDataHeaderForPointerArray(-1);
            v1.subsamples = new SubsampleEntry[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.subsamples[v5] = SubsampleEntry.decode(v2.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_53;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_53:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static BitstreamBuffer deserialize(ByteBuffer arg2) {
        return BitstreamBuffer.deserialize(new Message(arg2, new ArrayList()));
    }

    public static BitstreamBuffer deserialize(Message arg1) {
        return BitstreamBuffer.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(BitstreamBuffer.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.id, v1);
        arg6.encode(this.memoryHandle, 12, false);
        arg6.encode(this.size, 16);
        arg6.encode(this.offset, 24);
        arg6.encode(this.timestamp, 0x20, false);
        arg6.encode(this.keyId, 40, false);
        arg6.encode(this.iv, 0x30, false);
        int v3 = 56;
        if(this.subsamples == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.subsamples.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.subsamples.length; ++v0) {
                arg6.encode(this.subsamples[v0], v0 * 8 + v1, false);
            }
        }
    }
}

