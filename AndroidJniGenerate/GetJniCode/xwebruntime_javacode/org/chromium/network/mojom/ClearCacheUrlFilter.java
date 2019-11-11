package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Origin;

public final class ClearCacheUrlFilter extends Struct {
    public final class Type {
        public static final int DELETE_MATCHES = 0;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int KEEP_MATCHES = 1;

        private Type() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(Type.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String[] domains;
    public Origin[] origins;
    public int type;

    static {
        ClearCacheUrlFilter.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        ClearCacheUrlFilter.DEFAULT_STRUCT_INFO = ClearCacheUrlFilter.VERSION_ARRAY[0];
    }

    public ClearCacheUrlFilter() {
        this(0);
    }

    private ClearCacheUrlFilter(int arg2) {
        super(0x20, arg2);
    }

    public static ClearCacheUrlFilter decode(Decoder arg9) {
        ClearCacheUrlFilter v1;
        if(arg9 == null) {
            return null;
        }

        arg9.increaseStackDepth();
        try {
            v1 = new ClearCacheUrlFilter(arg9.readAndValidateDataHeader(ClearCacheUrlFilter.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.type = arg9.readInt(v0_1);
            Type.validate(v1.type);
            Decoder v2 = arg9.readPointer(16, false);
            int v4 = -1;
            DataHeader v5 = v2.readDataHeaderForPointerArray(v4);
            v1.domains = new String[v5.elementsOrVersion];
            int v6;
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.domains[v6] = v2.readString(v6 * 8 + v0_1, false);
            }

            v2 = arg9.readPointer(24, false);
            DataHeader v4_1 = v2.readDataHeaderForPointerArray(v4);
            v1.origins = new Origin[v4_1.elementsOrVersion];
            int v5_1;
            for(v5_1 = 0; v5_1 < v4_1.elementsOrVersion; ++v5_1) {
                v1.origins[v5_1] = Origin.decode(v2.readPointer(v5_1 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_52;
        }

        arg9.decreaseStackDepth();
        return v1;
    label_52:
        arg9.decreaseStackDepth();
        throw v0;
    }

    public static ClearCacheUrlFilter deserialize(ByteBuffer arg2) {
        return ClearCacheUrlFilter.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ClearCacheUrlFilter deserialize(Message arg1) {
        return ClearCacheUrlFilter.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg8) {
        arg8 = arg8.getEncoderAtDataOffset(ClearCacheUrlFilter.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg8.encode(this.type, v1);
        int v2 = -1;
        int v3 = 16;
        if(this.domains == null) {
            arg8.encodeNullPointer(v3, false);
        }
        else {
            Encoder v0 = arg8.encodePointerArray(this.domains.length, v3, v2);
            for(v3 = 0; v3 < this.domains.length; ++v3) {
                v0.encode(this.domains[v3], v3 * 8 + v1, false);
            }
        }

        v3 = 24;
        if(this.origins == null) {
            arg8.encodeNullPointer(v3, false);
        }
        else {
            arg8 = arg8.encodePointerArray(this.origins.length, v3, v2);
            int v0_1;
            for(v0_1 = 0; v0_1 < this.origins.length; ++v0_1) {
                arg8.encode(this.origins[v0_1], v0_1 * 8 + v1, false);
            }
        }
    }
}

