package org.chromium.net.interfaces;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HostResolverRequestInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int addressFamily;
    public String host;
    public boolean isMyIpAddress;
    public short port;

    static {
        HostResolverRequestInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        HostResolverRequestInfo.DEFAULT_STRUCT_INFO = HostResolverRequestInfo.VERSION_ARRAY[0];
    }

    public HostResolverRequestInfo() {
        this(0);
    }

    private HostResolverRequestInfo(int arg2) {
        super(24, arg2);
    }

    public static HostResolverRequestInfo decode(Decoder arg3) {
        HostResolverRequestInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new HostResolverRequestInfo(arg3.readAndValidateDataHeader(HostResolverRequestInfo.VERSION_ARRAY).elementsOrVersion);
            v1.host = arg3.readString(8, false);
            v1.port = arg3.readShort(16);
            v1.isMyIpAddress = arg3.readBoolean(18, 0);
            v1.addressFamily = arg3.readInt(20);
            AddressFamily.validate(v1.addressFamily);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static HostResolverRequestInfo deserialize(ByteBuffer arg2) {
        return HostResolverRequestInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HostResolverRequestInfo deserialize(Message arg1) {
        return HostResolverRequestInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(HostResolverRequestInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.host, 8, false);
        arg4.encode(this.port, 16);
        arg4.encode(this.isMyIpAddress, 18, 0);
        arg4.encode(this.addressFamily, 20);
    }
}

