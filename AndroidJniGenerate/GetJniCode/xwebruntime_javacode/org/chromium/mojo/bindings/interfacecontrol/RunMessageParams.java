package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RunMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public RunInput input;

    static {
        RunMessageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        RunMessageParams.DEFAULT_STRUCT_INFO = RunMessageParams.VERSION_ARRAY[0];
    }

    public RunMessageParams() {
        this(0);
    }

    private RunMessageParams(int arg2) {
        super(24, arg2);
    }

    public static RunMessageParams decode(Decoder arg2) {
        RunMessageParams v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RunMessageParams(arg2.readAndValidateDataHeader(RunMessageParams.VERSION_ARRAY).elementsOrVersion);
            v1.input = RunInput.decode(arg2, 8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RunMessageParams deserialize(Message arg1) {
        return RunMessageParams.decode(new Decoder(arg1));
    }

    public static RunMessageParams deserialize(ByteBuffer arg2) {
        return RunMessageParams.deserialize(new Message(arg2, new ArrayList()));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(RunMessageParams.DEFAULT_STRUCT_INFO).encode(this.input, 8, false);
    }
}

