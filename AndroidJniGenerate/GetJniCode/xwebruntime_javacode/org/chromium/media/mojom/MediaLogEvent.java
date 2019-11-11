package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MediaLogEvent extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        MediaLogEvent.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        MediaLogEvent.DEFAULT_STRUCT_INFO = MediaLogEvent.VERSION_ARRAY[0];
    }

    public MediaLogEvent() {
        this(0);
    }

    private MediaLogEvent(int arg2) {
        super(8, arg2);
    }

    public static MediaLogEvent decode(Decoder arg2) {
        MediaLogEvent v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new MediaLogEvent(arg2.readAndValidateDataHeader(MediaLogEvent.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static MediaLogEvent deserialize(ByteBuffer arg2) {
        return MediaLogEvent.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MediaLogEvent deserialize(Message arg1) {
        return MediaLogEvent.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(MediaLogEvent.DEFAULT_STRUCT_INFO);
    }
}

