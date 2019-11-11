package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.String16;

public final class MediaMetadata extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public String16 album;
    public String16 artist;
    public MediaImage[] artwork;
    public String16 title;

    static {
        MediaMetadata.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        MediaMetadata.DEFAULT_STRUCT_INFO = MediaMetadata.VERSION_ARRAY[0];
    }

    public MediaMetadata() {
        this(0);
    }

    private MediaMetadata(int arg2) {
        super(40, arg2);
    }

    public static MediaMetadata decode(Decoder arg8) {
        MediaMetadata v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new MediaMetadata(arg8.readAndValidateDataHeader(MediaMetadata.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.title = String16.decode(arg8.readPointer(v0_1, false));
            v1.artist = String16.decode(arg8.readPointer(16, false));
            v1.album = String16.decode(arg8.readPointer(24, false));
            Decoder v3 = arg8.readPointer(0x20, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.artwork = new MediaImage[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.artwork[v5] = MediaImage.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_43;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_43:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static MediaMetadata deserialize(ByteBuffer arg2) {
        return MediaMetadata.deserialize(new Message(arg2, new ArrayList()));
    }

    public static MediaMetadata deserialize(Message arg1) {
        return MediaMetadata.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(MediaMetadata.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.title, v1, false);
        arg6.encode(this.artist, 16, false);
        arg6.encode(this.album, 24, false);
        int v3 = 0x20;
        if(this.artwork == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.artwork.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.artwork.length; ++v0) {
                arg6.encode(this.artwork[v0], v0 * 8 + v1, false);
            }
        }
    }
}

