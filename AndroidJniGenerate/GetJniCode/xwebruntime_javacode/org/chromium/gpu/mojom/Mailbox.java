package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Mailbox extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] name;

    static {
        Mailbox.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        Mailbox.DEFAULT_STRUCT_INFO = Mailbox.VERSION_ARRAY[0];
    }

    public Mailbox() {
        this(0);
    }

    private Mailbox(int arg2) {
        super(16, arg2);
    }

    public static Mailbox decode(Decoder arg4) {
        Mailbox v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new Mailbox(arg4.readAndValidateDataHeader(Mailbox.VERSION_ARRAY).elementsOrVersion);
            v1.name = arg4.readBytes(8, 0, 16);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static Mailbox deserialize(ByteBuffer arg2) {
        return Mailbox.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Mailbox deserialize(Message arg1) {
        return Mailbox.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5.getEncoderAtDataOffset(Mailbox.DEFAULT_STRUCT_INFO).encode(this.name, 8, 0, 16);
    }
}

