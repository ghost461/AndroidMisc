package org.chromium.mojo.native_types;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.UntypedHandle;

public final class SerializedHandle extends Struct {
    public final class Type {
        public static final int FUCHSIA_HANDLE = 4;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int MACH_PORT = 3;
        public static final int MOJO_HANDLE = 0;
        public static final int PLATFORM_FILE = 1;
        public static final int WIN_HANDLE = 2;

        private Type() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(Type.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public UntypedHandle theHandle;
    public int type;

    static {
        SerializedHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SerializedHandle.DEFAULT_STRUCT_INFO = SerializedHandle.VERSION_ARRAY[0];
    }

    public SerializedHandle() {
        this(0);
    }

    private SerializedHandle(int arg2) {
        super(16, arg2);
        this.theHandle = InvalidHandle.INSTANCE;
    }

    public static SerializedHandle decode(Decoder arg3) {
        SerializedHandle v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SerializedHandle(arg3.readAndValidateDataHeader(SerializedHandle.VERSION_ARRAY).elementsOrVersion);
            v1.theHandle = arg3.readUntypedHandle(8, false);
            v1.type = arg3.readInt(12);
            Type.validate(v1.type);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SerializedHandle deserialize(ByteBuffer arg2) {
        return SerializedHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerializedHandle deserialize(Message arg1) {
        return SerializedHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SerializedHandle.DEFAULT_STRUCT_INFO);
        arg4.encode(this.theHandle, 8, false);
        arg4.encode(this.type, 12);
    }
}

