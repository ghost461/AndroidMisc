package org.chromium.service_manager.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CapabilitySet extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public String[] capabilities;

    static {
        CapabilitySet.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        CapabilitySet.DEFAULT_STRUCT_INFO = CapabilitySet.VERSION_ARRAY[0];
    }

    public CapabilitySet() {
        this(0);
    }

    private CapabilitySet(int arg2) {
        super(16, arg2);
    }

    public static CapabilitySet decode(Decoder arg8) {
        CapabilitySet v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new CapabilitySet(arg8.readAndValidateDataHeader(CapabilitySet.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.capabilities = new String[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.capabilities[v5] = v3.readString(v5 * 8 + v2, false);
            }
        }
        catch(Throwable v0) {
            goto label_30;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_30:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static CapabilitySet deserialize(ByteBuffer arg2) {
        return CapabilitySet.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CapabilitySet deserialize(Message arg1) {
        return CapabilitySet.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(CapabilitySet.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.capabilities == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.capabilities.length, v2, -1);
            int v0;
            for(v0 = 0; v0 < this.capabilities.length; ++v0) {
                arg6.encode(this.capabilities[v0], v0 * 8 + v2, false);
            }
        }
    }
}

