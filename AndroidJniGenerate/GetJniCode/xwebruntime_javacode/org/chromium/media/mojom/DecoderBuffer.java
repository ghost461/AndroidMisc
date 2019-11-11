package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class DecoderBuffer extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public TimeDelta backDiscard;
    public int dataSize;
    public DecryptConfig decryptConfig;
    public TimeDelta duration;
    public TimeDelta frontDiscard;
    public boolean isEndOfStream;
    public boolean isKeyFrame;
    public byte[] sideData;
    public TimeDelta timestamp;

    static {
        DecoderBuffer.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        DecoderBuffer.DEFAULT_STRUCT_INFO = DecoderBuffer.VERSION_ARRAY[0];
    }

    public DecoderBuffer() {
        this(0);
    }

    private DecoderBuffer(int arg2) {
        super(0x40, arg2);
    }

    public static DecoderBuffer decode(Decoder arg5) {
        DecoderBuffer v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new DecoderBuffer(arg5.readAndValidateDataHeader(DecoderBuffer.VERSION_ARRAY).elementsOrVersion);
            v1.timestamp = TimeDelta.decode(arg5.readPointer(8, false));
            v1.duration = TimeDelta.decode(arg5.readPointer(16, false));
            v1.isEndOfStream = arg5.readBoolean(24, 0);
            v1.isKeyFrame = arg5.readBoolean(24, 1);
            v1.dataSize = arg5.readInt(28);
            v1.sideData = arg5.readBytes(0x20, 0, -1);
            v1.decryptConfig = DecryptConfig.decode(arg5.readPointer(40, true));
            v1.frontDiscard = TimeDelta.decode(arg5.readPointer(0x30, false));
            v1.backDiscard = TimeDelta.decode(arg5.readPointer(56, false));
        }
        catch(Throwable v0) {
            arg5.decreaseStackDepth();
            throw v0;
        }

        arg5.decreaseStackDepth();
        return v1;
    }

    public static DecoderBuffer deserialize(ByteBuffer arg2) {
        return DecoderBuffer.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DecoderBuffer deserialize(Message arg1) {
        return DecoderBuffer.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(DecoderBuffer.DEFAULT_STRUCT_INFO);
        arg6.encode(this.timestamp, 8, false);
        arg6.encode(this.duration, 16, false);
        arg6.encode(this.isEndOfStream, 24, 0);
        arg6.encode(this.isKeyFrame, 24, 1);
        arg6.encode(this.dataSize, 28);
        arg6.encode(this.sideData, 0x20, 0, -1);
        arg6.encode(this.decryptConfig, 40, true);
        arg6.encode(this.frontDiscard, 0x30, false);
        arg6.encode(this.backDiscard, 56, false);
    }
}

