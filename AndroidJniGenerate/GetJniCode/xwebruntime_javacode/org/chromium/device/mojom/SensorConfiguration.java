package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SensorConfiguration extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public double frequency;

    static {
        SensorConfiguration.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SensorConfiguration.DEFAULT_STRUCT_INFO = SensorConfiguration.VERSION_ARRAY[0];
    }

    public SensorConfiguration() {
        this(0);
    }

    private SensorConfiguration(int arg2) {
        super(16, arg2);
    }

    public static SensorConfiguration decode(Decoder arg4) {
        SensorConfiguration v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new SensorConfiguration(arg4.readAndValidateDataHeader(SensorConfiguration.VERSION_ARRAY).elementsOrVersion);
            v1.frequency = arg4.readDouble(8);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static SensorConfiguration deserialize(ByteBuffer arg2) {
        return SensorConfiguration.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SensorConfiguration deserialize(Message arg1) {
        return SensorConfiguration.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(SensorConfiguration.DEFAULT_STRUCT_INFO).encode(this.frequency, 8);
    }
}

