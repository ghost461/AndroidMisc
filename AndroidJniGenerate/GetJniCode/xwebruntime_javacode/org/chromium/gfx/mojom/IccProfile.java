package org.chromium.gfx.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class IccProfile extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        IccProfile.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        IccProfile.DEFAULT_STRUCT_INFO = IccProfile.VERSION_ARRAY[0];
    }

    public IccProfile() {
        this(0);
    }

    private IccProfile(int arg2) {
        super(8, arg2);
    }

    public static IccProfile decode(Decoder arg2) {
        IccProfile v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new IccProfile(arg2.readAndValidateDataHeader(IccProfile.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static IccProfile deserialize(ByteBuffer arg2) {
        return IccProfile.deserialize(new Message(arg2, new ArrayList()));
    }

    public static IccProfile deserialize(Message arg1) {
        return IccProfile.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(IccProfile.DEFAULT_STRUCT_INFO);
    }
}

