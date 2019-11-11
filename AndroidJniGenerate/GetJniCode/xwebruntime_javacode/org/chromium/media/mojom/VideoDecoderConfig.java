package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Rect;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoDecoderConfig extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 88;
    private static final DataHeader[] VERSION_ARRAY;
    public int codec;
    public Size codedSize;
    public int colorSpace;
    public VideoColorSpace colorSpaceInfo;
    public EncryptionScheme encryptionScheme;
    public byte[] extraData;
    public int format;
    public HdrMetadata hdrMetadata;
    public Size naturalSize;
    public int profile;
    public int videoRotation;
    public Rect visibleRect;

    static {
        VideoDecoderConfig.VERSION_ARRAY = new DataHeader[]{new DataHeader(88, 0)};
        VideoDecoderConfig.DEFAULT_STRUCT_INFO = VideoDecoderConfig.VERSION_ARRAY[0];
    }

    public VideoDecoderConfig() {
        this(0);
    }

    private VideoDecoderConfig(int arg2) {
        super(88, arg2);
    }

    public static VideoDecoderConfig decode(Decoder arg4) {
        VideoDecoderConfig v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new VideoDecoderConfig(arg4.readAndValidateDataHeader(VideoDecoderConfig.VERSION_ARRAY).elementsOrVersion);
            v1.codec = arg4.readInt(8);
            VideoCodec.validate(v1.codec);
            v1.profile = arg4.readInt(12);
            VideoCodecProfile.validate(v1.profile);
            v1.format = arg4.readInt(16);
            VideoPixelFormat.validate(v1.format);
            v1.colorSpace = arg4.readInt(20);
            ColorSpace.validate(v1.colorSpace);
            v1.videoRotation = arg4.readInt(24);
            VideoRotation.validate(v1.videoRotation);
            v1.codedSize = Size.decode(arg4.readPointer(0x20, false));
            v1.visibleRect = Rect.decode(arg4.readPointer(40, false));
            v1.naturalSize = Size.decode(arg4.readPointer(0x30, false));
            v1.extraData = arg4.readBytes(56, 0, -1);
            v1.encryptionScheme = EncryptionScheme.decode(arg4.readPointer(0x40, false));
            v1.colorSpaceInfo = VideoColorSpace.decode(arg4.readPointer(72, false));
            v1.hdrMetadata = HdrMetadata.decode(arg4.readPointer(80, true));
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static VideoDecoderConfig deserialize(ByteBuffer arg2) {
        return VideoDecoderConfig.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoDecoderConfig deserialize(Message arg1) {
        return VideoDecoderConfig.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(VideoDecoderConfig.DEFAULT_STRUCT_INFO);
        arg5.encode(this.codec, 8);
        arg5.encode(this.profile, 12);
        arg5.encode(this.format, 16);
        arg5.encode(this.colorSpace, 20);
        arg5.encode(this.videoRotation, 24);
        arg5.encode(this.codedSize, 0x20, false);
        arg5.encode(this.visibleRect, 40, false);
        arg5.encode(this.naturalSize, 0x30, false);
        arg5.encode(this.extraData, 56, 0, -1);
        arg5.encode(this.encryptionScheme, 0x40, false);
        arg5.encode(this.colorSpaceInfo, 72, false);
        arg5.encode(this.hdrMetadata, 80, true);
    }
}

