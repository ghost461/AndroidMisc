package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentAddress extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 104;
    private static final DataHeader[] VERSION_ARRAY;
    public String[] addressLine;
    public String city;
    public String country;
    public String dependentLocality;
    public String languageCode;
    public String organization;
    public String phone;
    public String postalCode;
    public String recipient;
    public String region;
    public String scriptCode;
    public String sortingCode;

    static {
        PaymentAddress.VERSION_ARRAY = new DataHeader[]{new DataHeader(104, 0)};
        PaymentAddress.DEFAULT_STRUCT_INFO = PaymentAddress.VERSION_ARRAY[0];
    }

    public PaymentAddress() {
        this(0);
    }

    private PaymentAddress(int arg2) {
        super(104, arg2);
    }

    public static PaymentAddress decode(Decoder arg8) {
        PaymentAddress v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new PaymentAddress(arg8.readAndValidateDataHeader(PaymentAddress.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.country = arg8.readString(v0_1, false);
            Decoder v3 = arg8.readPointer(16, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.addressLine = new String[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.addressLine[v5] = v3.readString(v5 * 8 + v0_1, false);
            }

            v1.region = arg8.readString(24, false);
            v1.city = arg8.readString(0x20, false);
            v1.dependentLocality = arg8.readString(40, false);
            v1.postalCode = arg8.readString(0x30, false);
            v1.sortingCode = arg8.readString(56, false);
            v1.languageCode = arg8.readString(0x40, false);
            v1.scriptCode = arg8.readString(72, false);
            v1.organization = arg8.readString(80, false);
            v1.recipient = arg8.readString(88, false);
            v1.phone = arg8.readString(0x60, false);
        }
        catch(Throwable v0) {
            goto label_63;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_63:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static PaymentAddress deserialize(ByteBuffer arg2) {
        return PaymentAddress.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentAddress deserialize(Message arg1) {
        return PaymentAddress.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg7) {
        arg7 = arg7.getEncoderAtDataOffset(PaymentAddress.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg7.encode(this.country, v1, false);
        int v3 = 16;
        if(this.addressLine == null) {
            arg7.encodeNullPointer(v3, false);
        }
        else {
            Encoder v0 = arg7.encodePointerArray(this.addressLine.length, v3, -1);
            for(v3 = 0; v3 < this.addressLine.length; ++v3) {
                v0.encode(this.addressLine[v3], v3 * 8 + v1, false);
            }
        }

        arg7.encode(this.region, 24, false);
        arg7.encode(this.city, 0x20, false);
        arg7.encode(this.dependentLocality, 40, false);
        arg7.encode(this.postalCode, 0x30, false);
        arg7.encode(this.sortingCode, 56, false);
        arg7.encode(this.languageCode, 0x40, false);
        arg7.encode(this.scriptCode, 72, false);
        arg7.encode(this.organization, 80, false);
        arg7.encode(this.recipient, 88, false);
        arg7.encode(this.phone, 0x60, false);
    }
}

