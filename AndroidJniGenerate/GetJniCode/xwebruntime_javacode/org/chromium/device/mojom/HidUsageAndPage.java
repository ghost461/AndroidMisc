package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HidUsageAndPage extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public short usage;
    public short usagePage;

    static {
        HidUsageAndPage.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        HidUsageAndPage.DEFAULT_STRUCT_INFO = HidUsageAndPage.VERSION_ARRAY[0];
    }

    public HidUsageAndPage() {
        this(0);
    }

    private HidUsageAndPage(int arg2) {
        super(16, arg2);
    }

    public static HidUsageAndPage decode(Decoder arg2) {
        HidUsageAndPage v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new HidUsageAndPage(arg2.readAndValidateDataHeader(HidUsageAndPage.VERSION_ARRAY).elementsOrVersion);
            v1.usage = arg2.readShort(8);
            v1.usagePage = arg2.readShort(10);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static HidUsageAndPage deserialize(ByteBuffer arg2) {
        return HidUsageAndPage.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HidUsageAndPage deserialize(Message arg1) {
        return HidUsageAndPage.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg3) {
        arg3 = arg3.getEncoderAtDataOffset(HidUsageAndPage.DEFAULT_STRUCT_INFO);
        arg3.encode(this.usage, 8);
        arg3.encode(this.usagePage, 10);
    }
}

