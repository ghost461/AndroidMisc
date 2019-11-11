package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SessionData extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public byte[] keySetId;
    public String mimeType;

    static {
        SessionData.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        SessionData.DEFAULT_STRUCT_INFO = SessionData.VERSION_ARRAY[0];
    }

    public SessionData() {
        this(0);
    }

    private SessionData(int arg2) {
        super(24, arg2);
    }

    public static SessionData decode(Decoder arg4) {
        SessionData v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new SessionData(arg4.readAndValidateDataHeader(SessionData.VERSION_ARRAY).elementsOrVersion);
            v1.keySetId = arg4.readBytes(8, 0, -1);
            v1.mimeType = arg4.readString(16, false);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static SessionData deserialize(ByteBuffer arg2) {
        return SessionData.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SessionData deserialize(Message arg1) {
        return SessionData.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(SessionData.DEFAULT_STRUCT_INFO);
        arg5.encode(this.keySetId, 8, 0, -1);
        arg5.encode(this.mimeType, 16, false);
    }
}

