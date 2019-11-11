package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class DictionaryValue extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public Map storage;

    static {
        DictionaryValue.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        DictionaryValue.DEFAULT_STRUCT_INFO = DictionaryValue.VERSION_ARRAY[0];
    }

    public DictionaryValue() {
        this(0);
    }

    private DictionaryValue(int arg2) {
        super(16, arg2);
    }

    public static DictionaryValue decode(Decoder arg9) {
        DictionaryValue v1;
        if(arg9 == null) {
            return null;
        }

        arg9.increaseStackDepth();
        try {
            v1 = new DictionaryValue(arg9.readAndValidateDataHeader(DictionaryValue.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            int v2 = 0;
            Decoder v3 = arg9.readPointer(v0_1, false);
            v3.readDataHeaderForMap();
            Decoder v4 = v3.readPointer(v0_1, false);
            DataHeader v5 = v4.readDataHeaderForPointerArray(-1);
            String[] v6 = new String[v5.elementsOrVersion];
            int v7;
            for(v7 = 0; v7 < v5.elementsOrVersion; ++v7) {
                v6[v7] = v4.readString(v7 * 8 + v0_1, false);
            }

            v3 = v3.readPointer(16, false);
            DataHeader v4_1 = v3.readDataHeaderForPointerArray(v6.length);
            Value[] v5_1 = new Value[v4_1.elementsOrVersion];
            for(v7 = 0; v7 < v4_1.elementsOrVersion; ++v7) {
                v5_1[v7] = Value.decode(v3, v7 * 16 + v0_1);
            }

            v1.storage = new HashMap();
            while(v2 < v6.length) {
                v1.storage.put(v6[v2], v5_1[v2]);
                ++v2;
            }
        }
        catch(Throwable v0) {
            goto label_56;
        }

        arg9.decreaseStackDepth();
        return v1;
    label_56:
        arg9.decreaseStackDepth();
        throw v0;
    }

    public static DictionaryValue deserialize(ByteBuffer arg2) {
        return DictionaryValue.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DictionaryValue deserialize(Message arg1) {
        return DictionaryValue.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg10) {
        arg10 = arg10.getEncoderAtDataOffset(DictionaryValue.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.storage == null) {
            arg10.encodeNullPointer(v2, false);
        }
        else {
            arg10 = arg10.encoderForMap(v2);
            int v0 = this.storage.size();
            String[] v3 = new String[v0];
            Value[] v0_1 = new Value[v0];
            Iterator v4 = this.storage.entrySet().iterator();
            int v5;
            for(v5 = 0; v4.hasNext(); ++v5) {
                Object v6 = v4.next();
                v3[v5] = ((Map$Entry)v6).getKey();
                v0_1[v5] = ((Map$Entry)v6).getValue();
            }

            v5 = -1;
            Encoder v4_1 = arg10.encodePointerArray(v3.length, v2, v5);
            int v6_1;
            for(v6_1 = 0; v6_1 < v3.length; ++v6_1) {
                v4_1.encode(v3[v6_1], v6_1 * 8 + v2, false);
            }

            arg10 = arg10.encodeUnionArray(v0_1.length, 16, v5);
            int v3_1;
            for(v3_1 = 0; v3_1 < v0_1.length; ++v3_1) {
                arg10.encode(v0_1[v3_1], v3_1 * 16 + v2, false);
            }
        }
    }
}

