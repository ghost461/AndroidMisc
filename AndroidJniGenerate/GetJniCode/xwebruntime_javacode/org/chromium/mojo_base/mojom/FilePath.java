package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class FilePath extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        FilePath.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        FilePath.DEFAULT_STRUCT_INFO = FilePath.VERSION_ARRAY[0];
    }

    public FilePath() {
        this(0);
    }

    private FilePath(int arg2) {
        super(8, arg2);
    }

    public static FilePath decode(Decoder arg2) {
        FilePath v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new FilePath(arg2.readAndValidateDataHeader(FilePath.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static FilePath deserialize(ByteBuffer arg2) {
        return FilePath.deserialize(new Message(arg2, new ArrayList()));
    }

    public static FilePath deserialize(Message arg1) {
        return FilePath.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(FilePath.DEFAULT_STRUCT_INFO);
    }
}

