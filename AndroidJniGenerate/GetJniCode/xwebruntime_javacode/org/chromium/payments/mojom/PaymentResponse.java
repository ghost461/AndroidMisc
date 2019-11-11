package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentResponse extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public String methodName;
    public String payerEmail;
    public String payerName;
    public String payerPhone;
    public PaymentAddress shippingAddress;
    public String shippingOption;
    public String stringifiedDetails;

    static {
        PaymentResponse.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        PaymentResponse.DEFAULT_STRUCT_INFO = PaymentResponse.VERSION_ARRAY[0];
    }

    public PaymentResponse() {
        this(0);
    }

    private PaymentResponse(int arg2) {
        super(0x40, arg2);
    }

    public static PaymentResponse decode(Decoder arg3) {
        PaymentResponse v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new PaymentResponse(arg3.readAndValidateDataHeader(PaymentResponse.VERSION_ARRAY).elementsOrVersion);
            v1.methodName = arg3.readString(8, false);
            v1.stringifiedDetails = arg3.readString(16, false);
            v1.shippingAddress = PaymentAddress.decode(arg3.readPointer(24, true));
            v1.shippingOption = arg3.readString(0x20, true);
            v1.payerName = arg3.readString(40, true);
            v1.payerEmail = arg3.readString(0x30, true);
            v1.payerPhone = arg3.readString(56, true);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static PaymentResponse deserialize(ByteBuffer arg2) {
        return PaymentResponse.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentResponse deserialize(Message arg1) {
        return PaymentResponse.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(PaymentResponse.DEFAULT_STRUCT_INFO);
        arg4.encode(this.methodName, 8, false);
        arg4.encode(this.stringifiedDetails, 16, false);
        arg4.encode(this.shippingAddress, 24, true);
        arg4.encode(this.shippingOption, 0x20, true);
        arg4.encode(this.payerName, 40, true);
        arg4.encode(this.payerEmail, 0x30, true);
        arg4.encode(this.payerPhone, 56, true);
    }
}

