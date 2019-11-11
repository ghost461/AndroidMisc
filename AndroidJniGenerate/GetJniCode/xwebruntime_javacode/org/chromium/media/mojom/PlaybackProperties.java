package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PlaybackProperties extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public int audioCodec;
    public boolean hasAudio;
    public boolean hasVideo;
    public boolean isBackground;
    public boolean isEmbeddedMediaExperience;
    public boolean isEme;
    public boolean isMse;
    public Size naturalSize;
    public int videoCodec;

    static {
        PlaybackProperties.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        PlaybackProperties.DEFAULT_STRUCT_INFO = PlaybackProperties.VERSION_ARRAY[0];
    }

    public PlaybackProperties() {
        this(0);
    }

    private PlaybackProperties(int arg2) {
        super(0x20, arg2);
    }

    public static PlaybackProperties decode(Decoder arg4) {
        PlaybackProperties v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new PlaybackProperties(arg4.readAndValidateDataHeader(PlaybackProperties.VERSION_ARRAY).elementsOrVersion);
            v1.audioCodec = arg4.readInt(8);
            AudioCodec.validate(v1.audioCodec);
            v1.videoCodec = arg4.readInt(12);
            VideoCodec.validate(v1.videoCodec);
            v1.hasAudio = arg4.readBoolean(16, 0);
            v1.hasVideo = arg4.readBoolean(16, 1);
            v1.isBackground = arg4.readBoolean(16, 2);
            v1.isMse = arg4.readBoolean(16, 3);
            v1.isEme = arg4.readBoolean(16, 4);
            v1.isEmbeddedMediaExperience = arg4.readBoolean(16, 5);
            v1.naturalSize = Size.decode(arg4.readPointer(24, false));
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static PlaybackProperties deserialize(ByteBuffer arg2) {
        return PlaybackProperties.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PlaybackProperties deserialize(Message arg1) {
        return PlaybackProperties.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(PlaybackProperties.DEFAULT_STRUCT_INFO);
        arg5.encode(this.audioCodec, 8);
        arg5.encode(this.videoCodec, 12);
        arg5.encode(this.hasAudio, 16, 0);
        arg5.encode(this.hasVideo, 16, 1);
        arg5.encode(this.isBackground, 16, 2);
        arg5.encode(this.isMse, 16, 3);
        arg5.encode(this.isEme, 16, 4);
        arg5.encode(this.isEmbeddedMediaExperience, 16, 5);
        arg5.encode(this.naturalSize, 24, false);
    }
}

