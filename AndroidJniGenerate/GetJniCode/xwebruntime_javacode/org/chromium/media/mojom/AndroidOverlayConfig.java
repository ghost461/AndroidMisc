package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Rect;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.UnguessableToken;

public final class AndroidOverlayConfig extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean powerEfficient;
    public Rect rect;
    public UnguessableToken routingToken;
    public boolean secure;

    static {
        AndroidOverlayConfig.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        AndroidOverlayConfig.DEFAULT_STRUCT_INFO = AndroidOverlayConfig.VERSION_ARRAY[0];
    }

    public AndroidOverlayConfig() {
        this(0);
    }

    private AndroidOverlayConfig(int arg2) {
        super(0x20, arg2);
    }

    public static AndroidOverlayConfig decode(Decoder arg3) {
        AndroidOverlayConfig v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new AndroidOverlayConfig(arg3.readAndValidateDataHeader(AndroidOverlayConfig.VERSION_ARRAY).elementsOrVersion);
            v1.routingToken = UnguessableToken.decode(arg3.readPointer(8, false));
            v1.rect = Rect.decode(arg3.readPointer(16, false));
            v1.secure = arg3.readBoolean(24, 0);
            v1.powerEfficient = arg3.readBoolean(24, 1);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static AndroidOverlayConfig deserialize(ByteBuffer arg2) {
        return AndroidOverlayConfig.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AndroidOverlayConfig deserialize(Message arg1) {
        return AndroidOverlayConfig.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(AndroidOverlayConfig.DEFAULT_STRUCT_INFO);
        arg4.encode(this.routingToken, 8, false);
        arg4.encode(this.rect, 16, false);
        arg4.encode(this.secure, 24, 0);
        arg4.encode(this.powerEfficient, 24, 1);
    }
}

