package org.chromium.blink.mojom.document_metadata;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class WebPage extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public Entity[] entities;
    public String title;
    public Url url;

    static {
        WebPage.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        WebPage.DEFAULT_STRUCT_INFO = WebPage.VERSION_ARRAY[0];
    }

    public WebPage() {
        this(0);
    }

    private WebPage(int arg2) {
        super(0x20, arg2);
    }

    public static WebPage decode(Decoder arg8) {
        WebPage v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new WebPage(arg8.readAndValidateDataHeader(WebPage.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.url = Url.decode(arg8.readPointer(v0_1, false));
            v1.title = arg8.readString(16, false);
            Decoder v3 = arg8.readPointer(24, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.entities = new Entity[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.entities[v5] = Entity.decode(v3.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_38;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_38:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static WebPage deserialize(ByteBuffer arg2) {
        return WebPage.deserialize(new Message(arg2, new ArrayList()));
    }

    public static WebPage deserialize(Message arg1) {
        return WebPage.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(WebPage.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.url, v1, false);
        arg6.encode(this.title, 16, false);
        int v3 = 24;
        if(this.entities == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.entities.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.entities.length; ++v0) {
                arg6.encode(this.entities[v0], v0 * 8 + v1, false);
            }
        }
    }
}

