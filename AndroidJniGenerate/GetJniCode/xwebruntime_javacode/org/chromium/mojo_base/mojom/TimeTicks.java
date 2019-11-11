package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class TimeTicks extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public long internalValue;

    static {
        TimeTicks.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        TimeTicks.DEFAULT_STRUCT_INFO = TimeTicks.VERSION_ARRAY[0];
    }

    public TimeTicks() {
        this(0);
    }

    private TimeTicks(int arg2) {
        super(16, arg2);
    }

    public static TimeTicks decode(Decoder arg4) {
        TimeTicks v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new TimeTicks(arg4.readAndValidateDataHeader(TimeTicks.VERSION_ARRAY).elementsOrVersion);
            v1.internalValue = arg4.readLong(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static TimeTicks deserialize(ByteBuffer arg2) {
        return TimeTicks.deserialize(new Message(arg2, new ArrayList()));
    }

    public static TimeTicks deserialize(Message arg1) {
        return TimeTicks.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(TimeTicks.DEFAULT_STRUCT_INFO).encode(this.internalValue, 8);
    }
}

