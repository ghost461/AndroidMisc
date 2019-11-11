package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Time extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public long internalValue;

    static {
        Time.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Time.DEFAULT_STRUCT_INFO = Time.VERSION_ARRAY[0];
    }

    public Time() {
        this(0);
    }

    private Time(int arg2) {
        super(16, arg2);
    }

    public static Time decode(Decoder arg4) {
        Time v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new Time(arg4.readAndValidateDataHeader(Time.VERSION_ARRAY).elementsOrVersion);
            v1.internalValue = arg4.readLong(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static Time deserialize(ByteBuffer arg2) {
        return Time.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Time deserialize(Message arg1) {
        return Time.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(Time.DEFAULT_STRUCT_INFO).encode(this.internalValue, 8);
    }
}

