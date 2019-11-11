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
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.net.interfaces.IpEndPoint;

class TcpServerSocket_Internal {
    final class org.chromium.network.mojom.TcpServerSocket_Internal$1 extends Manager {
        org.chromium.network.mojom.TcpServerSocket_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public TcpServerSocket[] buildArray(int arg1) {
            return new TcpServerSocket[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.TcpServerSocket_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.TcpServerSocket_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((TcpServerSocket)arg2));
        }

        public org.chromium.network.mojom.TcpServerSocket_Internal$Stub buildStub(Core arg2, TcpServerSocket arg3) {
            return new org.chromium.network.mojom.TcpServerSocket_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::TCPServerSocket";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.TcpServerSocket_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.TcpServerSocket$Proxy {
        org.chromium.network.mojom.TcpServerSocket_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void accept(TcpConnectedSocketObserver arg8, AcceptResponse arg9) {
            TcpServerSocketAcceptParams v0 = new TcpServerSocketAcceptParams();
            v0.observer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new TcpServerSocketAcceptResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.network.mojom.TcpServerSocket_Internal$Stub extends Stub {
        org.chromium.network.mojom.TcpServerSocket_Internal$Stub(Core arg1, TcpServerSocket arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(TcpServerSocket_Internal.MANAGER, v4_1);
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
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), TcpServerSocket_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().accept(TcpServerSocketAcceptParams.deserialize(v9_1.getPayload()).observer, new TcpServerSocketAcceptResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    final class TcpServerSocketAcceptParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TcpConnectedSocketObserver observer;

        static {
            TcpServerSocketAcceptParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TcpServerSocketAcceptParams.DEFAULT_STRUCT_INFO = TcpServerSocketAcceptParams.VERSION_ARRAY[0];
        }

        public TcpServerSocketAcceptParams() {
            this(0);
        }

        private TcpServerSocketAcceptParams(int arg2) {
            super(16, arg2);
        }

        public static TcpServerSocketAcceptParams decode(Decoder arg4) {
            TcpServerSocketAcceptParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new TcpServerSocketAcceptParams(arg4.readAndValidateDataHeader(TcpServerSocketAcceptParams.VERSION_ARRAY).elementsOrVersion);
                v1.observer = arg4.readServiceInterface(8, true, TcpConnectedSocketObserver.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static TcpServerSocketAcceptParams deserialize(ByteBuffer arg2) {
            return TcpServerSocketAcceptParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TcpServerSocketAcceptParams deserialize(Message arg1) {
            return TcpServerSocketAcceptParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(TcpServerSocketAcceptParams.DEFAULT_STRUCT_INFO).encode(this.observer, 8, true, TcpConnectedSocketObserver.MANAGER);
        }
    }

    final class TcpServerSocketAcceptResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public TcpConnectedSocket connectedSocket;
        public int netError;
        public ProducerHandle receiveStream;
        public IpEndPoint remoteAddr;
        public ConsumerHandle sendStream;

        static {
            TcpServerSocketAcceptResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            TcpServerSocketAcceptResponseParams.DEFAULT_STRUCT_INFO = TcpServerSocketAcceptResponseParams.VERSION_ARRAY[0];
        }

        public TcpServerSocketAcceptResponseParams() {
            this(0);
        }

        private TcpServerSocketAcceptResponseParams(int arg2) {
            super(40, arg2);
            this.sendStream = InvalidHandle.INSTANCE;
            this.receiveStream = InvalidHandle.INSTANCE;
        }

        public static TcpServerSocketAcceptResponseParams decode(Decoder arg4) {
            TcpServerSocketAcceptResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new TcpServerSocketAcceptResponseParams(arg4.readAndValidateDataHeader(TcpServerSocketAcceptResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.netError = arg4.readInt(8);
                v1.sendStream = arg4.readConsumerHandle(12, true);
                v1.remoteAddr = IpEndPoint.decode(arg4.readPointer(16, true));
                v1.connectedSocket = arg4.readServiceInterface(24, true, TcpConnectedSocket.MANAGER);
                v1.receiveStream = arg4.readProducerHandle(0x20, true);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static TcpServerSocketAcceptResponseParams deserialize(ByteBuffer arg2) {
            return TcpServerSocketAcceptResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TcpServerSocketAcceptResponseParams deserialize(Message arg1) {
            return TcpServerSocketAcceptResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(TcpServerSocketAcceptResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.netError, 8);
            arg5.encode(this.sendStream, 12, true);
            arg5.encode(this.remoteAddr, 16, true);
            arg5.encode(this.connectedSocket, 24, true, TcpConnectedSocket.MANAGER);
            arg5.encode(this.receiveStream, 0x20, true);
        }
    }

    class TcpServerSocketAcceptResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final AcceptResponse mCallback;

        TcpServerSocketAcceptResponseParamsForwardToCallback(AcceptResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg8) {
            try {
                ServiceMessage v8 = arg8.asServiceMessage();
                if(!v8.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                TcpServerSocketAcceptResponseParams v8_1 = TcpServerSocketAcceptResponseParams.deserialize(v8.getPayload());
                this.mCallback.call(Integer.valueOf(v8_1.netError), v8_1.remoteAddr, v8_1.connectedSocket, v8_1.sendStream, v8_1.receiveStream);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class TcpServerSocketAcceptResponseParamsProxyToResponder implements AcceptResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        TcpServerSocketAcceptResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg3, IpEndPoint arg4, TcpConnectedSocket arg5, ConsumerHandle arg6, ProducerHandle arg7) {
            TcpServerSocketAcceptResponseParams v0 = new TcpServerSocketAcceptResponseParams();
            v0.netError = arg3.intValue();
            v0.remoteAddr = arg4;
            v0.connectedSocket = arg5;
            v0.sendStream = arg6;
            v0.receiveStream = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) {
            this.call(arg7, arg8, arg9, arg10, arg11);
        }
    }

    private static final int ACCEPT_ORDINAL;
    public static final Manager MANAGER;

    static {
        TcpServerSocket_Internal.MANAGER = new org.chromium.network.mojom.TcpServerSocket_Internal$1();
    }

    TcpServerSocket_Internal() {
        super();
    }
}

