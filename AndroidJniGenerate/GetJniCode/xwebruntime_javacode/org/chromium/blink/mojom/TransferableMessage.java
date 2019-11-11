package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.skia.mojom.Bitmap;

public final class TransferableMessage extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public SerializedArrayBufferContents[] arrayBufferContentsArray;
    public boolean hasUserGesture;
    public Bitmap[] imageBitmapContentsArray;
    public CloneableMessage message;
    public MessagePipeHandle[] ports;

    static {
        TransferableMessage.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        TransferableMessage.DEFAULT_STRUCT_INFO = TransferableMessage.VERSION_ARRAY[0];
    }

    public TransferableMessage() {
        this(0);
    }

    private TransferableMessage(int arg2) {
        super(0x30, arg2);
    }

    public static TransferableMessage decode(Decoder arg9) {
        TransferableMessage v1;
        if(arg9 == null) {
            return null;
        }

        arg9.increaseStackDepth();
        try {
            v1 = new TransferableMessage(arg9.readAndValidateDataHeader(TransferableMessage.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.message = CloneableMessage.decode(arg9.readPointer(v0_1, false));
            int v4 = -1;
            v1.ports = arg9.readMessagePipeHandles(16, 0, v4);
            Decoder v3 = arg9.readPointer(24, false);
            DataHeader v5 = v3.readDataHeaderForPointerArray(v4);
            v1.arrayBufferContentsArray = new SerializedArrayBufferContents[v5.elementsOrVersion];
            int v6;
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.arrayBufferContentsArray[v6] = SerializedArrayBufferContents.decode(v3.readPointer(v6 * 8 + v0_1, false));
            }

            v3 = arg9.readPointer(0x20, false);
            DataHeader v4_1 = v3.readDataHeaderForPointerArray(v4);
            v1.imageBitmapContentsArray = new Bitmap[v4_1.elementsOrVersion];
            int v5_1;
            for(v5_1 = 0; v5_1 < v4_1.elementsOrVersion; ++v5_1) {
                v1.imageBitmapContentsArray[v5_1] = Bitmap.decode(v3.readPointer(v5_1 * 8 + v0_1, false));
            }

            v1.hasUserGesture = arg9.readBoolean(40, 0);
        }
        catch(Throwable v0) {
            goto label_58;
        }

        arg9.decreaseStackDepth();
        return v1;
    label_58:
        arg9.decreaseStackDepth();
        throw v0;
    }

    public static TransferableMessage deserialize(ByteBuffer arg2) {
        return TransferableMessage.deserialize(new Message(arg2, new ArrayList()));
    }

    public static TransferableMessage deserialize(Message arg1) {
        return TransferableMessage.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg8) {
        Encoder v0;
        arg8 = arg8.getEncoderAtDataOffset(TransferableMessage.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg8.encode(this.message, v1, false);
        int v3 = -1;
        arg8.encode(this.ports, 16, 0, v3);
        int v4 = 24;
        if(this.arrayBufferContentsArray == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.arrayBufferContentsArray.length, v4, v3);
            for(v4 = 0; v4 < this.arrayBufferContentsArray.length; ++v4) {
                v0.encode(this.arrayBufferContentsArray[v4], v4 * 8 + v1, false);
            }
        }

        v4 = 0x20;
        if(this.imageBitmapContentsArray == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.imageBitmapContentsArray.length, v4, v3);
            for(v3 = 0; v3 < this.imageBitmapContentsArray.length; ++v3) {
                v0.encode(this.imageBitmapContentsArray[v3], v3 * 8 + v1, false);
            }
        }

        arg8.encode(this.hasUserGesture, 40, 0);
    }
}

