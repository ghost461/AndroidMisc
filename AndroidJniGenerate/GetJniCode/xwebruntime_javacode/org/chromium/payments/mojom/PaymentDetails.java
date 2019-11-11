package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentDetails extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public PaymentItem[] displayItems;
    public String error;
    public String id;
    public PaymentDetailsModifier[] modifiers;
    public PaymentShippingOption[] shippingOptions;
    public PaymentItem total;

    static {
        PaymentDetails.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        PaymentDetails.DEFAULT_STRUCT_INFO = PaymentDetails.VERSION_ARRAY[0];
    }

    public PaymentDetails() {
        this(0);
    }

    private PaymentDetails(int arg2) {
        super(56, arg2);
        this.error = "";
    }

    public static PaymentDetails decode(Decoder arg10) {
        PaymentDetails v1;
        if(arg10 == null) {
            return null;
        }

        arg10.increaseStackDepth();
        try {
            v1 = new PaymentDetails(arg10.readAndValidateDataHeader(PaymentDetails.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            v1.total = PaymentItem.decode(arg10.readPointer(v2, true));
            Decoder v3 = arg10.readPointer(16, false);
            int v5 = -1;
            DataHeader v6 = v3.readDataHeaderForPointerArray(v5);
            v1.displayItems = new PaymentItem[v6.elementsOrVersion];
            int v7;
            for(v7 = 0; v7 < v6.elementsOrVersion; ++v7) {
                v1.displayItems[v7] = PaymentItem.decode(v3.readPointer(v7 * 8 + v2, false));
            }

            v3 = arg10.readPointer(24, false);
            v6 = v3.readDataHeaderForPointerArray(v5);
            v1.shippingOptions = new PaymentShippingOption[v6.elementsOrVersion];
            for(v7 = 0; v7 < v6.elementsOrVersion; ++v7) {
                v1.shippingOptions[v7] = PaymentShippingOption.decode(v3.readPointer(v7 * 8 + v2, false));
            }

            v3 = arg10.readPointer(0x20, false);
            DataHeader v5_1 = v3.readDataHeaderForPointerArray(v5);
            v1.modifiers = new PaymentDetailsModifier[v5_1.elementsOrVersion];
            int v6_1;
            for(v6_1 = 0; v6_1 < v5_1.elementsOrVersion; ++v6_1) {
                v1.modifiers[v6_1] = PaymentDetailsModifier.decode(v3.readPointer(v6_1 * 8 + v2, false));
            }

            v1.error = arg10.readString(40, false);
            v1.id = arg10.readString(0x30, true);
        }
        catch(Throwable v0) {
            goto label_76;
        }

        arg10.decreaseStackDepth();
        return v1;
    label_76:
        arg10.decreaseStackDepth();
        throw v0;
    }

    public static PaymentDetails deserialize(ByteBuffer arg2) {
        return PaymentDetails.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentDetails deserialize(Message arg1) {
        return PaymentDetails.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg9) {
        Encoder v0;
        arg9 = arg9.getEncoderAtDataOffset(PaymentDetails.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        arg9.encode(this.total, v2, true);
        int v3 = 16;
        int v4 = -1;
        if(this.displayItems == null) {
            arg9.encodeNullPointer(v3, false);
        }
        else {
            v0 = arg9.encodePointerArray(this.displayItems.length, v3, v4);
            for(v3 = 0; v3 < this.displayItems.length; ++v3) {
                v0.encode(this.displayItems[v3], v3 * 8 + v2, false);
            }
        }

        v3 = 24;
        if(this.shippingOptions == null) {
            arg9.encodeNullPointer(v3, false);
        }
        else {
            v0 = arg9.encodePointerArray(this.shippingOptions.length, v3, v4);
            for(v3 = 0; v3 < this.shippingOptions.length; ++v3) {
                v0.encode(this.shippingOptions[v3], v3 * 8 + v2, false);
            }
        }

        v3 = 0x20;
        if(this.modifiers == null) {
            arg9.encodeNullPointer(v3, false);
        }
        else {
            v0 = arg9.encodePointerArray(this.modifiers.length, v3, v4);
            for(v3 = 0; v3 < this.modifiers.length; ++v3) {
                v0.encode(this.modifiers[v3], v3 * 8 + v2, false);
            }
        }

        arg9.encode(this.error, 40, false);
        arg9.encode(this.id, 0x30, true);
    }
}

