package org.chromium.payments.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface$Proxy;
import org.chromium.mojo.bindings.Interface$Stub;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class PaymentRequestClient_Internal {
    final class org.chromium.payments.mojom.PaymentRequestClient_Internal$1 extends Manager {
        org.chromium.payments.mojom.PaymentRequestClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public PaymentRequestClient[] buildArray(int arg1) {
            return new PaymentRequestClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.payments.mojom.PaymentRequestClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.payments.mojom.PaymentRequestClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PaymentRequestClient)arg2));
        }

        public org.chromium.payments.mojom.PaymentRequestClient_Internal$Stub buildStub(Core arg2, PaymentRequestClient arg3) {
            return new org.chromium.payments.mojom.PaymentRequestClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "payments::mojom::PaymentRequestClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class PaymentRequestClientOnAbortParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean abortedSuccessfully;

        static {
            PaymentRequestClientOnAbortParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestClientOnAbortParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnAbortParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnAbortParams() {
            this(0);
        }

        private PaymentRequestClientOnAbortParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestClientOnAbortParams decode(Decoder arg3) {
            PaymentRequestClientOnAbortParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnAbortParams(arg3.readAndValidateDataHeader(PaymentRequestClientOnAbortParams.VERSION_ARRAY).elementsOrVersion);
                v1.abortedSuccessfully = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnAbortParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnAbortParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnAbortParams deserialize(Message arg1) {
            return PaymentRequestClientOnAbortParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PaymentRequestClientOnAbortParams.DEFAULT_STRUCT_INFO).encode(this.abortedSuccessfully, 8, 0);
        }
    }

    final class PaymentRequestClientOnCanMakePaymentParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            PaymentRequestClientOnCanMakePaymentParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestClientOnCanMakePaymentParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnCanMakePaymentParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnCanMakePaymentParams() {
            this(0);
        }

        private PaymentRequestClientOnCanMakePaymentParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestClientOnCanMakePaymentParams decode(Decoder arg2) {
            PaymentRequestClientOnCanMakePaymentParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnCanMakePaymentParams(arg2.readAndValidateDataHeader(PaymentRequestClientOnCanMakePaymentParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
                CanMakePaymentQueryResult.validate(v1.result);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnCanMakePaymentParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnCanMakePaymentParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnCanMakePaymentParams deserialize(Message arg1) {
            return PaymentRequestClientOnCanMakePaymentParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(PaymentRequestClientOnCanMakePaymentParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    final class PaymentRequestClientOnCompleteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PaymentRequestClientOnCompleteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PaymentRequestClientOnCompleteParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnCompleteParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnCompleteParams() {
            this(0);
        }

        private PaymentRequestClientOnCompleteParams(int arg2) {
            super(8, arg2);
        }

        public static PaymentRequestClientOnCompleteParams decode(Decoder arg2) {
            PaymentRequestClientOnCompleteParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnCompleteParams(arg2.readAndValidateDataHeader(PaymentRequestClientOnCompleteParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnCompleteParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnCompleteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnCompleteParams deserialize(Message arg1) {
            return PaymentRequestClientOnCompleteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PaymentRequestClientOnCompleteParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class PaymentRequestClientOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;

        static {
            PaymentRequestClientOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestClientOnErrorParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnErrorParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnErrorParams() {
            this(0);
        }

        private PaymentRequestClientOnErrorParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestClientOnErrorParams decode(Decoder arg2) {
            PaymentRequestClientOnErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnErrorParams(arg2.readAndValidateDataHeader(PaymentRequestClientOnErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg2.readInt(8);
                PaymentErrorReason.validate(v1.error);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnErrorParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnErrorParams deserialize(Message arg1) {
            return PaymentRequestClientOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(PaymentRequestClientOnErrorParams.DEFAULT_STRUCT_INFO).encode(this.error, 8);
        }
    }

    final class PaymentRequestClientOnPaymentResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PaymentResponse response;

        static {
            PaymentRequestClientOnPaymentResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestClientOnPaymentResponseParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnPaymentResponseParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnPaymentResponseParams() {
            this(0);
        }

        private PaymentRequestClientOnPaymentResponseParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestClientOnPaymentResponseParams decode(Decoder arg3) {
            PaymentRequestClientOnPaymentResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnPaymentResponseParams(arg3.readAndValidateDataHeader(PaymentRequestClientOnPaymentResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.response = PaymentResponse.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnPaymentResponseParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnPaymentResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnPaymentResponseParams deserialize(Message arg1) {
            return PaymentRequestClientOnPaymentResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PaymentRequestClientOnPaymentResponseParams.DEFAULT_STRUCT_INFO).encode(this.response, 8, false);
        }
    }

    final class PaymentRequestClientOnShippingAddressChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PaymentAddress address;

        static {
            PaymentRequestClientOnShippingAddressChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestClientOnShippingAddressChangeParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnShippingAddressChangeParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnShippingAddressChangeParams() {
            this(0);
        }

        private PaymentRequestClientOnShippingAddressChangeParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestClientOnShippingAddressChangeParams decode(Decoder arg3) {
            PaymentRequestClientOnShippingAddressChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnShippingAddressChangeParams(arg3.readAndValidateDataHeader(PaymentRequestClientOnShippingAddressChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.address = PaymentAddress.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnShippingAddressChangeParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnShippingAddressChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnShippingAddressChangeParams deserialize(Message arg1) {
            return PaymentRequestClientOnShippingAddressChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PaymentRequestClientOnShippingAddressChangeParams.DEFAULT_STRUCT_INFO).encode(this.address, 8, false);
        }
    }

    final class PaymentRequestClientOnShippingOptionChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String shippingOptionId;

        static {
            PaymentRequestClientOnShippingOptionChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestClientOnShippingOptionChangeParams.DEFAULT_STRUCT_INFO = PaymentRequestClientOnShippingOptionChangeParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientOnShippingOptionChangeParams() {
            this(0);
        }

        private PaymentRequestClientOnShippingOptionChangeParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestClientOnShippingOptionChangeParams decode(Decoder arg3) {
            PaymentRequestClientOnShippingOptionChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientOnShippingOptionChangeParams(arg3.readAndValidateDataHeader(PaymentRequestClientOnShippingOptionChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.shippingOptionId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientOnShippingOptionChangeParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientOnShippingOptionChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientOnShippingOptionChangeParams deserialize(Message arg1) {
            return PaymentRequestClientOnShippingOptionChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PaymentRequestClientOnShippingOptionChangeParams.DEFAULT_STRUCT_INFO).encode(this.shippingOptionId, 8, false);
        }
    }

    final class PaymentRequestClientWarnNoFaviconParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PaymentRequestClientWarnNoFaviconParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PaymentRequestClientWarnNoFaviconParams.DEFAULT_STRUCT_INFO = PaymentRequestClientWarnNoFaviconParams.VERSION_ARRAY[0];
        }

        public PaymentRequestClientWarnNoFaviconParams() {
            this(0);
        }

        private PaymentRequestClientWarnNoFaviconParams(int arg2) {
            super(8, arg2);
        }

        public static PaymentRequestClientWarnNoFaviconParams decode(Decoder arg2) {
            PaymentRequestClientWarnNoFaviconParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestClientWarnNoFaviconParams(arg2.readAndValidateDataHeader(PaymentRequestClientWarnNoFaviconParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestClientWarnNoFaviconParams deserialize(ByteBuffer arg2) {
            return PaymentRequestClientWarnNoFaviconParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestClientWarnNoFaviconParams deserialize(Message arg1) {
            return PaymentRequestClientWarnNoFaviconParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PaymentRequestClientWarnNoFaviconParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class org.chromium.payments.mojom.PaymentRequestClient_Internal$Proxy extends AbstractProxy implements org.chromium.payments.mojom.PaymentRequestClient$Proxy {
        org.chromium.payments.mojom.PaymentRequestClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onAbort(boolean arg5) {
            PaymentRequestClientOnAbortParams v0 = new PaymentRequestClientOnAbortParams();
            v0.abortedSuccessfully = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void onCanMakePayment(int arg5) {
            PaymentRequestClientOnCanMakePaymentParams v0 = new PaymentRequestClientOnCanMakePaymentParams();
            v0.result = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void onComplete() {
            this.getProxyHandler().getMessageReceiver().accept(new PaymentRequestClientOnCompleteParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void onError(int arg5) {
            PaymentRequestClientOnErrorParams v0 = new PaymentRequestClientOnErrorParams();
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onPaymentResponse(PaymentResponse arg5) {
            PaymentRequestClientOnPaymentResponseParams v0 = new PaymentRequestClientOnPaymentResponseParams();
            v0.response = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onShippingAddressChange(PaymentAddress arg5) {
            PaymentRequestClientOnShippingAddressChangeParams v0 = new PaymentRequestClientOnShippingAddressChangeParams();
            v0.address = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onShippingOptionChange(String arg5) {
            PaymentRequestClientOnShippingOptionChangeParams v0 = new PaymentRequestClientOnShippingOptionChangeParams();
            v0.shippingOptionId = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void warnNoFavicon() {
            this.getProxyHandler().getMessageReceiver().accept(new PaymentRequestClientWarnNoFaviconParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }
    }

    final class org.chromium.payments.mojom.PaymentRequestClient_Internal$Stub extends Stub {
        org.chromium.payments.mojom.PaymentRequestClient_Internal$Stub(Core arg1, PaymentRequestClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_58;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_52;
                    }
                    case 1: {
                        goto label_46;
                    }
                    case 2: {
                        goto label_40;
                    }
                    case 3: {
                        goto label_34;
                    }
                    case 4: {
                        goto label_29;
                    }
                    case 5: {
                        goto label_23;
                    }
                    case 6: {
                        goto label_17;
                    }
                    case 7: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                this.getImpl().onCanMakePayment(PaymentRequestClientOnCanMakePaymentParams.deserialize(v4_1.getPayload()).result);
                return 1;
            label_34:
                this.getImpl().onError(PaymentRequestClientOnErrorParams.deserialize(v4_1.getPayload()).error);
                return 1;
            label_52:
                this.getImpl().onShippingAddressChange(PaymentRequestClientOnShippingAddressChangeParams.deserialize(v4_1.getPayload()).address);
                return 1;
            label_23:
                this.getImpl().onAbort(PaymentRequestClientOnAbortParams.deserialize(v4_1.getPayload()).abortedSuccessfully);
                return 1;
            label_40:
                this.getImpl().onPaymentResponse(PaymentRequestClientOnPaymentResponseParams.deserialize(v4_1.getPayload()).response);
                return 1;
            label_12:
                PaymentRequestClientWarnNoFaviconParams.deserialize(v4_1.getPayload());
                this.getImpl().warnNoFavicon();
                return 1;
            label_29:
                PaymentRequestClientOnCompleteParams.deserialize(v4_1.getPayload());
                this.getImpl().onComplete();
                return 1;
            label_46:
                this.getImpl().onShippingOptionChange(PaymentRequestClientOnShippingOptionChangeParams.deserialize(v4_1.getPayload()).shippingOptionId);
                return 1;
            label_58:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PaymentRequestClient_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg4, MessageReceiver arg5) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                if(v1.getType() != -1) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PaymentRequestClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_ABORT_ORDINAL = 5;
    private static final int ON_CAN_MAKE_PAYMENT_ORDINAL = 6;
    private static final int ON_COMPLETE_ORDINAL = 4;
    private static final int ON_ERROR_ORDINAL = 3;
    private static final int ON_PAYMENT_RESPONSE_ORDINAL = 2;
    private static final int ON_SHIPPING_ADDRESS_CHANGE_ORDINAL = 0;
    private static final int ON_SHIPPING_OPTION_CHANGE_ORDINAL = 1;
    private static final int WARN_NO_FAVICON_ORDINAL = 7;

    static {
        PaymentRequestClient_Internal.MANAGER = new org.chromium.payments.mojom.PaymentRequestClient_Internal$1();
    }

    PaymentRequestClient_Internal() {
        super();
    }
}

