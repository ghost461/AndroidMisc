package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PlatformSharedMemoryRegion extends Struct {
    public final class Mode {
        private static final boolean IS_EXTENSIBLE = false;
        public static final int READ_ONLY = 0;
        public static final int UNSAFE = 2;
        public static final int WRITABLE = 1;

        private Mode() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: 
                case 2: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(Mode.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public UnguessableToken guid;
    public int mode;
    public PlatformSharedMemoryHandle platformHandle;
    public long size;

    static {
        PlatformSharedMemoryRegion.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        PlatformSharedMemoryRegion.DEFAULT_STRUCT_INFO = PlatformSharedMemoryRegion.VERSION_ARRAY[0];
    }

    public PlatformSharedMemoryRegion() {
        this(0);
    }

    private PlatformSharedMemoryRegion(int arg2) {
        super(40, arg2);
    }

    public static PlatformSharedMemoryRegion decode(Decoder arg5) {
        PlatformSharedMemoryRegion v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new PlatformSharedMemoryRegion(arg5.readAndValidateDataHeader(PlatformSharedMemoryRegion.VERSION_ARRAY).elementsOrVersion);
            v1.platformHandle = PlatformSharedMemoryHandle.decode(arg5.readPointer(8, false));
            v1.mode = arg5.readInt(16);
            Mode.validate(v1.mode);
            v1.size = arg5.readLong(24);
            v1.guid = UnguessableToken.decode(arg5.readPointer(0x20, false));
        }
        catch(Throwable v0) {
            arg5.decreaseStackDepth();
            throw v0;
        }

        arg5.decreaseStackDepth();
        return v1;
    }

    public static PlatformSharedMemoryRegion deserialize(ByteBuffer arg2) {
        return PlatformSharedMemoryRegion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PlatformSharedMemoryRegion deserialize(Message arg1) {
        return PlatformSharedMemoryRegion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(PlatformSharedMemoryRegion.DEFAULT_STRUCT_INFO);
        arg5.encode(this.platformHandle, 8, false);
        arg5.encode(this.mode, 16);
        arg5.encode(this.size, 24);
        arg5.encode(this.guid, 0x20, false);
    }
}

