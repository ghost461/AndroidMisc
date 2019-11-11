package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SerialDeviceControlSignals extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean cts;
    public boolean dcd;
    public boolean dsr;
    public boolean ri;

    static {
        SerialDeviceControlSignals.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SerialDeviceControlSignals.DEFAULT_STRUCT_INFO = SerialDeviceControlSignals.VERSION_ARRAY[0];
    }

    public SerialDeviceControlSignals() {
        this(0);
    }

    private SerialDeviceControlSignals(int arg2) {
        super(16, arg2);
    }

    public static SerialDeviceControlSignals decode(Decoder arg3) {
        SerialDeviceControlSignals v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SerialDeviceControlSignals(arg3.readAndValidateDataHeader(SerialDeviceControlSignals.VERSION_ARRAY).elementsOrVersion);
            v1.dcd = arg3.readBoolean(8, 0);
            v1.cts = arg3.readBoolean(8, 1);
            v1.ri = arg3.readBoolean(8, 2);
            v1.dsr = arg3.readBoolean(8, 3);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SerialDeviceControlSignals deserialize(ByteBuffer arg2) {
        return SerialDeviceControlSignals.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerialDeviceControlSignals deserialize(Message arg1) {
        return SerialDeviceControlSignals.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SerialDeviceControlSignals.DEFAULT_STRUCT_INFO);
        arg4.encode(this.dcd, 8, 0);
        arg4.encode(this.cts, 8, 1);
        arg4.encode(this.ri, 8, 2);
        arg4.encode(this.dsr, 8, 3);
    }
}

