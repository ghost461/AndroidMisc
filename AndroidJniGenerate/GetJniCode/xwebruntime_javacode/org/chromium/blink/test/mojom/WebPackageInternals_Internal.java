package org.chromium.blink.test.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo_base.mojom.Time;

class WebPackageInternals_Internal {
    final class org.chromium.blink.test.mojom.WebPackageInternals_Internal$1 extends Manager {
        org.chromium.blink.test.mojom.WebPackageInternals_Internal$1() {
            super();
        }

        public WebPackageInternals[] buildArray(int arg1) {
            return new WebPackageInternals[arg1];
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new Proxy(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public Stub buildStub(Core arg2, WebPackageInternals arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WebPackageInternals)arg2));
        }

        public String getName() {
            return "blink::test::mojom::WebPackageInternals";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.test.mojom.WebPackageInternals$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void setSignedExchangeVerificationTime(Time arg8, SetSignedExchangeVerificationTimeResponse arg9) {
            WebPackageInternalsSetSignedExchangeVerificationTimeParams v0 = new WebPackageInternalsSetSignedExchangeVerificationTimeParams();
            v0.time = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new WebPackageInternalsSetSignedExchangeVerificationTimeResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, WebPackageInternals arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                if(v1.getType() != -2) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WebPackageInternals_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg9, MessageReceiver arg10) {
            try {
                ServiceMessage v9_1 = arg9.asServiceMessage();
                MessageHeader v1 = v9_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_20;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WebPackageInternals_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().setSignedExchangeVerificationTime(WebPackageInternalsSetSignedExchangeVerificationTimeParams.deserialize(v9_1.getPayload()).time, new WebPackageInternalsSetSignedExchangeVerificationTimeResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    final class WebPackageInternalsSetSignedExchangeVerificationTimeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Time time;

        static {
            WebPackageInternalsSetSignedExchangeVerificationTimeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WebPackageInternalsSetSignedExchangeVerificationTimeParams.DEFAULT_STRUCT_INFO = WebPackageInternalsSetSignedExchangeVerificationTimeParams.VERSION_ARRAY[0];
        }

        public WebPackageInternalsSetSignedExchangeVerificationTimeParams() {
            this(0);
        }

        private WebPackageInternalsSetSignedExchangeVerificationTimeParams(int arg2) {
            super(16, arg2);
        }

        public static WebPackageInternalsSetSignedExchangeVerificationTimeParams decode(Decoder arg3) {
            WebPackageInternalsSetSignedExchangeVerificationTimeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebPackageInternalsSetSignedExchangeVerificationTimeParams(arg3.readAndValidateDataHeader(WebPackageInternalsSetSignedExchangeVerificationTimeParams.VERSION_ARRAY).elementsOrVersion);
                v1.time = Time.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebPackageInternalsSetSignedExchangeVerificationTimeParams deserialize(ByteBuffer arg2) {
            return WebPackageInternalsSetSignedExchangeVerificationTimeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebPackageInternalsSetSignedExchangeVerificationTimeParams deserialize(Message arg1) {
            return WebPackageInternalsSetSignedExchangeVerificationTimeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WebPackageInternalsSetSignedExchangeVerificationTimeParams.DEFAULT_STRUCT_INFO).encode(this.time, 8, false);
        }
    }

    final class WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.DEFAULT_STRUCT_INFO = WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.VERSION_ARRAY[0];
        }

        public WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams() {
            this(0);
        }

        private WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams(int arg2) {
            super(8, arg2);
        }

        public static WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams decode(Decoder arg2) {
            WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams(arg2.readAndValidateDataHeader(WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams deserialize(ByteBuffer arg2) {
            return WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams deserialize(Message arg1) {
            return WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class WebPackageInternalsSetSignedExchangeVerificationTimeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetSignedExchangeVerificationTimeResponse mCallback;

        WebPackageInternalsSetSignedExchangeVerificationTimeResponseParamsForwardToCallback(SetSignedExchangeVerificationTimeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg3) {
            try {
                if(!arg3.asServiceMessage().getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class WebPackageInternalsSetSignedExchangeVerificationTimeResponseParamsProxyToResponder implements SetSignedExchangeVerificationTimeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        WebPackageInternalsSetSignedExchangeVerificationTimeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new WebPackageInternalsSetSignedExchangeVerificationTimeResponseParams().serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    public static final Manager MANAGER;
    private static final int SET_SIGNED_EXCHANGE_VERIFICATION_TIME_ORDINAL;

    static {
        WebPackageInternals_Internal.MANAGER = new org.chromium.blink.test.mojom.WebPackageInternals_Internal$1();
    }

    WebPackageInternals_Internal() {
        super();
    }
}

