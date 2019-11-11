package org.chromium.network.mojom;

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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class SslPrivateKey_Internal {
    final class org.chromium.network.mojom.SslPrivateKey_Internal$1 extends Manager {
        org.chromium.network.mojom.SslPrivateKey_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public SslPrivateKey[] buildArray(int arg1) {
            return new SslPrivateKey[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.SslPrivateKey_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.SslPrivateKey_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((SslPrivateKey)arg2));
        }

        public org.chromium.network.mojom.SslPrivateKey_Internal$Stub buildStub(Core arg2, SslPrivateKey arg3) {
            return new org.chromium.network.mojom.SslPrivateKey_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::SSLPrivateKey";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.SslPrivateKey_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.SslPrivateKey$Proxy {
        org.chromium.network.mojom.SslPrivateKey_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void sign(short arg7, byte[] arg8, SignResponse arg9) {
            SslPrivateKeySignParams v0 = new SslPrivateKeySignParams();
            v0.algorithm = arg7;
            v0.input = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new SslPrivateKeySignResponseParamsForwardToCallback(arg9));
        }
    }

    final class SslPrivateKeySignParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public short algorithm;
        public byte[] input;

        static {
            SslPrivateKeySignParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            SslPrivateKeySignParams.DEFAULT_STRUCT_INFO = SslPrivateKeySignParams.VERSION_ARRAY[0];
        }

        public SslPrivateKeySignParams() {
            this(0);
        }

        private SslPrivateKeySignParams(int arg2) {
            super(24, arg2);
        }

        public static SslPrivateKeySignParams decode(Decoder arg4) {
            SslPrivateKeySignParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new SslPrivateKeySignParams(arg4.readAndValidateDataHeader(SslPrivateKeySignParams.VERSION_ARRAY).elementsOrVersion);
                v1.algorithm = arg4.readShort(8);
                v1.input = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static SslPrivateKeySignParams deserialize(ByteBuffer arg2) {
            return SslPrivateKeySignParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SslPrivateKeySignParams deserialize(Message arg1) {
            return SslPrivateKeySignParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(SslPrivateKeySignParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.algorithm, 8);
            arg5.encode(this.input, 16, 0, -1);
        }
    }

    final class SslPrivateKeySignResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int netError;
        public byte[] signature;

        static {
            SslPrivateKeySignResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            SslPrivateKeySignResponseParams.DEFAULT_STRUCT_INFO = SslPrivateKeySignResponseParams.VERSION_ARRAY[0];
        }

        public SslPrivateKeySignResponseParams() {
            this(0);
        }

        private SslPrivateKeySignResponseParams(int arg2) {
            super(24, arg2);
        }

        public static SslPrivateKeySignResponseParams decode(Decoder arg4) {
            SslPrivateKeySignResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new SslPrivateKeySignResponseParams(arg4.readAndValidateDataHeader(SslPrivateKeySignResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.netError = arg4.readInt(8);
                v1.signature = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static SslPrivateKeySignResponseParams deserialize(ByteBuffer arg2) {
            return SslPrivateKeySignResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SslPrivateKeySignResponseParams deserialize(Message arg1) {
            return SslPrivateKeySignResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(SslPrivateKeySignResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.netError, 8);
            arg5.encode(this.signature, 16, 0, -1);
        }
    }

    class SslPrivateKeySignResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SignResponse mCallback;

        SslPrivateKeySignResponseParamsForwardToCallback(SignResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                SslPrivateKeySignResponseParams v4_1 = SslPrivateKeySignResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.netError), v4_1.signature);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SslPrivateKeySignResponseParamsProxyToResponder implements SignResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SslPrivateKeySignResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, byte[] arg7) {
            SslPrivateKeySignResponseParams v0 = new SslPrivateKeySignResponseParams();
            v0.netError = arg6.intValue();
            v0.signature = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((byte[])arg2));
        }
    }

    final class org.chromium.network.mojom.SslPrivateKey_Internal$Stub extends Stub {
        org.chromium.network.mojom.SslPrivateKey_Internal$Stub(Core arg1, SslPrivateKey arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(SslPrivateKey_Internal.MANAGER, v4_1);
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
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), SslPrivateKey_Internal.MANAGER, v10_1, arg11);
            label_10:
                SslPrivateKeySignParams v10_2 = SslPrivateKeySignParams.deserialize(v10_1.getPayload());
                this.getImpl().sign(v10_2.algorithm, v10_2.input, new SslPrivateKeySignResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int SIGN_ORDINAL;

    static {
        SslPrivateKey_Internal.MANAGER = new org.chromium.network.mojom.SslPrivateKey_Internal$1();
    }

    SslPrivateKey_Internal() {
        super();
    }
}

