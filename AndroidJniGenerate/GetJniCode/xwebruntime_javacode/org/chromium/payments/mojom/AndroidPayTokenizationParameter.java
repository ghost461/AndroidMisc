package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class AndroidPayTokenizationParameter extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public String key;
    public String value;

    static {
        AndroidPayTokenizationParameter.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        AndroidPayTokenizationParameter.DEFAULT_STRUCT_INFO = AndroidPayTokenizationParameter.VERSION_ARRAY[0];
    }

    public AndroidPayTokenizationParameter() {
        this(0);
    }

    private AndroidPayTokenizationParameter(int arg2) {
        super(24, arg2);
    }

    public static AndroidPayTokenizationParameter decode(Decoder arg3) {
        AndroidPayTokenizationParameter v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new AndroidPayTokenizationParameter(arg3.readAndValidateDataHeader(AndroidPayTokenizationParameter.VERSION_ARRAY).elementsOrVersion);
            v1.key = arg3.readString(8, true);
            v1.value = arg3.readString(16, true);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static AndroidPayTokenizationParameter deserialize(ByteBuffer arg2) {
        return AndroidPayTokenizationParameter.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AndroidPayTokenizationParameter deserialize(Message arg1) {
        return AndroidPayTokenizationParameter.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(AndroidPayTokenizationParameter.DEFAULT_STRUCT_INFO);
        arg4.encode(this.key, 8, true);
        arg4.encode(this.value, 16, true);
    }
}

