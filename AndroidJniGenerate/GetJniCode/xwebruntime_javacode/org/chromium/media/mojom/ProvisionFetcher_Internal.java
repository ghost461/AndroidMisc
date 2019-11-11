package org.chromium.media.mojom;

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

class ProvisionFetcher_Internal {
    final class org.chromium.media.mojom.ProvisionFetcher_Internal$1 extends Manager {
        org.chromium.media.mojom.ProvisionFetcher_Internal$1() {
            super();
        }

        public ProvisionFetcher[] buildArray(int arg1) {
            return new ProvisionFetcher[arg1];
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

        public Stub buildStub(Core arg2, ProvisionFetcher arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProvisionFetcher)arg2));
        }

        public String getName() {
            return "media::mojom::ProvisionFetcher";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ProvisionFetcherRetrieveParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String defaultUrl;
        public String requestData;

        static {
            ProvisionFetcherRetrieveParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProvisionFetcherRetrieveParams.DEFAULT_STRUCT_INFO = ProvisionFetcherRetrieveParams.VERSION_ARRAY[0];
        }

        public ProvisionFetcherRetrieveParams() {
            this(0);
        }

        private ProvisionFetcherRetrieveParams(int arg2) {
            super(24, arg2);
        }

        public static ProvisionFetcherRetrieveParams decode(Decoder arg3) {
            ProvisionFetcherRetrieveParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProvisionFetcherRetrieveParams(arg3.readAndValidateDataHeader(ProvisionFetcherRetrieveParams.VERSION_ARRAY).elementsOrVersion);
                v1.defaultUrl = arg3.readString(8, false);
                v1.requestData = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProvisionFetcherRetrieveParams deserialize(ByteBuffer arg2) {
            return ProvisionFetcherRetrieveParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProvisionFetcherRetrieveParams deserialize(Message arg1) {
            return ProvisionFetcherRetrieveParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ProvisionFetcherRetrieveParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.defaultUrl, 8, false);
            arg4.encode(this.requestData, 16, false);
        }
    }

    final class ProvisionFetcherRetrieveResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String response;
        public boolean result;

        static {
            ProvisionFetcherRetrieveResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProvisionFetcherRetrieveResponseParams.DEFAULT_STRUCT_INFO = ProvisionFetcherRetrieveResponseParams.VERSION_ARRAY[0];
        }

        public ProvisionFetcherRetrieveResponseParams() {
            this(0);
        }

        private ProvisionFetcherRetrieveResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ProvisionFetcherRetrieveResponseParams decode(Decoder arg3) {
            ProvisionFetcherRetrieveResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProvisionFetcherRetrieveResponseParams(arg3.readAndValidateDataHeader(ProvisionFetcherRetrieveResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readBoolean(8, 0);
                v1.response = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProvisionFetcherRetrieveResponseParams deserialize(ByteBuffer arg2) {
            return ProvisionFetcherRetrieveResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProvisionFetcherRetrieveResponseParams deserialize(Message arg1) {
            return ProvisionFetcherRetrieveResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ProvisionFetcherRetrieveResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8, 0);
            arg4.encode(this.response, 16, false);
        }
    }

    class ProvisionFetcherRetrieveResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RetrieveResponse mCallback;

        ProvisionFetcherRetrieveResponseParamsForwardToCallback(RetrieveResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                ProvisionFetcherRetrieveResponseParams v4_1 = ProvisionFetcherRetrieveResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Boolean.valueOf(v4_1.result), v4_1.response);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ProvisionFetcherRetrieveResponseParamsProxyToResponder implements RetrieveResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ProvisionFetcherRetrieveResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6, String arg7) {
            ProvisionFetcherRetrieveResponseParams v0 = new ProvisionFetcherRetrieveResponseParams();
            v0.result = arg6.booleanValue();
            v0.response = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Boolean)arg1), ((String)arg2));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.ProvisionFetcher$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void retrieve(String arg7, String arg8, RetrieveResponse arg9) {
            ProvisionFetcherRetrieveParams v0 = new ProvisionFetcherRetrieveParams();
            v0.defaultUrl = arg7;
            v0.requestData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ProvisionFetcherRetrieveResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ProvisionFetcher arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProvisionFetcher_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg10, MessageReceiver arg11) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_21;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_21:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProvisionFetcher_Internal.MANAGER, v10_1, arg11);
            label_10:
                ProvisionFetcherRetrieveParams v10_2 = ProvisionFetcherRetrieveParams.deserialize(v10_1.getPayload());
                this.getImpl().retrieve(v10_2.defaultUrl, v10_2.requestData, new ProvisionFetcherRetrieveResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int RETRIEVE_ORDINAL;

    static {
        ProvisionFetcher_Internal.MANAGER = new org.chromium.media.mojom.ProvisionFetcher_Internal$1();
    }

    ProvisionFetcher_Internal() {
        super();
    }
}

