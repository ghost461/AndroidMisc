package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class PaymentMethodData extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 88;
    private static final DataHeader[] VERSION_ARRAY;
    public int[] allowedCardNetworks;
    public int apiVersion;
    public int environment;
    public String merchantId;
    public String merchantName;
    public int minGooglePlayServicesVersion;
    public AndroidPayTokenizationParameter[] parameters;
    public String stringifiedData;
    public String[] supportedMethods;
    public int[] supportedNetworks;
    public int[] supportedTypes;
    public int tokenizationType;

    static {
        PaymentMethodData.VERSION_ARRAY = new DataHeader[]{new DataHeader(88, 0)};
        PaymentMethodData.DEFAULT_STRUCT_INFO = PaymentMethodData.VERSION_ARRAY[0];
    }

    public PaymentMethodData() {
        this(0);
    }

    private PaymentMethodData(int arg2) {
        super(88, arg2);
    }

    public static PaymentMethodData decode(Decoder arg9) {
        PaymentMethodData v1;
        if(arg9 == null) {
            return null;
        }

        arg9.increaseStackDepth();
        try {
            v1 = new PaymentMethodData(arg9.readAndValidateDataHeader(PaymentMethodData.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            int v2 = 0;
            Decoder v3 = arg9.readPointer(v0_1, false);
            int v4 = -1;
            DataHeader v5 = v3.readDataHeaderForPointerArray(v4);
            v1.supportedMethods = new String[v5.elementsOrVersion];
            int v6;
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.supportedMethods[v6] = v3.readString(v6 * 8 + v0_1, false);
            }

            v1.stringifiedData = arg9.readString(16, false);
            v1.environment = arg9.readInt(24);
            AndroidPayEnvironment.validate(v1.environment);
            v1.tokenizationType = arg9.readInt(28);
            AndroidPayTokenization.validate(v1.tokenizationType);
            v1.merchantName = arg9.readString(0x20, true);
            v1.merchantId = arg9.readString(40, true);
            v1.allowedCardNetworks = arg9.readInts(0x30, 0, v4);
            int v3_1;
            for(v3_1 = 0; v3_1 < v1.allowedCardNetworks.length; ++v3_1) {
                AndroidPayCardNetwork.validate(v1.allowedCardNetworks[v3_1]);
            }

            v3 = arg9.readPointer(56, false);
            v5 = v3.readDataHeaderForPointerArray(v4);
            v1.parameters = new AndroidPayTokenizationParameter[v5.elementsOrVersion];
            for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                v1.parameters[v6] = AndroidPayTokenizationParameter.decode(v3.readPointer(v6 * 8 + v0_1, false));
            }

            v1.minGooglePlayServicesVersion = arg9.readInt(0x40);
            v1.apiVersion = arg9.readInt(68);
            v1.supportedNetworks = arg9.readInts(72, 0, v4);
            for(v0_1 = 0; v0_1 < v1.supportedNetworks.length; ++v0_1) {
                BasicCardNetwork.validate(v1.supportedNetworks[v0_1]);
            }

            v1.supportedTypes = arg9.readInts(80, 0, v4);
            while(v2 < v1.supportedTypes.length) {
                BasicCardType.validate(v1.supportedTypes[v2]);
                ++v2;
            }
        }
        catch(Throwable v0) {
            goto label_108;
        }

        arg9.decreaseStackDepth();
        return v1;
    label_108:
        arg9.decreaseStackDepth();
        throw v0;
    }

    public static PaymentMethodData deserialize(ByteBuffer arg2) {
        return PaymentMethodData.deserialize(new Message(arg2, new ArrayList()));
    }

    public static PaymentMethodData deserialize(Message arg1) {
        return PaymentMethodData.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg8) {
        Encoder v0;
        arg8 = arg8.getEncoderAtDataOffset(PaymentMethodData.DEFAULT_STRUCT_INFO);
        int v1 = -1;
        int v2 = 8;
        if(this.supportedMethods == null) {
            arg8.encodeNullPointer(v2, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.supportedMethods.length, v2, v1);
            int v4;
            for(v4 = 0; v4 < this.supportedMethods.length; ++v4) {
                v0.encode(this.supportedMethods[v4], v4 * 8 + v2, false);
            }
        }

        arg8.encode(this.stringifiedData, 16, false);
        arg8.encode(this.environment, 24);
        arg8.encode(this.tokenizationType, 28);
        arg8.encode(this.merchantName, 0x20, true);
        arg8.encode(this.merchantId, 40, true);
        arg8.encode(this.allowedCardNetworks, 0x30, 0, v1);
        v4 = 56;
        if(this.parameters == null) {
            arg8.encodeNullPointer(v4, false);
        }
        else {
            v0 = arg8.encodePointerArray(this.parameters.length, v4, v1);
            for(v4 = 0; v4 < this.parameters.length; ++v4) {
                v0.encode(this.parameters[v4], v4 * 8 + v2, false);
            }
        }

        arg8.encode(this.minGooglePlayServicesVersion, 0x40);
        arg8.encode(this.apiVersion, 68);
        arg8.encode(this.supportedNetworks, 72, 0, v1);
        arg8.encode(this.supportedTypes, 80, 0, v1);
    }
}

