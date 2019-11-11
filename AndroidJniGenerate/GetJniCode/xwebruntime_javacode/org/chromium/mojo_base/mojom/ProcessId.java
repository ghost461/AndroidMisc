package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ProcessId extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int pid;

    static {
        ProcessId.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ProcessId.DEFAULT_STRUCT_INFO = ProcessId.VERSION_ARRAY[0];
    }

    public ProcessId() {
        this(0);
    }

    private ProcessId(int arg2) {
        super(16, arg2);
    }

    public static ProcessId decode(Decoder arg2) {
        ProcessId v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new ProcessId(arg2.readAndValidateDataHeader(ProcessId.VERSION_ARRAY).elementsOrVersion);
            v1.pid = arg2.readInt(8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static ProcessId deserialize(ByteBuffer arg2) {
        return ProcessId.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ProcessId deserialize(Message arg1) {
        return ProcessId.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(ProcessId.DEFAULT_STRUCT_INFO).encode(this.pid, 8);
    }
}

