package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gpu.mojom.MailboxHolder;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MailboxVideoFrameData extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public MailboxHolder[] mailboxHolder;

    static {
        MailboxVideoFrameData.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        MailboxVideoFrameData.DEFAULT_STRUCT_INFO = MailboxVideoFrameData.VERSION_ARRAY[0];
    }

    public MailboxVideoFrameData() {
        this(0);
    }

    private MailboxVideoFrameData(int arg2) {
        super(16, arg2);
    }

    public static MailboxVideoFrameData decode(Decoder arg8) {
        MailboxVideoFrameData v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new MailboxVideoFrameData(arg8.readAndValidateDataHeader(MailboxVideoFrameData.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(4);
            v1.mailboxHolder = new MailboxHolder[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.mailboxHolder[v5] = MailboxHolder.decode(v3.readPointer(v5 * 8 + v2, false));
            }
        }
        catch(Throwable v0) {
            goto label_31;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_31:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static MailboxVideoFrameData deserialize(ByteBuffer arg2) {
        return MailboxVideoFrameData.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MailboxVideoFrameData deserialize(Message arg1) {
        return MailboxVideoFrameData.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(MailboxVideoFrameData.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.mailboxHolder == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.mailboxHolder.length, v2, 4);
            int v0;
            for(v0 = 0; v0 < this.mailboxHolder.length; ++v0) {
                arg6.encode(this.mailboxHolder[v0], v0 * 8 + v2, false);
            }
        }
    }
}

