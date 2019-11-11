package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentShippingOption extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public PaymentCurrencyAmount amount;
    public String id;
    public String label;
    public boolean selected;

    static {
        PaymentShippingOption.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        PaymentShippingOption.DEFAULT_STRUCT_INFO = PaymentShippingOption.VERSION_ARRAY[0];
    }

    public PaymentShippingOption() {
        this(0);
    }

    private PaymentShippingOption(int arg2) {
        super(40, arg2);
    }

    public static PaymentShippingOption decode(Decoder arg3) {
        PaymentShippingOption v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PaymentShippingOption(arg3.readAndValidateDataHeader(PaymentShippingOption.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg3.readString(8, false);
            v1.label = arg3.readString(16, false);
            v1.amount = PaymentCurrencyAmount.decode(arg3.readPointer(24, false));
            v1.selected = arg3.readBoolean(0x20, 0);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PaymentShippingOption deserialize(ByteBuffer arg2) {
        return PaymentShippingOption.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentShippingOption deserialize(Message arg1) {
        return PaymentShippingOption.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PaymentShippingOption.DEFAULT_STRUCT_INFO);
        arg4.encode(this.id, 8, false);
        arg4.encode(this.label, 16, false);
        arg4.encode(this.amount, 24, false);
        arg4.encode(this.selected, 0x20, 0);
    }
}

