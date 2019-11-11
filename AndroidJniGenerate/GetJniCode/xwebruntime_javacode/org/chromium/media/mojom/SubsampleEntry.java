package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SubsampleEntry extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        SubsampleEntry.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        SubsampleEntry.DEFAULT_STRUCT_INFO = SubsampleEntry.VERSION_ARRAY[0];
    }

    public SubsampleEntry() {
        this(0);
    }

    private SubsampleEntry(int arg2) {
        super(8, arg2);
    }

    public static SubsampleEntry decode(Decoder arg2) {
        SubsampleEntry v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new SubsampleEntry(arg2.readAndValidateDataHeader(SubsampleEntry.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static SubsampleEntry deserialize(ByteBuffer arg2) {
        return SubsampleEntry.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SubsampleEntry deserialize(Message arg1) {
        return SubsampleEntry.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(SubsampleEntry.DEFAULT_STRUCT_INFO);
    }
}

