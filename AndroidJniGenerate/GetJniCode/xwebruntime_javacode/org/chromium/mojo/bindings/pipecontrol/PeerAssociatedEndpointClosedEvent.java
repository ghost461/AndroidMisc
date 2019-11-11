package org.chromium.mojo.bindings.pipecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PeerAssociatedEndpointClosedEvent extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public DisconnectReason disconnectReason;
    public int id;

    static {
        PeerAssociatedEndpointClosedEvent.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        PeerAssociatedEndpointClosedEvent.DEFAULT_STRUCT_INFO = PeerAssociatedEndpointClosedEvent.VERSION_ARRAY[0];
    }

    public PeerAssociatedEndpointClosedEvent() {
        this(0);
    }

    private PeerAssociatedEndpointClosedEvent(int arg2) {
        super(24, arg2);
    }

    public static PeerAssociatedEndpointClosedEvent decode(Decoder arg3) {
        PeerAssociatedEndpointClosedEvent v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PeerAssociatedEndpointClosedEvent(arg3.readAndValidateDataHeader(PeerAssociatedEndpointClosedEvent.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg3.readInt(8);
            v1.disconnectReason = DisconnectReason.decode(arg3.readPointer(16, true));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PeerAssociatedEndpointClosedEvent deserialize(ByteBuffer arg2) {
        return PeerAssociatedEndpointClosedEvent.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PeerAssociatedEndpointClosedEvent deserialize(Message arg1) {
        return PeerAssociatedEndpointClosedEvent.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PeerAssociatedEndpointClosedEvent.DEFAULT_STRUCT_INFO);
        arg4.encode(this.id, 8);
        arg4.encode(this.disconnectReason, 16, true);
    }
}

