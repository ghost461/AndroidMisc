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
import org.chromium.net.interfaces.IpEndPoint;

class TcpConnectedSocket_Internal {
    final class org.chromium.network.mojom.TcpConnectedSocket_Internal$1 extends Manager {
        org.chromium.network.mojom.TcpConnectedSocket_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public TcpConnectedSocket[] buildArray(int arg1) {
            return new TcpConnectedSocket[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.TcpConnectedSocket_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.TcpConnectedSocket_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((TcpConnectedSocket)arg2));
        }

        public org.chromium.network.mojom.TcpConnectedSocket_Internal$Stub buildStub(Core arg2, TcpConnectedSocket arg3) {
            return new org.chromium.network.mojom.TcpConnectedSocket_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::TCPConnectedSocket";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.TcpConnectedSocket_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.TcpConnectedSocket$Proxy {
        org.chromium.network.mojom.TcpConnectedSocket_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getLocalAddress(GetLocalAddressResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new TcpConnectedSocketGetLocalAddressParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new TcpConnectedSocketGetLocalAddressResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.network.mojom.TcpConnectedSocket_Internal$Stub extends Stub {
        org.chromium.network.mojom.TcpConnectedSocket_Internal$Stub(Core arg1, TcpConnectedSocket arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(TcpConnectedSocket_Internal.MANAGER, v4_1);
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

                switch(v1.getType()) {
                    case -1: {
                        goto label_19;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), TcpConnectedSocket_Internal.MANAGER, v8_1, arg9);
            label_10:
                TcpConnectedSocketGetLocalAddressParams.deserialize(v8_1.getPayload());
                this.getImpl().getLocalAddress(new TcpConnectedSocketGetLocalAddressResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    final class TcpConnectedSocketGetLocalAddressParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            TcpConnectedSocketGetLocalAddressParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            TcpConnectedSocketGetLocalAddressParams.DEFAULT_STRUCT_INFO = TcpConnectedSocketGetLocalAddressParams.VERSION_ARRAY[0];
        }

        public TcpConnectedSocketGetLocalAddressParams() {
            this(0);
        }

        private TcpConnectedSocketGetLocalAddressParams(int arg2) {
            super(8, arg2);
        }

        public static TcpConnectedSocketGetLocalAddressParams decode(Decoder arg2) {
            TcpConnectedSocketGetLocalAddressParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TcpConnectedSocketGetLocalAddressParams(arg2.readAndValidateDataHeader(TcpConnectedSocketGetLocalAddressParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TcpConnectedSocketGetLocalAddressParams deserialize(ByteBuffer arg2) {
            return TcpConnectedSocketGetLocalAddressParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TcpConnectedSocketGetLocalAddressParams deserialize(Message arg1) {
            return TcpConnectedSocketGetLocalAddressParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(TcpConnectedSocketGetLocalAddressParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class TcpConnectedSocketGetLocalAddressResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint localAddr;
        public int netError;

        static {
            TcpConnectedSocketGetLocalAddressResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            TcpConnectedSocketGetLocalAddressResponseParams.DEFAULT_STRUCT_INFO = TcpConnectedSocketGetLocalAddressResponseParams.VERSION_ARRAY[0];
        }

        public TcpConnectedSocketGetLocalAddressResponseParams() {
            this(0);
        }

        private TcpConnectedSocketGetLocalAddressResponseParams(int arg2) {
            super(24, arg2);
        }

        public static TcpConnectedSocketGetLocalAddressResponseParams decode(Decoder arg3) {
            TcpConnectedSocketGetLocalAddressResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new TcpConnectedSocketGetLocalAddressResponseParams(arg3.readAndValidateDataHeader(TcpConnectedSocketGetLocalAddressResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.netError = arg3.readInt(8);
                v1.localAddr = IpEndPoint.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static TcpConnectedSocketGetLocalAddressResponseParams deserialize(ByteBuffer arg2) {
            return TcpConnectedSocketGetLocalAddressResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TcpConnectedSocketGetLocalAddressResponseParams deserialize(Message arg1) {
            return TcpConnectedSocketGetLocalAddressResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(TcpConnectedSocketGetLocalAddressResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.netError, 8);
            arg4.encode(this.localAddr, 16, true);
        }
    }

    class TcpConnectedSocketGetLocalAddressResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetLocalAddressResponse mCallback;

        TcpConnectedSocketGetLocalAddressResponseParamsForwardToCallback(GetLocalAddressResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                TcpConnectedSocketGetLocalAddressResponseParams v4_1 = TcpConnectedSocketGetLocalAddressResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.netError), v4_1.localAddr);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class TcpConnectedSocketGetLocalAddressResponseParamsProxyToResponder implements GetLocalAddressResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        TcpConnectedSocketGetLocalAddressResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, IpEndPoint arg7) {
            TcpConnectedSocketGetLocalAddressResponseParams v0 = new TcpConnectedSocketGetLocalAddressResponseParams();
            v0.netError = arg6.intValue();
            v0.localAddr = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((IpEndPoint)arg2));
        }
    }

    private static final int GET_LOCAL_ADDRESS_ORDINAL;
    public static final Manager MANAGER;

    static {
        TcpConnectedSocket_Internal.MANAGER = new org.chromium.network.mojom.TcpConnectedSocket_Internal$1();
    }

    TcpConnectedSocket_Internal() {
        super();
    }
}

