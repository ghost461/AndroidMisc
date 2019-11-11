package org.chromium.blink.mojom.document_metadata;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Entity extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public Property[] properties;
    public String type;

    static {
        Entity.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        Entity.DEFAULT_STRUCT_INFO = Entity.VERSION_ARRAY[0];
    }

    public Entity() {
        this(0);
    }

    private Entity(int arg2) {
        super(24, arg2);
    }

    public static Entity decode(Decoder arg8) {
        Entity v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new Entity(arg8.readAndValidateDataHeader(Entity.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.type = arg8.readString(v0_1, false);
            Decoder v3 = arg8.readPointer(16, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.properties = new Property[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.properties[v5] = Property.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_34;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_34:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static Entity deserialize(ByteBuffer arg2) {
        return Entity.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Entity deserialize(Message arg1) {
        return Entity.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(Entity.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.type, v1, false);
        int v3 = 16;
        if(this.properties == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.properties.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.properties.length; ++v0) {
                arg6.encode(this.properties[v0], v0 * 8 + v1, false);
            }
        }
    }
}

