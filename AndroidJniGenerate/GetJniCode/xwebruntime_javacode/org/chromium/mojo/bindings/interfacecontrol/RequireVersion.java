package org.chromium.mojo.bindings.interfacecontrol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RequireVersion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int version;

    static {
        RequireVersion.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        RequireVersion.DEFAULT_STRUCT_INFO = RequireVersion.VERSION_ARRAY[0];
    }

    public RequireVersion() {
        this(0);
    }

    private RequireVersion(int arg2) {
        super(16, arg2);
    }

    public static RequireVersion decode(Decoder arg2) {
        RequireVersion v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new RequireVersion(arg2.readAndValidateDataHeader(RequireVersion.VERSION_ARRAY).elementsOrVersion);
            v1.version = arg2.readInt(8);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static RequireVersion deserialize(ByteBuffer arg2) {
        return RequireVersion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static RequireVersion deserialize(Message arg1) {
        return RequireVersion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3.getEncoderAtDataOffset(RequireVersion.DEFAULT_STRUCT_INFO).encode(this.version, 8);
    }
}

