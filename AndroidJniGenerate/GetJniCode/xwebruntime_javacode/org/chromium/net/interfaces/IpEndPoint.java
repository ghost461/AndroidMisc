package org.chromium.net.interfaces;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class IpEndPoint extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public IpAddress address;
    public short port;

    static {
        IpEndPoint.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        IpEndPoint.DEFAULT_STRUCT_INFO = IpEndPoint.VERSION_ARRAY[0];
    }

    public IpEndPoint() {
        this(0);
    }

    private IpEndPoint(int arg2) {
        super(24, arg2);
    }

    public static IpEndPoint decode(Decoder arg3) {
        IpEndPoint v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new IpEndPoint(arg3.readAndValidateDataHeader(IpEndPoint.VERSION_ARRAY).elementsOrVersion);
            v1.address = IpAddress.decode(arg3.readPointer(8, false));
            v1.port = arg3.readShort(16);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static IpEndPoint deserialize(ByteBuffer arg2) {
        return IpEndPoint.deserialize(new Message(arg2, new ArrayList()));
    }

    public static IpEndPoint deserialize(Message arg1) {
        return IpEndPoint.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(IpEndPoint.DEFAULT_STRUCT_INFO);
        arg4.encode(this.address, 8, false);
        arg4.encode(this.port, 16);
    }
}

