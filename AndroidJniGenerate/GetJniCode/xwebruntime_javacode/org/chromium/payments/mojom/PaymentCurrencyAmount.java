package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentCurrencyAmount extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String currency;
    public String currencySystem;
    public String value;

    static {
        PaymentCurrencyAmount.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        PaymentCurrencyAmount.DEFAULT_STRUCT_INFO = PaymentCurrencyAmount.VERSION_ARRAY[0];
    }

    public PaymentCurrencyAmount() {
        this(0);
    }

    private PaymentCurrencyAmount(int arg2) {
        super(0x20, arg2);
        this.currencySystem = "urn:iso:std:iso:4217";
    }

    public static PaymentCurrencyAmount decode(Decoder arg3) {
        PaymentCurrencyAmount v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PaymentCurrencyAmount(arg3.readAndValidateDataHeader(PaymentCurrencyAmount.VERSION_ARRAY).elementsOrVersion);
            v1.currency = arg3.readString(8, false);
            v1.value = arg3.readString(16, false);
            v1.currencySystem = arg3.readString(24, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PaymentCurrencyAmount deserialize(ByteBuffer arg2) {
        return PaymentCurrencyAmount.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentCurrencyAmount deserialize(Message arg1) {
        return PaymentCurrencyAmount.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PaymentCurrencyAmount.DEFAULT_STRUCT_INFO);
        arg4.encode(this.currency, 8, false);
        arg4.encode(this.value, 16, false);
        arg4.encode(this.currencySystem, 24, false);
    }
}

