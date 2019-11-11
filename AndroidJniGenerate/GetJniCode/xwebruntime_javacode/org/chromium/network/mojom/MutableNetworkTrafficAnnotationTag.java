package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class MutableNetworkTrafficAnnotationTag extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int uniqueIdHashCode;

    static {
        MutableNetworkTrafficAnnotationTag.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        MutableNetworkTrafficAnnotationTag.DEFAULT_STRUCT_INFO = MutableNetworkTrafficAnnotationTag.VERSION_ARRAY[0];
    }

    public MutableNetworkTrafficAnnotationTag() {
        this(0);
    }

    private MutableNetworkTrafficAnnotationTag(int arg2) {
        super(16, arg2);
    }

    public static MutableNetworkTrafficAnnotationTag decode(Decoder arg2) {
        MutableNetworkTrafficAnnotationTag v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new MutableNetworkTrafficAnnotationTag(arg2.readAndValidateDataHeader(MutableNetworkTrafficAnnotationTag.VERSION_ARRAY).elementsOrVersion);
            v1.uniqueIdHashCode = arg2.readInt(8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static MutableNetworkTrafficAnnotationTag deserialize(ByteBuffer arg2) {
        return MutableNetworkTrafficAnnotationTag.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MutableNetworkTrafficAnnotationTag deserialize(Message arg1) {
        return MutableNetworkTrafficAnnotationTag.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(MutableNetworkTrafficAnnotationTag.DEFAULT_STRUCT_INFO).encode(this.uniqueIdHashCode, 8);
    }
}

