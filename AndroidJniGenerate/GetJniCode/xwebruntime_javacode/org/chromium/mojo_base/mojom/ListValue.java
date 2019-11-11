package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ListValue extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public Value[] storage;

    static {
        ListValue.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ListValue.DEFAULT_STRUCT_INFO = ListValue.VERSION_ARRAY[0];
    }

    public ListValue() {
        this(0);
    }

    private ListValue(int arg2) {
        super(16, arg2);
    }

    public static ListValue decode(Decoder arg7) {
        ListValue v1;
        if(arg7 == null) {
            return null;
        }

        arg7.increaseStackDepth();
        try {
            v1 = new ListValue(arg7.readAndValidateDataHeader(ListValue.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 0;
            int v2 = 8;
            Decoder v3 = arg7.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.storage = new Value[v4.elementsOrVersion];
            while(v0_1 < v4.elementsOrVersion) {
                v1.storage[v0_1] = Value.decode(v3, v0_1 * 16 + v2);
                ++v0_1;
            }
        }
        catch(Throwable v0) {
            goto label_29;
        }

        arg7.decreaseStackDepth();
        return v1;
    label_29:
        arg7.decreaseStackDepth();
        throw v0;
    }

    public static ListValue deserialize(ByteBuffer arg2) {
        return ListValue.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ListValue deserialize(Message arg1) {
        return ListValue.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(ListValue.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.storage == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodeUnionArray(this.storage.length, v2, -1);
            int v0;
            for(v0 = 0; v0 < this.storage.length; ++v0) {
                arg6.encode(this.storage[v0], v0 * 16 + v2, false);
            }
        }
    }
}

