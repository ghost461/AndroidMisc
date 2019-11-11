package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UdpSocketOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean allowAddressReuse;
    public int multicastInterface;
    public boolean multicastLoopbackMode;
    public int multicastTimeToLive;
    public int receiveBufferSize;
    public int sendBufferSize;

    static {
        UdpSocketOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        UdpSocketOptions.DEFAULT_STRUCT_INFO = UdpSocketOptions.VERSION_ARRAY[0];
    }

    public UdpSocketOptions() {
        this(0);
    }

    private UdpSocketOptions(int arg2) {
        super(0x20, arg2);
        this.allowAddressReuse = false;
        this.multicastInterface = 0;
        this.multicastTimeToLive = 1;
        this.multicastLoopbackMode = true;
        this.sendBufferSize = 0;
        this.receiveBufferSize = 0;
    }

    public static UdpSocketOptions decode(Decoder arg3) {
        UdpSocketOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new UdpSocketOptions(arg3.readAndValidateDataHeader(UdpSocketOptions.VERSION_ARRAY).elementsOrVersion);
            v1.allowAddressReuse = arg3.readBoolean(8, 0);
            v1.multicastLoopbackMode = arg3.readBoolean(8, 1);
            v1.multicastInterface = arg3.readInt(12);
            v1.multicastTimeToLive = arg3.readInt(16);
            v1.sendBufferSize = arg3.readInt(20);
            v1.receiveBufferSize = arg3.readInt(24);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static UdpSocketOptions deserialize(ByteBuffer arg2) {
        return UdpSocketOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UdpSocketOptions deserialize(Message arg1) {
        return UdpSocketOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(UdpSocketOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.allowAddressReuse, 8, 0);
        arg4.encode(this.multicastLoopbackMode, 8, 1);
        arg4.encode(this.multicastInterface, 12);
        arg4.encode(this.multicastTimeToLive, 16);
        arg4.encode(this.sendBufferSize, 20);
        arg4.encode(this.receiveBufferSize, 24);
    }
}

