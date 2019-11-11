package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MailboxHolder extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public Mailbox mailbox;
    public SyncToken syncToken;
    public int textureTarget;

    static {
        MailboxHolder.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        MailboxHolder.DEFAULT_STRUCT_INFO = MailboxHolder.VERSION_ARRAY[0];
    }

    public MailboxHolder() {
        this(0);
    }

    private MailboxHolder(int arg2) {
        super(0x20, arg2);
    }

    public static MailboxHolder decode(Decoder arg3) {
        MailboxHolder v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new MailboxHolder(arg3.readAndValidateDataHeader(MailboxHolder.VERSION_ARRAY).elementsOrVersion);
            v1.mailbox = Mailbox.decode(arg3.readPointer(8, false));
            v1.syncToken = SyncToken.decode(arg3.readPointer(16, false));
            v1.textureTarget = arg3.readInt(24);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static MailboxHolder deserialize(ByteBuffer arg2) {
        return MailboxHolder.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MailboxHolder deserialize(Message arg1) {
        return MailboxHolder.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(MailboxHolder.DEFAULT_STRUCT_INFO);
        arg4.encode(this.mailbox, 8, false);
        arg4.encode(this.syncToken, 16, false);
        arg4.encode(this.textureTarget, 24);
    }
}

