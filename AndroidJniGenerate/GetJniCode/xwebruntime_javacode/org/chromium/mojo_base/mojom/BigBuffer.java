package org.chromium.mojo_base.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class BigBuffer extends Union {
    public final class Tag {
        public static final int Bytes = 0;
        public static final int SharedMemory = 1;

        public Tag() {
            super();
        }
    }

    private byte[] mBytes;
    private BigBufferSharedMemoryRegion mSharedMemory;

    static {
    }

    public BigBuffer() {
        super();
    }

    public static final BigBuffer decode(Decoder arg3, int arg4) {
        DataHeader v0 = arg3.readDataHeaderForUnion(arg4);
        if(v0.size == 0) {
            return null;
        }

        BigBuffer v1 = new BigBuffer();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mBytes = arg3.readBytes(arg4 + 8, 0, -1);
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mSharedMemory = BigBufferSharedMemoryRegion.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 1;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static BigBuffer deserialize(Message arg1) {
        return BigBuffer.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg4, int arg5) {
        arg4.encode(16, arg5);
        arg4.encode(this.mTag, arg5 + 4);
        switch(this.mTag) {
            case 0: {
                arg4.encode(this.mBytes, arg5 + 8, 0, -1);
                break;
            }
            case 1: {
                arg4.encode(this.mSharedMemory, arg5 + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    public BigBufferSharedMemoryRegion getSharedMemory() {
        return this.mSharedMemory;
    }

    public void setBytes(byte[] arg2) {
        this.mTag = 0;
        this.mBytes = arg2;
    }

    public void setSharedMemory(BigBufferSharedMemoryRegion arg2) {
        this.mTag = 1;
        this.mSharedMemory = arg2;
    }
}

