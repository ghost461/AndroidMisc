package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RunResponseMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public RunOutput output;

    static {
        RunResponseMessageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        RunResponseMessageParams.DEFAULT_STRUCT_INFO = RunResponseMessageParams.VERSION_ARRAY[0];
    }

    public RunResponseMessageParams() {
        this(0);
    }

    private RunResponseMessageParams(int arg2) {
        super(24, arg2);
    }

    public static RunResponseMessageParams decode(Decoder arg2) {
        RunResponseMessageParams v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RunResponseMessageParams(arg2.readAndValidateDataHeader(RunResponseMessageParams.VERSION_ARRAY).elementsOrVersion);
            v1.output = RunOutput.decode(arg2, 8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RunResponseMessageParams deserialize(Message arg1) {
        return RunResponseMessageParams.decode(new Decoder(arg1));
    }

    public static RunResponseMessageParams deserialize(ByteBuffer arg2) {
        return RunResponseMessageParams.deserialize(new Message(arg2, new ArrayList()));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(RunResponseMessageParams.DEFAULT_STRUCT_INFO).encode(this.output, 8, true);
    }
}

