package org.chromium.mojo.bindings.pipecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class DisconnectReason extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int customReason;
    public String description;

    static {
        DisconnectReason.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        DisconnectReason.DEFAULT_STRUCT_INFO = DisconnectReason.VERSION_ARRAY[0];
    }

    public DisconnectReason() {
        this(0);
    }

    private DisconnectReason(int arg2) {
        super(24, arg2);
    }

    public static DisconnectReason decode(Decoder arg3) {
        DisconnectReason v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new DisconnectReason(arg3.readAndValidateDataHeader(DisconnectReason.VERSION_ARRAY).elementsOrVersion);
            v1.customReason = arg3.readInt(8);
            v1.description = arg3.readString(16, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static DisconnectReason deserialize(ByteBuffer arg2) {
        return DisconnectReason.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DisconnectReason deserialize(Message arg1) {
        return DisconnectReason.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(DisconnectReason.DEFAULT_STRUCT_INFO);
        arg4.encode(this.customReason, 8);
        arg4.encode(this.description, 16, false);
    }
}

