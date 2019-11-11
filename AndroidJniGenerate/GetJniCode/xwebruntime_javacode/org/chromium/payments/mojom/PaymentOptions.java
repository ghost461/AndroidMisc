package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean requestPayerEmail;
    public boolean requestPayerName;
    public boolean requestPayerPhone;
    public boolean requestShipping;
    public int shippingType;

    static {
        PaymentOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        PaymentOptions.DEFAULT_STRUCT_INFO = PaymentOptions.VERSION_ARRAY[0];
    }

    public PaymentOptions() {
        this(0);
    }

    private PaymentOptions(int arg2) {
        super(16, arg2);
    }

    public static PaymentOptions decode(Decoder arg3) {
        PaymentOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PaymentOptions(arg3.readAndValidateDataHeader(PaymentOptions.VERSION_ARRAY).elementsOrVersion);
            v1.requestPayerName = arg3.readBoolean(8, 0);
            v1.requestPayerEmail = arg3.readBoolean(8, 1);
            v1.requestPayerPhone = arg3.readBoolean(8, 2);
            v1.requestShipping = arg3.readBoolean(8, 3);
            v1.shippingType = arg3.readInt(12);
            PaymentShippingType.validate(v1.shippingType);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PaymentOptions deserialize(ByteBuffer arg2) {
        return PaymentOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentOptions deserialize(Message arg1) {
        return PaymentOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PaymentOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.requestPayerName, 8, 0);
        arg4.encode(this.requestPayerEmail, 8, 1);
        arg4.encode(this.requestPayerPhone, 8, 2);
        arg4.encode(this.requestShipping, 8, 3);
        arg4.encode(this.shippingType, 12);
    }
}

