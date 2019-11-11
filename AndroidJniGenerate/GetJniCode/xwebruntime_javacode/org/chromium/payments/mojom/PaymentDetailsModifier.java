package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentDetailsModifier extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public PaymentItem[] additionalDisplayItems;
    public PaymentMethodData methodData;
    public PaymentItem total;

    static {
        PaymentDetailsModifier.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        PaymentDetailsModifier.DEFAULT_STRUCT_INFO = PaymentDetailsModifier.VERSION_ARRAY[0];
    }

    public PaymentDetailsModifier() {
        this(0);
    }

    private PaymentDetailsModifier(int arg2) {
        super(0x20, arg2);
    }

    public static PaymentDetailsModifier decode(Decoder arg8) {
        PaymentDetailsModifier v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new PaymentDetailsModifier(arg8.readAndValidateDataHeader(PaymentDetailsModifier.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            v1.total = PaymentItem.decode(arg8.readPointer(v2, true));
            Decoder v0_1 = arg8.readPointer(16, false);
            DataHeader v4 = v0_1.readDataHeaderForPointerArray(-1);
            v1.additionalDisplayItems = new PaymentItem[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.additionalDisplayItems[v5] = PaymentItem.decode(v0_1.readPointer(v5 * 8 + v2, false));
            }

            v1.methodData = PaymentMethodData.decode(arg8.readPointer(24, false));
        }
        catch(Throwable v0) {
            goto label_40;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_40:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static PaymentDetailsModifier deserialize(ByteBuffer arg2) {
        return PaymentDetailsModifier.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentDetailsModifier deserialize(Message arg1) {
        return PaymentDetailsModifier.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(PaymentDetailsModifier.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        arg7.encode(this.total, v2, true);
        int v1 = 16;
        if(this.additionalDisplayItems == null) {
            arg7.encodeNullPointer(v1, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.additionalDisplayItems.length, v1, -1);
            for(v1 = 0; v1 < this.additionalDisplayItems.length; ++v1) {
                v0.encode(this.additionalDisplayItems[v1], v1 * 8 + v2, false);
            }
        }

        arg7.encode(this.methodData, 24, false);
    }
}

