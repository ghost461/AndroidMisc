package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SyncToken extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public long commandBufferId;
    public int namespaceId;
    public long releaseCount;
    public boolean verifiedFlush;

    static {
        SyncToken.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        SyncToken.DEFAULT_STRUCT_INFO = SyncToken.VERSION_ARRAY[0];
    }

    public SyncToken() {
        this(0);
    }

    private SyncToken(int arg2) {
        super(0x20, arg2);
    }

    public static SyncToken decode(Decoder arg4) {
        SyncToken v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new SyncToken(arg4.readAndValidateDataHeader(SyncToken.VERSION_ARRAY).elementsOrVersion);
            v1.verifiedFlush = arg4.readBoolean(8, 0);
            v1.namespaceId = arg4.readInt(12);
            CommandBufferNamespace.validate(v1.namespaceId);
            v1.commandBufferId = arg4.readLong(16);
            v1.releaseCount = arg4.readLong(24);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static SyncToken deserialize(ByteBuffer arg2) {
        return SyncToken.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SyncToken deserialize(Message arg1) {
        return SyncToken.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(SyncToken.DEFAULT_STRUCT_INFO);
        arg4.encode(this.verifiedFlush, 8, 0);
        arg4.encode(this.namespaceId, 12);
        arg4.encode(this.commandBufferId, 16);
        arg4.encode(this.releaseCount, 24);
    }
}

