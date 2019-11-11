package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CdmPromiseResult extends Struct {
    public final class Exception {
        private static final boolean IS_EXTENSIBLE = false;

        private Exception() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(Exception.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String errorMessage;
    public int exception;
    public boolean success;
    public int systemCode;

    static {
        CdmPromiseResult.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        CdmPromiseResult.DEFAULT_STRUCT_INFO = CdmPromiseResult.VERSION_ARRAY[0];
    }

    public CdmPromiseResult() {
        this(0);
    }

    private CdmPromiseResult(int arg2) {
        super(0x20, arg2);
    }

    public static CdmPromiseResult decode(Decoder arg3) {
        CdmPromiseResult v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new CdmPromiseResult(arg3.readAndValidateDataHeader(CdmPromiseResult.VERSION_ARRAY).elementsOrVersion);
            v1.success = arg3.readBoolean(8, 0);
            v1.exception = arg3.readInt(12);
            Exception.validate(v1.exception);
            v1.systemCode = arg3.readInt(16);
            v1.errorMessage = arg3.readString(24, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static CdmPromiseResult deserialize(ByteBuffer arg2) {
        return CdmPromiseResult.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CdmPromiseResult deserialize(Message arg1) {
        return CdmPromiseResult.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(CdmPromiseResult.DEFAULT_STRUCT_INFO);
        arg4.encode(this.success, 8, 0);
        arg4.encode(this.exception, 12);
        arg4.encode(this.systemCode, 16);
        arg4.encode(this.errorMessage, 24, false);
    }
}

