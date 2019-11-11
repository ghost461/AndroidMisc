package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class GpuDevice extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean active;
    public int deviceId;
    public String deviceString;
    public int vendorId;
    public String vendorString;

    static {
        GpuDevice.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        GpuDevice.DEFAULT_STRUCT_INFO = GpuDevice.VERSION_ARRAY[0];
    }

    public GpuDevice() {
        this(0);
    }

    private GpuDevice(int arg2) {
        super(40, arg2);
    }

    public static GpuDevice decode(Decoder arg3) {
        GpuDevice v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new GpuDevice(arg3.readAndValidateDataHeader(GpuDevice.VERSION_ARRAY).elementsOrVersion);
            v1.vendorId = arg3.readInt(8);
            v1.deviceId = arg3.readInt(12);
            v1.active = arg3.readBoolean(16, 0);
            v1.vendorString = arg3.readString(24, false);
            v1.deviceString = arg3.readString(0x20, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static GpuDevice deserialize(ByteBuffer arg2) {
        return GpuDevice.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuDevice deserialize(Message arg1) {
        return GpuDevice.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(GpuDevice.DEFAULT_STRUCT_INFO);
        arg4.encode(this.vendorId, 8);
        arg4.encode(this.deviceId, 12);
        arg4.encode(this.active, 16, 0);
        arg4.encode(this.vendorString, 24, false);
        arg4.encode(this.deviceString, 0x20, false);
    }
}

