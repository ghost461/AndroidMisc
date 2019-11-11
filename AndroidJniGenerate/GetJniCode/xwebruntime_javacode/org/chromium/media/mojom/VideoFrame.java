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
import org.chromium.mojo_base.mojom.DictionaryValue;
import org.chromium.mojo_base.mojom.TimeDelta;

public final class VideoFrame extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 72;
    private static final DataHeader[] VERSION_ARRAY;
    public Size codedSize;
    public VideoFrameData data;
    public int format;
    public DictionaryValue metadata;
    public Size naturalSize;
    public TimeDelta timestamp;
    public Rect visibleRect;

    static {
        VideoFrame.VERSION_ARRAY = new DataHeader[]{new DataHeader(72, 0)};
        VideoFrame.DEFAULT_STRUCT_INFO = VideoFrame.VERSION_ARRAY[0];
    }

    public VideoFrame() {
        this(0);
    }

    private VideoFrame(int arg2) {
        super(72, arg2);
    }

    public static VideoFrame decode(Decoder arg3) {
        VideoFrame v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new VideoFrame(arg3.readAndValidateDataHeader(VideoFrame.VERSION_ARRAY).elementsOrVersion);
            v1.format = arg3.readInt(8);
            VideoPixelFormat.validate(v1.format);
            v1.codedSize = Size.decode(arg3.readPointer(16, false));
            v1.visibleRect = Rect.decode(arg3.readPointer(24, false));
            v1.naturalSize = Size.decode(arg3.readPointer(0x20, false));
            v1.timestamp = TimeDelta.decode(arg3.readPointer(40, false));
            v1.data = VideoFrameData.decode(arg3, 0x30);
            v1.metadata = DictionaryValue.decode(arg3.readPointer(0x40, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static VideoFrame deserialize(ByteBuffer arg2) {
        return VideoFrame.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoFrame deserialize(Message arg1) {
        return VideoFrame.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(VideoFrame.DEFAULT_STRUCT_INFO);
        arg4.encode(this.format, 8);
        arg4.encode(this.codedSize, 16, false);
        arg4.encode(this.visibleRect, 24, false);
        arg4.encode(this.naturalSize, 0x20, false);
        arg4.encode(this.timestamp, 40, false);
        arg4.encode(this.data, 0x30, false);
        arg4.encode(this.metadata, 0x40, false);
    }
}

