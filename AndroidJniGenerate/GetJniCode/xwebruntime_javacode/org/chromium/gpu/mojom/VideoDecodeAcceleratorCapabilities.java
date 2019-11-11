package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoDecodeAcceleratorCapabilities extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int flags;
    public VideoDecodeAcceleratorSupportedProfile[] supportedProfiles;

    static {
        VideoDecodeAcceleratorCapabilities.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        VideoDecodeAcceleratorCapabilities.DEFAULT_STRUCT_INFO = VideoDecodeAcceleratorCapabilities.VERSION_ARRAY[0];
    }

    public VideoDecodeAcceleratorCapabilities() {
        this(0);
    }

    private VideoDecodeAcceleratorCapabilities(int arg2) {
        super(24, arg2);
    }

    public static VideoDecodeAcceleratorCapabilities decode(Decoder arg8) {
        VideoDecodeAcceleratorCapabilities v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new VideoDecodeAcceleratorCapabilities(arg8.readAndValidateDataHeader(VideoDecodeAcceleratorCapabilities.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.supportedProfiles = new VideoDecodeAcceleratorSupportedProfile[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.supportedProfiles[v5] = VideoDecodeAcceleratorSupportedProfile.decode(v3.readPointer(v5 * 8 + v2, false));
            }

            v1.flags = arg8.readInt(16);
        }
        catch(Throwable v0) {
            goto label_34;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_34:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static VideoDecodeAcceleratorCapabilities deserialize(ByteBuffer arg2) {
        return VideoDecodeAcceleratorCapabilities.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoDecodeAcceleratorCapabilities deserialize(Message arg1) {
        return VideoDecodeAcceleratorCapabilities.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(VideoDecodeAcceleratorCapabilities.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.supportedProfiles == null) {
            arg7.encodeNullPointer(v2, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.supportedProfiles.length, v2, -1);
            int v3;
            for(v3 = 0; v3 < this.supportedProfiles.length; ++v3) {
                v0.encode(this.supportedProfiles[v3], v3 * 8 + v2, false);
            }
        }

        arg7.encode(this.flags, 16);
    }
}

