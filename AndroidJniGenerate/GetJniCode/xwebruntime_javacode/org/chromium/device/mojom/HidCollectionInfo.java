package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class HidCollectionInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int[] reportIds;
    public HidUsageAndPage usage;

    static {
        HidCollectionInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        HidCollectionInfo.DEFAULT_STRUCT_INFO = HidCollectionInfo.VERSION_ARRAY[0];
    }

    public HidCollectionInfo() {
        this(0);
    }

    private HidCollectionInfo(int arg2) {
        super(24, arg2);
    }

    public static HidCollectionInfo decode(Decoder arg4) {
        HidCollectionInfo v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new HidCollectionInfo(arg4.readAndValidateDataHeader(HidCollectionInfo.VERSION_ARRAY).elementsOrVersion);
            v1.usage = HidUsageAndPage.decode(arg4.readPointer(8, false));
            v1.reportIds = arg4.readInts(16, 0, -1);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static HidCollectionInfo deserialize(ByteBuffer arg2) {
        return HidCollectionInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static HidCollectionInfo deserialize(Message arg1) {
        return HidCollectionInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(HidCollectionInfo.DEFAULT_STRUCT_INFO);
        arg5.encode(this.usage, 8, false);
        arg5.encode(this.reportIds, 16, 0, -1);
    }
}

