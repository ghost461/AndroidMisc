package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.String16;
import org.chromium.url.mojom.Url;

public final class MediaImage extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public Size[] sizes;
    public Url src;
    public String16 type;

    static {
        MediaImage.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        MediaImage.DEFAULT_STRUCT_INFO = MediaImage.VERSION_ARRAY[0];
    }

    public MediaImage() {
        this(0);
    }

    private MediaImage(int arg2) {
        super(0x20, arg2);
    }

    public static MediaImage decode(Decoder arg8) {
        MediaImage v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new MediaImage(arg8.readAndValidateDataHeader(MediaImage.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.src = Url.decode(arg8.readPointer(v0_1, false));
            v1.type = String16.decode(arg8.readPointer(16, false));
            Decoder v3 = arg8.readPointer(24, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.sizes = new Size[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.sizes[v5] = Size.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_39;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_39:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static MediaImage deserialize(ByteBuffer arg2) {
        return MediaImage.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MediaImage deserialize(Message arg1) {
        return MediaImage.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(MediaImage.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.src, v1, false);
        arg6.encode(this.type, 16, false);
        int v3 = 24;
        if(this.sizes == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.sizes.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.sizes.length; ++v0) {
                arg6.encode(this.sizes[v0], v0 * 8 + v1, false);
            }
        }
    }
}

