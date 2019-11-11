package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NfcMessage extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    public static final int MAX_SIZE = 0x8000;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public NfcRecord[] data;
    public String url;

    static {
        NfcMessage.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        NfcMessage.DEFAULT_STRUCT_INFO = NfcMessage.VERSION_ARRAY[0];
    }

    public NfcMessage() {
        this(0);
    }

    private NfcMessage(int arg2) {
        super(24, arg2);
    }

    public static NfcMessage decode(Decoder arg8) {
        NfcMessage v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new NfcMessage(arg8.readAndValidateDataHeader(NfcMessage.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.data = new NfcRecord[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.data[v5] = NfcRecord.decode(v3.readPointer(v5 * 8 + v2, false));
            }

            v1.url = arg8.readString(16, true);
        }
        catch(Throwable v0) {
            goto label_35;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_35:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static NfcMessage deserialize(ByteBuffer arg2) {
        return NfcMessage.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NfcMessage deserialize(Message arg1) {
        return NfcMessage.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(NfcMessage.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.data == null) {
            arg7.encodeNullPointer(v2, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.data.length, v2, -1);
            int v3;
            for(v3 = 0; v3 < this.data.length; ++v3) {
                v0.encode(this.data[v3], v3 * 8 + v2, false);
            }
        }

        arg7.encode(this.url, 16, true);
    }
}

