package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoDecodeAcceleratorSupportedProfile extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean encryptedOnly;
    public Size maxResolution;
    public Size minResolution;
    public int profile;

    static {
        VideoDecodeAcceleratorSupportedProfile.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        VideoDecodeAcceleratorSupportedProfile.DEFAULT_STRUCT_INFO = VideoDecodeAcceleratorSupportedProfile.VERSION_ARRAY[0];
    }

    public VideoDecodeAcceleratorSupportedProfile() {
        this(0);
    }

    private VideoDecodeAcceleratorSupportedProfile(int arg2) {
        super(0x20, arg2);
    }

    public static VideoDecodeAcceleratorSupportedProfile decode(Decoder arg3) {
        VideoDecodeAcceleratorSupportedProfile v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new VideoDecodeAcceleratorSupportedProfile(arg3.readAndValidateDataHeader(VideoDecodeAcceleratorSupportedProfile.VERSION_ARRAY).elementsOrVersion);
            v1.profile = arg3.readInt(8);
            VideoCodecProfile.validate(v1.profile);
            v1.encryptedOnly = arg3.readBoolean(12, 0);
            v1.maxResolution = Size.decode(arg3.readPointer(16, false));
            v1.minResolution = Size.decode(arg3.readPointer(24, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static VideoDecodeAcceleratorSupportedProfile deserialize(ByteBuffer arg2) {
        return VideoDecodeAcceleratorSupportedProfile.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoDecodeAcceleratorSupportedProfile deserialize(Message arg1) {
        return VideoDecodeAcceleratorSupportedProfile.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(VideoDecodeAcceleratorSupportedProfile.DEFAULT_STRUCT_INFO);
        arg4.encode(this.profile, 8);
        arg4.encode(this.encryptedOnly, 12, 0);
        arg4.encode(this.maxResolution, 16, false);
        arg4.encode(this.minResolution, 24, false);
    }
}

