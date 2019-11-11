package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SerialDeviceInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String displayName;
    public boolean hasProductId;
    public boolean hasVendorId;
    public String path;
    public short productId;
    public short vendorId;

    static {
        SerialDeviceInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        SerialDeviceInfo.DEFAULT_STRUCT_INFO = SerialDeviceInfo.VERSION_ARRAY[0];
    }

    public SerialDeviceInfo() {
        this(0);
    }

    private SerialDeviceInfo(int arg2) {
        super(0x20, arg2);
        this.hasVendorId = false;
        this.hasProductId = false;
    }

    public static SerialDeviceInfo decode(Decoder arg3) {
        SerialDeviceInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SerialDeviceInfo(arg3.readAndValidateDataHeader(SerialDeviceInfo.VERSION_ARRAY).elementsOrVersion);
            v1.path = arg3.readString(8, false);
            v1.vendorId = arg3.readShort(16);
            v1.hasVendorId = arg3.readBoolean(18, 0);
            v1.hasProductId = arg3.readBoolean(18, 1);
            v1.productId = arg3.readShort(20);
            v1.displayName = arg3.readString(24, true);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SerialDeviceInfo deserialize(ByteBuffer arg2) {
        return SerialDeviceInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerialDeviceInfo deserialize(Message arg1) {
        return SerialDeviceInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SerialDeviceInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.path, 8, false);
        arg4.encode(this.vendorId, 16);
        arg4.encode(this.hasVendorId, 18, 0);
        arg4.encode(this.hasProductId, 18, 1);
        arg4.encode(this.productId, 20);
        arg4.encode(this.displayName, 24, true);
    }
}

