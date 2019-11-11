package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;

public final class SensorInitParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    public static final long READ_BUFFER_SIZE_FOR_TESTS = 0x30;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public long bufferOffset;
    public InterfaceRequest clientRequest;
    public SensorConfiguration defaultConfiguration;
    public double maximumFrequency;
    public SharedBufferHandle memory;
    public double minimumFrequency;
    public int mode;
    public Sensor sensor;

    static {
        SensorInitParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        SensorInitParams.DEFAULT_STRUCT_INFO = SensorInitParams.VERSION_ARRAY[0];
    }

    public SensorInitParams() {
        this(0);
    }

    private SensorInitParams(int arg2) {
        super(0x40, arg2);
        this.memory = InvalidHandle.INSTANCE;
    }

    public static SensorInitParams decode(Decoder arg6) {
        SensorInitParams v1;
        if(arg6 == null) {
            return null;
        }

        arg6.increaseStackDepth();
        try {
            v1 = new SensorInitParams(arg6.readAndValidateDataHeader(SensorInitParams.VERSION_ARRAY).elementsOrVersion);
            v1.sensor = arg6.readServiceInterface(8, false, Sensor.MANAGER);
            v1.clientRequest = arg6.readInterfaceRequest(16, false);
            v1.memory = arg6.readSharedBufferHandle(20, false);
            v1.bufferOffset = arg6.readLong(24);
            v1.mode = arg6.readInt(0x20);
            ReportingMode.validate(v1.mode);
            v1.defaultConfiguration = SensorConfiguration.decode(arg6.readPointer(40, false));
            v1.maximumFrequency = arg6.readDouble(0x30);
            v1.minimumFrequency = arg6.readDouble(56);
        }
        catch(Throwable v0) {
            arg6.decreaseStackDepth();
            throw v0;
        }

        arg6.decreaseStackDepth();
        return v1;
    }

    public static SensorInitParams deserialize(ByteBuffer arg2) {
        return SensorInitParams.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SensorInitParams deserialize(Message arg1) {
        return SensorInitParams.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(SensorInitParams.DEFAULT_STRUCT_INFO);
        arg5.encode(this.sensor, 8, false, Sensor.MANAGER);
        arg5.encode(this.clientRequest, 16, false);
        arg5.encode(this.memory, 20, false);
        arg5.encode(this.bufferOffset, 24);
        arg5.encode(this.mode, 0x20);
        arg5.encode(this.defaultConfiguration, 40, false);
        arg5.encode(this.maximumFrequency, 0x30);
        arg5.encode(this.minimumFrequency, 56);
    }
}

