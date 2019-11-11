package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SerialHostControlSignals extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean dtr;
    public boolean hasDtr;
    public boolean hasRts;
    public boolean rts;

    static {
        SerialHostControlSignals.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SerialHostControlSignals.DEFAULT_STRUCT_INFO = SerialHostControlSignals.VERSION_ARRAY[0];
    }

    public SerialHostControlSignals() {
        this(0);
    }

    private SerialHostControlSignals(int arg2) {
        super(16, arg2);
        this.hasDtr = false;
        this.hasRts = false;
    }

    public static SerialHostControlSignals decode(Decoder arg3) {
        SerialHostControlSignals v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SerialHostControlSignals(arg3.readAndValidateDataHeader(SerialHostControlSignals.VERSION_ARRAY).elementsOrVersion);
            v1.dtr = arg3.readBoolean(8, 0);
            v1.hasDtr = arg3.readBoolean(8, 1);
            v1.rts = arg3.readBoolean(8, 2);
            v1.hasRts = arg3.readBoolean(8, 3);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SerialHostControlSignals deserialize(ByteBuffer arg2) {
        return SerialHostControlSignals.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerialHostControlSignals deserialize(Message arg1) {
        return SerialHostControlSignals.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SerialHostControlSignals.DEFAULT_STRUCT_INFO);
        arg4.encode(this.dtr, 8, 0);
        arg4.encode(this.hasDtr, 8, 1);
        arg4.encode(this.rts, 8, 2);
        arg4.encode(this.hasRts, 8, 3);
    }
}

