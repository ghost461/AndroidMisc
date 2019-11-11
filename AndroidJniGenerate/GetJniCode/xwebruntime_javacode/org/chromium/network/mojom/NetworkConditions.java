package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class NetworkConditions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public double downloadThroughput;
    public TimeDelta latency;
    public boolean offline;
    public double uploadThroughput;

    static {
        NetworkConditions.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        NetworkConditions.DEFAULT_STRUCT_INFO = NetworkConditions.VERSION_ARRAY[0];
    }

    public NetworkConditions() {
        this(0);
    }

    private NetworkConditions(int arg2) {
        super(40, arg2);
    }

    public static NetworkConditions decode(Decoder arg4) {
        NetworkConditions v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new NetworkConditions(arg4.readAndValidateDataHeader(NetworkConditions.VERSION_ARRAY).elementsOrVersion);
            v1.offline = arg4.readBoolean(8, 0);
            v1.latency = TimeDelta.decode(arg4.readPointer(16, false));
            v1.downloadThroughput = arg4.readDouble(24);
            v1.uploadThroughput = arg4.readDouble(0x20);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static NetworkConditions deserialize(ByteBuffer arg2) {
        return NetworkConditions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NetworkConditions deserialize(Message arg1) {
        return NetworkConditions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(NetworkConditions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.offline, 8, 0);
        arg4.encode(this.latency, 16, false);
        arg4.encode(this.downloadThroughput, 24);
        arg4.encode(this.uploadThroughput, 0x20);
    }
}

