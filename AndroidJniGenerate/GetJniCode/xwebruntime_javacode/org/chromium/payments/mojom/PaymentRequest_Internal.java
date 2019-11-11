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

class PaymentRequest_Internal {
    final class org.chromium.payments.mojom.PaymentRequest_Internal$1 extends Manager {
        org.chromium.payments.mojom.PaymentRequest_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public PaymentRequest[] buildArray(int arg1) {
            return new PaymentRequest[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.payments.mojom.PaymentRequest_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.payments.mojom.PaymentRequest_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PaymentRequest)arg2));
        }

        public org.chromium.payments.mojom.PaymentRequest_Internal$Stub buildStub(Core arg2, PaymentRequest arg3) {
            return new org.chromium.payments.mojom.PaymentRequest_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "payments::mojom::PaymentRequest";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class PaymentRequestAbortParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PaymentRequestAbortParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PaymentRequestAbortParams.DEFAULT_STRUCT_INFO = PaymentRequestAbortParams.VERSION_ARRAY[0];
        }

        public PaymentRequestAbortParams() {
            this(0);
        }

        private PaymentRequestAbortParams(int arg2) {
            super(8, arg2);
        }

        public static PaymentRequestAbortParams decode(Decoder arg2) {
            PaymentRequestAbortParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestAbortParams(arg2.readAndValidateDataHeader(PaymentRequestAbortParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestAbortParams deserialize(ByteBuffer arg2) {
            return PaymentRequestAbortParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestAbortParams deserialize(Message arg1) {
            return PaymentRequestAbortParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PaymentRequestAbortParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class PaymentRequestCanMakePaymentParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PaymentRequestCanMakePaymentParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PaymentRequestCanMakePaymentParams.DEFAULT_STRUCT_INFO = PaymentRequestCanMakePaymentParams.VERSION_ARRAY[0];
        }

        public PaymentRequestCanMakePaymentParams() {
            this(0);
        }

        private PaymentRequestCanMakePaymentParams(int arg2) {
            super(8, arg2);
        }

        public static PaymentRequestCanMakePaymentParams decode(Decoder arg2) {
            PaymentRequestCanMakePaymentParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestCanMakePaymentParams(arg2.readAndValidateDataHeader(PaymentRequestCanMakePaymentParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestCanMakePaymentParams deserialize(ByteBuffer arg2) {
            return PaymentRequestCanMakePaymentParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestCanMakePaymentParams deserialize(Message arg1) {
            return PaymentRequestCanMakePaymentParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PaymentRequestCanMakePaymentParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class PaymentRequestCompleteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            PaymentRequestCompleteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestCompleteParams.DEFAULT_STRUCT_INFO = PaymentRequestCompleteParams.VERSION_ARRAY[0];
        }

        public PaymentRequestCompleteParams() {
            this(0);
        }

        private PaymentRequestCompleteParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestCompleteParams decode(Decoder arg2) {
            PaymentRequestCompleteParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestCompleteParams(arg2.readAndValidateDataHeader(PaymentRequestCompleteParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
                PaymentComplete.validate(v1.result);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestCompleteParams deserialize(ByteBuffer arg2) {
            return PaymentRequestCompleteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestCompleteParams deserialize(Message arg1) {
            return PaymentRequestCompleteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(PaymentRequestCompleteParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    final class PaymentRequestInitParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public PaymentRequestClient client;
        public PaymentDetails details;
        public PaymentMethodData[] methodData;
        public PaymentOptions options;

        static {
            PaymentRequestInitParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            PaymentRequestInitParams.DEFAULT_STRUCT_INFO = PaymentRequestInitParams.VERSION_ARRAY[0];
        }

        public PaymentRequestInitParams() {
            this(0);
        }

        private PaymentRequestInitParams(int arg2) {
            super(40, arg2);
        }

        public static PaymentRequestInitParams decode(Decoder arg8) {
            PaymentRequestInitParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new PaymentRequestInitParams(arg8.readAndValidateDataHeader(PaymentRequestInitParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                v1.client = arg8.readServiceInterface(v2, false, PaymentRequestClient.MANAGER);
                Decoder v0_1 = arg8.readPointer(16, false);
                DataHeader v4 = v0_1.readDataHeaderForPointerArray(-1);
                v1.methodData = new PaymentMethodData[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.methodData[v5] = PaymentMethodData.decode(v0_1.readPointer(v5 * 8 + v2, false));
                }

                v1.details = PaymentDetails.decode(arg8.readPointer(24, false));
                v1.options = PaymentOptions.decode(arg8.readPointer(0x20, false));
            }
            catch(Throwable v0) {
                goto label_43;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_43:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static PaymentRequestInitParams deserialize(ByteBuffer arg2) {
            return PaymentRequestInitParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestInitParams deserialize(Message arg1) {
            return PaymentRequestInitParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg7) {
            arg7 = arg7.getEncoderAtDataOffset(PaymentRequestInitParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            arg7.encode(this.client, v2, false, PaymentRequestClient.MANAGER);
            int v1 = 16;
            if(this.methodData == null) {
                arg7.encodeNullPointer(v1, false);
            }
            else {
                Encoder v0 = arg7.encodePointerArray(this.methodData.length, v1, -1);
                for(v1 = 0; v1 < this.methodData.length; ++v1) {
                    v0.encode(this.methodData[v1], v1 * 8 + v2, false);
                }
            }

            arg7.encode(this.details, 24, false);
            arg7.encode(this.options, 0x20, false);
        }
    }

    final class PaymentRequestNoUpdatedPaymentDetailsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PaymentRequestNoUpdatedPaymentDetailsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PaymentRequestNoUpdatedPaymentDetailsParams.DEFAULT_STRUCT_INFO = PaymentRequestNoUpdatedPaymentDetailsParams.VERSION_ARRAY[0];
        }

        public PaymentRequestNoUpdatedPaymentDetailsParams() {
            this(0);
        }

        private PaymentRequestNoUpdatedPaymentDetailsParams(int arg2) {
            super(8, arg2);
        }

        public static PaymentRequestNoUpdatedPaymentDetailsParams decode(Decoder arg2) {
            PaymentRequestNoUpdatedPaymentDetailsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PaymentRequestNoUpdatedPaymentDetailsParams(arg2.readAndValidateDataHeader(PaymentRequestNoUpdatedPaymentDetailsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestNoUpdatedPaymentDetailsParams deserialize(ByteBuffer arg2) {
            return PaymentRequestNoUpdatedPaymentDetailsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestNoUpdatedPaymentDetailsParams deserialize(Message arg1) {
            return PaymentRequestNoUpdatedPaymentDetailsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PaymentRequestNoUpdatedPaymentDetailsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class PaymentRequestShowParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isUserGesture;

        static {
            PaymentRequestShowParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestShowParams.DEFAULT_STRUCT_INFO = PaymentRequestShowParams.VERSION_ARRAY[0];
        }

        public PaymentRequestShowParams() {
            this(0);
        }

        private PaymentRequestShowParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestShowParams decode(Decoder arg3) {
            PaymentRequestShowParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PaymentRequestShowParams(arg3.readAndValidateDataHeader(PaymentRequestShowParams.VERSION_ARRAY).elementsOrVersion);
                v1.isUserGesture = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestShowParams deserialize(ByteBuffer arg2) {
            return PaymentRequestShowParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestShowParams deserialize(Message arg1) {
            return PaymentRequestShowParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PaymentRequestShowParams.DEFAULT_STRUCT_INFO).encode(this.isUserGesture, 8, 0);
        }
    }

    final class PaymentRequestUpdateWithParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PaymentDetails details;

        static {
            PaymentRequestUpdateWithParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PaymentRequestUpdateWithParams.DEFAULT_STRUCT_INFO = PaymentRequestUpdateWithParams.VERSION_ARRAY[0];
        }

        public PaymentRequestUpdateWithParams() {
            this(0);
        }

        private PaymentRequestUpdateWithParams(int arg2) {
            super(16, arg2);
        }

        public static PaymentRequestUpdateWithParams decode(Decoder arg3) {
            PaymentRequestUpdateWithParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PaymentRequestUpdateWithParams(arg3.readAndValidateDataHeader(PaymentRequestUpdateWithParams.VERSION_ARRAY).elementsOrVersion);
                v1.details = PaymentDetails.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PaymentRequestUpdateWithParams deserialize(ByteBuffer arg2) {
            return PaymentRequestUpdateWithParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PaymentRequestUpdateWithParams deserialize(Message arg1) {
            return PaymentRequestUpdateWithParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PaymentRequestUpdateWithParams.DEFAULT_STRUCT_INFO).encode(this.details, 8, false);
        }
    }

    final class org.chromium.payments.mojom.PaymentRequest_Internal$Proxy extends AbstractProxy implements org.chromium.payments.mojom.PaymentRequest$Proxy {
        org.chromium.payments.mojom.PaymentRequest_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void abort() {
            this.getProxyHandler().getMessageReceiver().accept(new PaymentRequestAbortParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void canMakePayment() {
            this.getProxyHandler().getMessageReceiver().accept(new PaymentRequestCanMakePaymentParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void complete(int arg5) {
            PaymentRequestCompleteParams v0 = new PaymentRequestCompleteParams();
            v0.result = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void init(PaymentRequestClient arg2, PaymentMethodData[] arg3, PaymentDetails arg4, PaymentOptions arg5) {
            PaymentRequestInitParams v0 = new PaymentRequestInitParams();
            v0.client = arg2;
            v0.methodData = arg3;
            v0.details = arg4;
            v0.options = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void noUpdatedPaymentDetails() {
            this.getProxyHandler().getMessageReceiver().accept(new PaymentRequestNoUpdatedPaymentDetailsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void show(boolean arg5) {
            PaymentRequestShowParams v0 = new PaymentRequestShowParams();
            v0.isUserGesture = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void updateWith(PaymentDetails arg5) {
            PaymentRequestUpdateWithParams v0 = new PaymentRequestUpdateWithParams();
            v0.details = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class org.chromium.payments.mojom.PaymentRequest_Internal$Stub extends Stub {
        org.chromium.payments.mojom.PaymentRequest_Internal$Stub(Core arg1, PaymentRequest arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_54;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_45;
                    }
                    case 1: {
                        goto label_39;
                    }
                    case 2: {
                        goto label_33;
                    }
                    case 3: {
                        goto label_28;
                    }
                    case 4: {
                        goto label_23;
                    }
                    case 5: {
                        goto label_17;
                    }
                    case 6: {
                        goto label_12;
                    }
                }

                return 0;
            label_33:
                this.getImpl().updateWith(PaymentRequestUpdateWithParams.deserialize(v7_1.getPayload()).details);
                return 1;
            label_17:
                this.getImpl().complete(PaymentRequestCompleteParams.deserialize(v7_1.getPayload()).result);
                return 1;
            label_39:
                this.getImpl().show(PaymentRequestShowParams.deserialize(v7_1.getPayload()).isUserGesture);
                return 1;
            label_23:
                PaymentRequestAbortParams.deserialize(v7_1.getPayload());
                this.getImpl().abort();
                return 1;
            label_28:
                PaymentRequestNoUpdatedPaymentDetailsParams.deserialize(v7_1.getPayload());
                this.getImpl().noUpdatedPaymentDetails();
                return 1;
            label_12:
                PaymentRequestCanMakePaymentParams.deserialize(v7_1.getPayload());
                this.getImpl().canMakePayment();
                return 1;
            label_45:
                PaymentRequestInitParams v7_2 = PaymentRequestInitParams.deserialize(v7_1.getPayload());
                this.getImpl().init(v7_2.client, v7_2.methodData, v7_2.details, v7_2.options);
                return 1;
            label_54:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PaymentRequest_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PaymentRequest_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int ABORT_ORDINAL = 4;
    private static final int CAN_MAKE_PAYMENT_ORDINAL = 6;
    private static final int COMPLETE_ORDINAL = 5;
    private static final int INIT_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int NO_UPDATED_PAYMENT_DETAILS_ORDINAL = 3;
    private static final int SHOW_ORDINAL = 1;
    private static final int UPDATE_WITH_ORDINAL = 2;

    static {
        PaymentRequest_Internal.MANAGER = new org.chromium.payments.mojom.PaymentRequest_Internal$1();
    }

    PaymentRequest_Internal() {
        super();
    }
}

