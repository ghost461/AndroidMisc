package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RemoteInvocationResult extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public int error;
    public RemoteInvocationResultValue value;

    static {
        RemoteInvocationResult.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        RemoteInvocationResult.DEFAULT_STRUCT_INFO = RemoteInvocationResult.VERSION_ARRAY[0];
    }

    public RemoteInvocationResult() {
        this(0);
    }

    private RemoteInvocationResult(int arg2) {
        super(0x20, arg2);
        this.error = 0;
    }

    public static RemoteInvocationResult decode(Decoder arg2) {
        RemoteInvocationResult v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RemoteInvocationResult(arg2.readAndValidateDataHeader(RemoteInvocationResult.VERSION_ARRAY).elementsOrVersion);
            v1.error = arg2.readInt(8);
            RemoteInvocationError.validate(v1.error);
            v1.value = RemoteInvocationResultValue.decode(arg2, 16);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RemoteInvocationResult deserialize(ByteBuffer arg2) {
        return RemoteInvocationResult.deserialize(new Message(arg2, new ArrayList()));
    }

    public static RemoteInvocationResult deserialize(Message arg1) {
        return RemoteInvocationResult.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(RemoteInvocationResult.DEFAULT_STRUCT_INFO);
        arg4.encode(this.error, 8);
        arg4.encode(this.value, 16, true);
    }
}

