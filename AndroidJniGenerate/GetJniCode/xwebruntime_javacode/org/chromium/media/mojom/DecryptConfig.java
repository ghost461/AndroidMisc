package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class DecryptConfig extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String iv;
    public String keyId;
    public SubsampleEntry[] subsamples;

    static {
        DecryptConfig.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        DecryptConfig.DEFAULT_STRUCT_INFO = DecryptConfig.VERSION_ARRAY[0];
    }

    public DecryptConfig() {
        this(0);
    }

    private DecryptConfig(int arg2) {
        super(0x20, arg2);
    }

    public static DecryptConfig decode(Decoder arg8) {
        DecryptConfig v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new DecryptConfig(arg8.readAndValidateDataHeader(DecryptConfig.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.keyId = arg8.readString(v0_1, false);
            v1.iv = arg8.readString(16, false);
            Decoder v3 = arg8.readPointer(24, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.subsamples = new SubsampleEntry[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.subsamples[v5] = SubsampleEntry.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_37;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_37:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static DecryptConfig deserialize(ByteBuffer arg2) {
        return DecryptConfig.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DecryptConfig deserialize(Message arg1) {
        return DecryptConfig.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(DecryptConfig.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.keyId, v1, false);
        arg6.encode(this.iv, 16, false);
        int v3 = 24;
        if(this.subsamples == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.subsamples.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.subsamples.length; ++v0) {
                arg6.encode(this.subsamples[v0], v0 * 8 + v1, false);
            }
        }
    }
}

