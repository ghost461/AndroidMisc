package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UrlLoaderCompletionStatus extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        UrlLoaderCompletionStatus.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        UrlLoaderCompletionStatus.DEFAULT_STRUCT_INFO = UrlLoaderCompletionStatus.VERSION_ARRAY[0];
    }

    public UrlLoaderCompletionStatus() {
        this(0);
    }

    private UrlLoaderCompletionStatus(int arg2) {
        super(8, arg2);
    }

    public static UrlLoaderCompletionStatus decode(Decoder arg2) {
        UrlLoaderCompletionStatus v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new UrlLoaderCompletionStatus(arg2.readAndValidateDataHeader(UrlLoaderCompletionStatus.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static UrlLoaderCompletionStatus deserialize(ByteBuffer arg2) {
        return UrlLoaderCompletionStatus.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UrlLoaderCompletionStatus deserialize(Message arg1) {
        return UrlLoaderCompletionStatus.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(UrlLoaderCompletionStatus.DEFAULT_STRUCT_INFO);
    }
}

