package org.chromium.blink.mojom.document_metadata;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Property extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String name;
    public Values values;

    static {
        Property.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        Property.DEFAULT_STRUCT_INFO = Property.VERSION_ARRAY[0];
    }

    public Property() {
        this(0);
    }

    private Property(int arg2) {
        super(0x20, arg2);
    }

    public static Property decode(Decoder arg3) {
        Property v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new Property(arg3.readAndValidateDataHeader(Property.VERSION_ARRAY).elementsOrVersion);
            v1.name = arg3.readString(8, false);
            v1.values = Values.decode(arg3, 16);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static Property deserialize(ByteBuffer arg2) {
        return Property.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Property deserialize(Message arg1) {
        return Property.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(Property.DEFAULT_STRUCT_INFO);
        arg4.encode(this.name, 8, false);
        arg4.encode(this.values, 16, false);
    }
}

