package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SerialConnectionInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public int bitrate;
    public boolean ctsFlowControl;
    public int dataBits;
    public int parityBit;
    public int stopBits;

    static {
        SerialConnectionInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        SerialConnectionInfo.DEFAULT_STRUCT_INFO = SerialConnectionInfo.VERSION_ARRAY[0];
    }

    public SerialConnectionInfo() {
        this(0);
    }

    private SerialConnectionInfo(int arg2) {
        super(0x20, arg2);
        this.bitrate = 0;
        this.dataBits = 0;
        this.parityBit = 0;
        this.stopBits = 0;
    }

    public static SerialConnectionInfo decode(Decoder arg3) {
        SerialConnectionInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SerialConnectionInfo(arg3.readAndValidateDataHeader(SerialConnectionInfo.VERSION_ARRAY).elementsOrVersion);
            v1.bitrate = arg3.readInt(8);
            v1.dataBits = arg3.readInt(12);
            SerialDataBits.validate(v1.dataBits);
            v1.parityBit = arg3.readInt(16);
            SerialParityBit.validate(v1.parityBit);
            v1.stopBits = arg3.readInt(20);
            SerialStopBits.validate(v1.stopBits);
            v1.ctsFlowControl = arg3.readBoolean(24, 0);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SerialConnectionInfo deserialize(ByteBuffer arg2) {
        return SerialConnectionInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SerialConnectionInfo deserialize(Message arg1) {
        return SerialConnectionInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SerialConnectionInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.bitrate, 8);
        arg4.encode(this.dataBits, 12);
        arg4.encode(this.parityBit, 16);
        arg4.encode(this.stopBits, 20);
        arg4.encode(this.ctsFlowControl, 24, 0);
    }
}

