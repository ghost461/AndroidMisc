package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class AudioBuffer extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x30;
    private static final DataHeader[] VERSION_ARRAY;
    public int channelCount;
    public int channelLayout;
    public byte[] data;
    public boolean endOfStream;
    public int frameCount;
    public int sampleFormat;
    public int sampleRate;
    public TimeDelta timestamp;

    static {
        AudioBuffer.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
        AudioBuffer.DEFAULT_STRUCT_INFO = AudioBuffer.VERSION_ARRAY[0];
    }

    public AudioBuffer() {
        this(0);
    }

    private AudioBuffer(int arg2) {
        super(0x30, arg2);
    }

    public static AudioBuffer decode(Decoder arg4) {
        AudioBuffer v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new AudioBuffer(arg4.readAndValidateDataHeader(AudioBuffer.VERSION_ARRAY).elementsOrVersion);
            v1.sampleFormat = arg4.readInt(8);
            SampleFormat.validate(v1.sampleFormat);
            v1.channelLayout = arg4.readInt(12);
            ChannelLayout.validate(v1.channelLayout);
            v1.channelCount = arg4.readInt(16);
            v1.sampleRate = arg4.readInt(20);
            v1.frameCount = arg4.readInt(24);
            v1.endOfStream = arg4.readBoolean(28, 0);
            v1.timestamp = TimeDelta.decode(arg4.readPointer(0x20, false));
            v1.data = arg4.readBytes(40, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static AudioBuffer deserialize(ByteBuffer arg2) {
        return AudioBuffer.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AudioBuffer deserialize(Message arg1) {
        return AudioBuffer.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(AudioBuffer.DEFAULT_STRUCT_INFO);
        arg5.encode(this.sampleFormat, 8);
        arg5.encode(this.channelLayout, 12);
        arg5.encode(this.channelCount, 16);
        arg5.encode(this.sampleRate, 20);
        arg5.encode(this.frameCount, 24);
        arg5.encode(this.endOfStream, 28, 0);
        arg5.encode(this.timestamp, 0x20, false);
        arg5.encode(this.data, 40, 0, -1);
    }
}

