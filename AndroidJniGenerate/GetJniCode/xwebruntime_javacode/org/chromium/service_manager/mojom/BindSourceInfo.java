package org.chromium.service_manager.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class BindSourceInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public Identity identity;
    public CapabilitySet requiredCapabilities;

    static {
        BindSourceInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        BindSourceInfo.DEFAULT_STRUCT_INFO = BindSourceInfo.VERSION_ARRAY[0];
    }

    public BindSourceInfo() {
        this(0);
    }

    private BindSourceInfo(int arg2) {
        super(24, arg2);
    }

    public static BindSourceInfo decode(Decoder arg3) {
        BindSourceInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new BindSourceInfo(arg3.readAndValidateDataHeader(BindSourceInfo.VERSION_ARRAY).elementsOrVersion);
            v1.identity = Identity.decode(arg3.readPointer(8, false));
            v1.requiredCapabilities = CapabilitySet.decode(arg3.readPointer(16, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static BindSourceInfo deserialize(ByteBuffer arg2) {
        return BindSourceInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static BindSourceInfo deserialize(Message arg1) {
        return BindSourceInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(BindSourceInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.identity, 8, false);
        arg4.encode(this.requiredCapabilities, 16, false);
    }
}

