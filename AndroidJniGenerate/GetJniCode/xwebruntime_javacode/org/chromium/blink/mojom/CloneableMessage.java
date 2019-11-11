package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.BigBuffer;

public final class CloneableMessage extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public SerializedBlob[] blobs;
    public BigBuffer encodedMessage;
    public long stackTraceDebuggerIdFirst;
    public long stackTraceDebuggerIdSecond;
    public long stackTraceId;

    static {
        CloneableMessage.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        CloneableMessage.DEFAULT_STRUCT_INFO = CloneableMessage.VERSION_ARRAY[0];
    }

    public CloneableMessage() {
        this(0);
    }

    private CloneableMessage(int arg2) {
        super(56, arg2);
    }

    public static CloneableMessage decode(Decoder arg8) {
        CloneableMessage v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new CloneableMessage(arg8.readAndValidateDataHeader(CloneableMessage.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.encodedMessage = BigBuffer.decode(arg8, v0_1);
            Decoder v2 = arg8.readPointer(24, false);
            DataHeader v4 = v2.readDataHeaderForPointerArray(-1);
            v1.blobs = new SerializedBlob[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.blobs[v5] = SerializedBlob.decode(v2.readPointer(v5 * 8 + v0_1, false));
            }

            v1.stackTraceId = arg8.readLong(0x20);
            v1.stackTraceDebuggerIdFirst = arg8.readLong(40);
            v1.stackTraceDebuggerIdSecond = arg8.readLong(0x30);
        }
        catch(Throwable v0) {
            goto label_43;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_43:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static CloneableMessage deserialize(ByteBuffer arg2) {
        return CloneableMessage.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CloneableMessage deserialize(Message arg1) {
        return CloneableMessage.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(CloneableMessage.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg7.encode(this.encodedMessage, v1, false);
        int v3 = 24;
        if(this.blobs == null) {
            arg7.encodeNullPointer(v3, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.blobs.length, v3, -1);
            for(v3 = 0; v3 < this.blobs.length; ++v3) {
                v0.encode(this.blobs[v3], v3 * 8 + v1, false);
            }
        }

        arg7.encode(this.stackTraceId, 0x20);
        arg7.encode(this.stackTraceDebuggerIdFirst, 40);
        arg7.encode(this.stackTraceDebuggerIdSecond, 0x30);
    }
}

