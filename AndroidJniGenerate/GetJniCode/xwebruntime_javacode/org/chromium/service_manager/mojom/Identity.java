package org.chromium.service_manager.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Identity extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String instance;
    public String name;
    public String userId;

    static {
        Identity.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        Identity.DEFAULT_STRUCT_INFO = Identity.VERSION_ARRAY[0];
    }

    public Identity() {
        this(0);
    }

    private Identity(int arg2) {
        super(0x20, arg2);
    }

    public static Identity decode(Decoder arg3) {
        Identity v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new Identity(arg3.readAndValidateDataHeader(Identity.VERSION_ARRAY).elementsOrVersion);
            v1.name = arg3.readString(8, false);
            v1.userId = arg3.readString(16, false);
            v1.instance = arg3.readString(24, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static Identity deserialize(ByteBuffer arg2) {
        return Identity.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Identity deserialize(Message arg1) {
        return Identity.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(Identity.DEFAULT_STRUCT_INFO);
        arg4.encode(this.name, 8, false);
        arg4.encode(this.userId, 16, false);
        arg4.encode(this.instance, 24, false);
    }
}

