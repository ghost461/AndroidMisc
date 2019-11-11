package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class BatteryStatus extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean charging;
    public double chargingTime;
    public double dischargingTime;
    public double level;

    static {
        BatteryStatus.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        BatteryStatus.DEFAULT_STRUCT_INFO = BatteryStatus.VERSION_ARRAY[0];
    }

    public BatteryStatus() {
        this(0);
    }

    private BatteryStatus(int arg3) {
        super(40, arg3);
        this.charging = true;
        this.chargingTime = 0;
        this.dischargingTime = Infinity;
        this.level = 1;
    }

    public static BatteryStatus decode(Decoder arg4) {
        BatteryStatus v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new BatteryStatus(arg4.readAndValidateDataHeader(BatteryStatus.VERSION_ARRAY).elementsOrVersion);
            v1.charging = arg4.readBoolean(8, 0);
            v1.chargingTime = arg4.readDouble(16);
            v1.dischargingTime = arg4.readDouble(24);
            v1.level = arg4.readDouble(0x20);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static BatteryStatus deserialize(ByteBuffer arg2) {
        return BatteryStatus.deserialize(new Message(arg2, new ArrayList()));
    }

    public static BatteryStatus deserialize(Message arg1) {
        return BatteryStatus.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(BatteryStatus.DEFAULT_STRUCT_INFO);
        arg4.encode(this.charging, 8, 0);
        arg4.encode(this.chargingTime, 16);
        arg4.encode(this.dischargingTime, 24);
        arg4.encode(this.level, 0x20);
    }
}

