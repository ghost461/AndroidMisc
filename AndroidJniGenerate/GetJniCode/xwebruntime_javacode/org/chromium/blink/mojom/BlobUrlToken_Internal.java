package org.chromium.blink.mojom;

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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo_base.mojom.UnguessableToken;

class BlobUrlToken_Internal {
    final class org.chromium.blink.mojom.BlobUrlToken_Internal$1 extends Manager {
        org.chromium.blink.mojom.BlobUrlToken_Internal$1() {
            super();
        }

        public BlobUrlToken[] buildArray(int arg1) {
            return new BlobUrlToken[arg1];
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

        public Stub buildStub(Core arg2, BlobUrlToken arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BlobUrlToken)arg2));
        }

        public String getName() {
            return "blink::mojom::BlobURLToken";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BlobUrlTokenCloneParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest token;

        static {
            BlobUrlTokenCloneParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobUrlTokenCloneParams.DEFAULT_STRUCT_INFO = BlobUrlTokenCloneParams.VERSION_ARRAY[0];
        }

        public BlobUrlTokenCloneParams() {
            this(0);
        }

        private BlobUrlTokenCloneParams(int arg2) {
            super(16, arg2);
        }

        public static BlobUrlTokenCloneParams decode(Decoder arg3) {
            BlobUrlTokenCloneParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobUrlTokenCloneParams(arg3.readAndValidateDataHeader(BlobUrlTokenCloneParams.VERSION_ARRAY).elementsOrVersion);
                v1.token = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlTokenCloneParams deserialize(ByteBuffer arg2) {
            return BlobUrlTokenCloneParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlTokenCloneParams deserialize(Message arg1) {
            return BlobUrlTokenCloneParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobUrlTokenCloneParams.DEFAULT_STRUCT_INFO).encode(this.token, 8, false);
        }
    }

    final class BlobUrlTokenGetTokenParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BlobUrlTokenGetTokenParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BlobUrlTokenGetTokenParams.DEFAULT_STRUCT_INFO = BlobUrlTokenGetTokenParams.VERSION_ARRAY[0];
        }

        public BlobUrlTokenGetTokenParams() {
            this(0);
        }

        private BlobUrlTokenGetTokenParams(int arg2) {
            super(8, arg2);
        }

        public static BlobUrlTokenGetTokenParams decode(Decoder arg2) {
            BlobUrlTokenGetTokenParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BlobUrlTokenGetTokenParams(arg2.readAndValidateDataHeader(BlobUrlTokenGetTokenParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlTokenGetTokenParams deserialize(ByteBuffer arg2) {
            return BlobUrlTokenGetTokenParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlTokenGetTokenParams deserialize(Message arg1) {
            return BlobUrlTokenGetTokenParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BlobUrlTokenGetTokenParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class BlobUrlTokenGetTokenResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public UnguessableToken token;

        static {
            BlobUrlTokenGetTokenResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobUrlTokenGetTokenResponseParams.DEFAULT_STRUCT_INFO = BlobUrlTokenGetTokenResponseParams.VERSION_ARRAY[0];
        }

        public BlobUrlTokenGetTokenResponseParams() {
            this(0);
        }

        private BlobUrlTokenGetTokenResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BlobUrlTokenGetTokenResponseParams decode(Decoder arg3) {
            BlobUrlTokenGetTokenResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobUrlTokenGetTokenResponseParams(arg3.readAndValidateDataHeader(BlobUrlTokenGetTokenResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.token = UnguessableToken.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlTokenGetTokenResponseParams deserialize(ByteBuffer arg2) {
            return BlobUrlTokenGetTokenResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlTokenGetTokenResponseParams deserialize(Message arg1) {
            return BlobUrlTokenGetTokenResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobUrlTokenGetTokenResponseParams.DEFAULT_STRUCT_INFO).encode(this.token, 8, false);
        }
    }

    class BlobUrlTokenGetTokenResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetTokenResponse mCallback;

        BlobUrlTokenGetTokenResponseParamsForwardToCallback(GetTokenResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(BlobUrlTokenGetTokenResponseParams.deserialize(v5.getPayload()).token);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BlobUrlTokenGetTokenResponseParamsProxyToResponder implements GetTokenResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobUrlTokenGetTokenResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((UnguessableToken)arg1));
        }

        public void call(UnguessableToken arg7) {
            BlobUrlTokenGetTokenResponseParams v0 = new BlobUrlTokenGetTokenResponseParams();
            v0.token = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.BlobUrlToken$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void clone(InterfaceRequest arg5) {
            BlobUrlTokenCloneParams v0 = new BlobUrlTokenCloneParams();
            v0.token = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void getToken(GetTokenResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new BlobUrlTokenGetTokenParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new BlobUrlTokenGetTokenResponseParamsForwardToCallback(arg8));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, BlobUrlToken arg2) {
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
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        return 0;
                    }

                    this.getImpl().clone(BlobUrlTokenCloneParams.deserialize(v4_1.getPayload()).token);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BlobUrlToken_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                int v3 = v1.getType();
                if(v3 != -1) {
                    if(v3 != 1) {
                        return 0;
                    }

                    BlobUrlTokenGetTokenParams.deserialize(v8_1.getPayload());
                    this.getImpl().getToken(new BlobUrlTokenGetTokenResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BlobUrlToken_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int CLONE_ORDINAL = 0;
    private static final int GET_TOKEN_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        BlobUrlToken_Internal.MANAGER = new org.chromium.blink.mojom.BlobUrlToken_Internal$1();
    }

    BlobUrlToken_Internal() {
        super();
    }
}

