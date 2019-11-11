package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentItem extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public PaymentCurrencyAmount amount;
    public String label;
    public boolean pending;

    static {
        PaymentItem.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        PaymentItem.DEFAULT_STRUCT_INFO = PaymentItem.VERSION_ARRAY[0];
    }

    public PaymentItem() {
        this(0);
    }

    private PaymentItem(int arg2) {
        super(0x20, arg2);
    }

    public static PaymentItem decode(Decoder arg3) {
        PaymentItem v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PaymentItem(arg3.readAndValidateDataHeader(PaymentItem.VERSION_ARRAY).elementsOrVersion);
            v1.label = arg3.readString(8, false);
            v1.amount = PaymentCurrencyAmount.decode(arg3.readPointer(16, false));
            v1.pending = arg3.readBoolean(24, 0);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PaymentItem deserialize(ByteBuffer arg2) {
        return PaymentItem.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentItem deserialize(Message arg1) {
        return PaymentItem.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PaymentItem.DEFAULT_STRUCT_INFO);
        arg4.encode(this.label, 8, false);
        arg4.encode(this.amount, 16, false);
        arg4.encode(this.pending, 24, 0);
    }
}

