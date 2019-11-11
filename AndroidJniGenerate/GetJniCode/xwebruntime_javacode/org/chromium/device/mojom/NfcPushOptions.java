package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NfcPushOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean ignoreRead;
    public int target;
    public double timeout;

    static {
        NfcPushOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        NfcPushOptions.DEFAULT_STRUCT_INFO = NfcPushOptions.VERSION_ARRAY[0];
    }

    public NfcPushOptions() {
        this(0);
    }

    private NfcPushOptions(int arg2) {
        super(24, arg2);
    }

    public static NfcPushOptions decode(Decoder arg4) {
        NfcPushOptions v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new NfcPushOptions(arg4.readAndValidateDataHeader(NfcPushOptions.VERSION_ARRAY).elementsOrVersion);
            v1.target = arg4.readInt(8);
            NfcPushTarget.validate(v1.target);
            v1.ignoreRead = arg4.readBoolean(12, 0);
            v1.timeout = arg4.readDouble(16);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static NfcPushOptions deserialize(ByteBuffer arg2) {
        return NfcPushOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NfcPushOptions deserialize(Message arg1) {
        return NfcPushOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(NfcPushOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.target, 8);
        arg4.encode(this.ignoreRead, 12, 0);
        arg4.encode(this.timeout, 16);
    }
}

