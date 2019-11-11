package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class AudioDecoderConfig extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public int channelLayout;
    public int codec;
    public int codecDelay;
    public EncryptionScheme encryptionScheme;
    public byte[] extraData;
    public int sampleFormat;
    public int samplesPerSecond;
    public TimeDelta seekPreroll;

    static {
        AudioDecoderConfig.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        AudioDecoderConfig.DEFAULT_STRUCT_INFO = AudioDecoderConfig.VERSION_ARRAY[0];
    }

    public AudioDecoderConfig() {
        this(0);
    }

    private AudioDecoderConfig(int arg2) {
        super(56, arg2);
    }

    public static AudioDecoderConfig decode(Decoder arg4) {
        AudioDecoderConfig v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new AudioDecoderConfig(arg4.readAndValidateDataHeader(AudioDecoderConfig.VERSION_ARRAY).elementsOrVersion);
            v1.codec = arg4.readInt(8);
            AudioCodec.validate(v1.codec);
            v1.sampleFormat = arg4.readInt(12);
            SampleFormat.validate(v1.sampleFormat);
            v1.channelLayout = arg4.readInt(16);
            ChannelLayout.validate(v1.channelLayout);
            v1.samplesPerSecond = arg4.readInt(20);
            v1.extraData = arg4.readBytes(24, 0, -1);
            v1.seekPreroll = TimeDelta.decode(arg4.readPointer(0x20, false));
            v1.codecDelay = arg4.readInt(40);
            v1.encryptionScheme = EncryptionScheme.decode(arg4.readPointer(0x30, false));
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static AudioDecoderConfig deserialize(ByteBuffer arg2) {
        return AudioDecoderConfig.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AudioDecoderConfig deserialize(Message arg1) {
        return AudioDecoderConfig.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(AudioDecoderConfig.DEFAULT_STRUCT_INFO);
        arg5.encode(this.codec, 8);
        arg5.encode(this.sampleFormat, 12);
        arg5.encode(this.channelLayout, 16);
        arg5.encode(this.samplesPerSecond, 20);
        arg5.encode(this.extraData, 24, 0, -1);
        arg5.encode(this.seekPreroll, 0x20, false);
        arg5.encode(this.codecDelay, 40);
        arg5.encode(this.encryptionScheme, 0x30, false);
    }
}

