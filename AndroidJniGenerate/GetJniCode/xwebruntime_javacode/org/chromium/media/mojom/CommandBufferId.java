package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.UnguessableToken;

public final class CommandBufferId extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public UnguessableToken channelToken;
    public int routeId;

    static {
        CommandBufferId.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        CommandBufferId.DEFAULT_STRUCT_INFO = CommandBufferId.VERSION_ARRAY[0];
    }

    public CommandBufferId() {
        this(0);
    }

    private CommandBufferId(int arg2) {
        super(24, arg2);
    }

    public static CommandBufferId decode(Decoder arg3) {
        CommandBufferId v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new CommandBufferId(arg3.readAndValidateDataHeader(CommandBufferId.VERSION_ARRAY).elementsOrVersion);
            v1.channelToken = UnguessableToken.decode(arg3.readPointer(8, false));
            v1.routeId = arg3.readInt(16);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static CommandBufferId deserialize(ByteBuffer arg2) {
        return CommandBufferId.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CommandBufferId deserialize(Message arg1) {
        return CommandBufferId.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(CommandBufferId.DEFAULT_STRUCT_INFO);
        arg4.encode(this.channelToken, 8, false);
        arg4.encode(this.routeId, 16);
    }
}

