package org.chromium.service_manager.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RunningServiceInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int id;
    public Identity identity;
    public int pid;

    static {
        RunningServiceInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        RunningServiceInfo.DEFAULT_STRUCT_INFO = RunningServiceInfo.VERSION_ARRAY[0];
    }

    public RunningServiceInfo() {
        this(0);
    }

    private RunningServiceInfo(int arg2) {
        super(24, arg2);
    }

    public static RunningServiceInfo decode(Decoder arg3) {
        RunningServiceInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new RunningServiceInfo(arg3.readAndValidateDataHeader(RunningServiceInfo.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg3.readInt(8);
            v1.pid = arg3.readInt(12);
            v1.identity = Identity.decode(arg3.readPointer(16, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static RunningServiceInfo deserialize(ByteBuffer arg2) {
        return RunningServiceInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static RunningServiceInfo deserialize(Message arg1) {
        return RunningServiceInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(RunningServiceInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.id, 8);
        arg4.encode(this.pid, 12);
        arg4.encode(this.identity, 16, false);
    }
}

