package org.chromium.net.interfaces;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class IpAddress extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] addressBytes;

    static {
        IpAddress.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        IpAddress.DEFAULT_STRUCT_INFO = IpAddress.VERSION_ARRAY[0];
    }

    public IpAddress() {
        this(0);
    }

    private IpAddress(int arg2) {
        super(16, arg2);
    }

    public static IpAddress decode(Decoder arg4) {
        IpAddress v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new IpAddress(arg4.readAndValidateDataHeader(IpAddress.VERSION_ARRAY).elementsOrVersion);
            v1.addressBytes = arg4.readBytes(8, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static IpAddress deserialize(ByteBuffer arg2) {
        return IpAddress.deserialize(new Message(arg2, new ArrayList()));
    }

    public static IpAddress deserialize(Message arg1) {
        return IpAddress.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5.getEncoderAtDataOffset(IpAddress.DEFAULT_STRUCT_INFO).encode(this.addressBytes, 8, 0, -1);
    }
}

