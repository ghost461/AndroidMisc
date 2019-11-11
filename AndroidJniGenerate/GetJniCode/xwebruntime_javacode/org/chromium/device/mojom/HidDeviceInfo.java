package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HidDeviceInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x60;
    private static final DataHeader[] VERSION_ARRAY;
    public int busType;
    public HidCollectionInfo[] collections;
    public String deviceNode;
    public String guid;
    public boolean hasReportId;
    public long maxFeatureReportSize;
    public long maxInputReportSize;
    public long maxOutputReportSize;
    public short productId;
    public String productName;
    public byte[] reportDescriptor;
    public String serialNumber;
    public short vendorId;

    static {
        HidDeviceInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x60, 0)};
        HidDeviceInfo.DEFAULT_STRUCT_INFO = HidDeviceInfo.VERSION_ARRAY[0];
    }

    public HidDeviceInfo() {
        this(0);
    }

    private HidDeviceInfo(int arg2) {
        super(0x60, arg2);
    }

    public static HidDeviceInfo decode(Decoder arg8) {
        HidDeviceInfo v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new HidDeviceInfo(arg8.readAndValidateDataHeader(HidDeviceInfo.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.guid = arg8.readString(v0_1, false);
            v1.vendorId = arg8.readShort(16);
            v1.productId = arg8.readShort(18);
            v1.busType = arg8.readInt(20);
            HidBusType.validate(v1.busType);
            v1.productName = arg8.readString(24, false);
            v1.serialNumber = arg8.readString(0x20, false);
            v1.reportDescriptor = arg8.readBytes(40, 0, -1);
            Decoder v3 = arg8.readPointer(0x30, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.collections = new HidCollectionInfo[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.collections[v5] = HidCollectionInfo.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }

            v1.hasReportId = arg8.readBoolean(56, 0);
            v1.maxInputReportSize = arg8.readLong(0x40);
            v1.maxOutputReportSize = arg8.readLong(72);
            v1.maxFeatureReportSize = arg8.readLong(80);
            v1.deviceNode = arg8.readString(88, false);
        }
        catch(Throwable v0) {
            goto label_69;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_69:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static HidDeviceInfo deserialize(ByteBuffer arg2) {
        return HidDeviceInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HidDeviceInfo deserialize(Message arg1) {
        return HidDeviceInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(HidDeviceInfo.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg7.encode(this.guid, v1, false);
        arg7.encode(this.vendorId, 16);
        arg7.encode(this.productId, 18);
        arg7.encode(this.busType, 20);
        arg7.encode(this.productName, 24, false);
        arg7.encode(this.serialNumber, 0x20, false);
        int v3 = -1;
        arg7.encode(this.reportDescriptor, 40, 0, v3);
        int v4 = 0x30;
        if(this.collections == null) {
            arg7.encodeNullPointer(v4, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.collections.length, v4, v3);
            for(v3 = 0; v3 < this.collections.length; ++v3) {
                v0.encode(this.collections[v3], v3 * 8 + v1, false);
            }
        }

        arg7.encode(this.hasReportId, 56, 0);
        arg7.encode(this.maxInputReportSize, 0x40);
        arg7.encode(this.maxOutputReportSize, 72);
        arg7.encode(this.maxFeatureReportSize, 80);
        arg7.encode(this.deviceNode, 88, false);
    }
}

