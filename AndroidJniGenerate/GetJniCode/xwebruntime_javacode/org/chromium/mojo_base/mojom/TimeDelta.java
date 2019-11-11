package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class TimeDelta extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public long microseconds;

    static {
        TimeDelta.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        TimeDelta.DEFAULT_STRUCT_INFO = TimeDelta.VERSION_ARRAY[0];
    }

    public TimeDelta() {
        this(0);
    }

    private TimeDelta(int arg2) {
        super(16, arg2);
    }

    public static TimeDelta decode(Decoder arg4) {
        TimeDelta v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new TimeDelta(arg4.readAndValidateDataHeader(TimeDelta.VERSION_ARRAY).elementsOrVersion);
            v1.microseconds = arg4.readLong(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static TimeDelta deserialize(ByteBuffer arg2) {
        return TimeDelta.deserialize(new Message(arg2, new ArrayList()));
    }

    public static TimeDelta deserialize(Message arg1) {
        return TimeDelta.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(TimeDelta.DEFAULT_STRUCT_INFO).encode(this.microseconds, 8);
    }
}

