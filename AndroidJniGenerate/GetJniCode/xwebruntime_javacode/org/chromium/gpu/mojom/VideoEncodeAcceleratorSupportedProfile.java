package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoEncodeAcceleratorSupportedProfile extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public int maxFramerateDenominator;
    public int maxFramerateNumerator;
    public Size maxResolution;
    public int profile;

    static {
        VideoEncodeAcceleratorSupportedProfile.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        VideoEncodeAcceleratorSupportedProfile.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorSupportedProfile.VERSION_ARRAY[0];
    }

    public VideoEncodeAcceleratorSupportedProfile() {
        this(0);
    }

    private VideoEncodeAcceleratorSupportedProfile(int arg2) {
        super(0x20, arg2);
    }

    public static VideoEncodeAcceleratorSupportedProfile decode(Decoder arg3) {
        VideoEncodeAcceleratorSupportedProfile v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new VideoEncodeAcceleratorSupportedProfile(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorSupportedProfile.VERSION_ARRAY).elementsOrVersion);
            v1.profile = arg3.readInt(8);
            VideoCodecProfile.validate(v1.profile);
            v1.maxFramerateNumerator = arg3.readInt(12);
            v1.maxResolution = Size.decode(arg3.readPointer(16, false));
            v1.maxFramerateDenominator = arg3.readInt(24);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static VideoEncodeAcceleratorSupportedProfile deserialize(ByteBuffer arg2) {
        return VideoEncodeAcceleratorSupportedProfile.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoEncodeAcceleratorSupportedProfile deserialize(Message arg1) {
        return VideoEncodeAcceleratorSupportedProfile.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorSupportedProfile.DEFAULT_STRUCT_INFO);
        arg4.encode(this.profile, 8);
        arg4.encode(this.maxFramerateNumerator, 12);
        arg4.encode(this.maxResolution, 16, false);
        arg4.encode(this.maxFramerateDenominator, 24);
    }
}

