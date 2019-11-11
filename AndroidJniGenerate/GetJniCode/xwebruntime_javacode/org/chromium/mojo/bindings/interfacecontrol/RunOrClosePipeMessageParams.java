package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RunOrClosePipeMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public RunOrClosePipeInput input;

    static {
        RunOrClosePipeMessageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        RunOrClosePipeMessageParams.DEFAULT_STRUCT_INFO = RunOrClosePipeMessageParams.VERSION_ARRAY[0];
    }

    public RunOrClosePipeMessageParams() {
        this(0);
    }

    private RunOrClosePipeMessageParams(int arg2) {
        super(24, arg2);
    }

    public static RunOrClosePipeMessageParams decode(Decoder arg2) {
        RunOrClosePipeMessageParams v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RunOrClosePipeMessageParams(arg2.readAndValidateDataHeader(RunOrClosePipeMessageParams.VERSION_ARRAY).elementsOrVersion);
            v1.input = RunOrClosePipeInput.decode(arg2, 8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RunOrClosePipeMessageParams deserialize(Message arg1) {
        return RunOrClosePipeMessageParams.decode(new Decoder(arg1));
    }

    public static RunOrClosePipeMessageParams deserialize(ByteBuffer arg2) {
        return RunOrClosePipeMessageParams.deserialize(new Message(arg2, new ArrayList()));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(RunOrClosePipeMessageParams.DEFAULT_STRUCT_INFO).encode(this.input, 8, false);
    }
}

