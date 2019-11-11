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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class TcpConnectedSocketObserver_Internal {
    final class org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$1 extends Manager {
        org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public TcpConnectedSocketObserver[] buildArray(int arg1) {
            return new TcpConnectedSocketObserver[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((TcpConnectedSocketObserver)arg2));
        }

        public org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Stub buildStub(Core arg2, TcpConnectedSocketObserver arg3) {
            return new org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::TCPConnectedSocketObserver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.TcpConnectedSocketObserver$Proxy {
        org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onReadError(int arg5) {
            TcpConnectedSocketObserverOnReadErrorParams v0 = new TcpConnectedSocketObserverOnReadErrorParams();
            v0.netError = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onWriteError(int arg5) {
            TcpConnectedSocketObserverOnWriteErrorParams v0 = new TcpConnectedSocketObserverOnWriteErrorParams();
            v0.netError = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Stub extends Stub {
        org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$Stub(Core arg1, TcpConnectedSocketObserver arg2) {
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
                    goto label_24;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_18;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().onReadError(TcpConnectedSocketObserverOnReadErrorParams.deserialize(v4_1.getPayload()).netError);
                return 1;
            label_12:
                this.getImpl().onWriteError(TcpConnectedSocketObserverOnWriteErrorParams.deserialize(v4_1.getPayload()).netError);
                return 1;
            label_24:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(TcpConnectedSocketObserver_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), TcpConnectedSocketObserver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class TcpConnectedSocketObserverOnReadErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int netError;

        static {
            TcpConnectedSocketObserverOnReadErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TcpConnectedSocketObserverOnReadErrorParams.DEFAULT_STRUCT_INFO = TcpConnectedSocketObserverOnReadErrorParams.VERSION_ARRAY[0];
        }

        public TcpConnectedSocketObserverOnReadErrorParams() {
            this(0);
        }

        private TcpConnectedSocketObserverOnReadErrorParams(int arg2) {
            super(16, arg2);
        }

        public static TcpConnectedSocketObserverOnReadErrorParams decode(Decoder arg2) {
            TcpConnectedSocketObserverOnReadErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TcpConnectedSocketObserverOnReadErrorParams(arg2.readAndValidateDataHeader(TcpConnectedSocketObserverOnReadErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.netError = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TcpConnectedSocketObserverOnReadErrorParams deserialize(ByteBuffer arg2) {
            return TcpConnectedSocketObserverOnReadErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TcpConnectedSocketObserverOnReadErrorParams deserialize(Message arg1) {
            return TcpConnectedSocketObserverOnReadErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(TcpConnectedSocketObserverOnReadErrorParams.DEFAULT_STRUCT_INFO).encode(this.netError, 8);
        }
    }

    final class TcpConnectedSocketObserverOnWriteErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int netError;

        static {
            TcpConnectedSocketObserverOnWriteErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TcpConnectedSocketObserverOnWriteErrorParams.DEFAULT_STRUCT_INFO = TcpConnectedSocketObserverOnWriteErrorParams.VERSION_ARRAY[0];
        }

        public TcpConnectedSocketObserverOnWriteErrorParams() {
            this(0);
        }

        private TcpConnectedSocketObserverOnWriteErrorParams(int arg2) {
            super(16, arg2);
        }

        public static TcpConnectedSocketObserverOnWriteErrorParams decode(Decoder arg2) {
            TcpConnectedSocketObserverOnWriteErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new TcpConnectedSocketObserverOnWriteErrorParams(arg2.readAndValidateDataHeader(TcpConnectedSocketObserverOnWriteErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.netError = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static TcpConnectedSocketObserverOnWriteErrorParams deserialize(ByteBuffer arg2) {
            return TcpConnectedSocketObserverOnWriteErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TcpConnectedSocketObserverOnWriteErrorParams deserialize(Message arg1) {
            return TcpConnectedSocketObserverOnWriteErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(TcpConnectedSocketObserverOnWriteErrorParams.DEFAULT_STRUCT_INFO).encode(this.netError, 8);
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_READ_ERROR_ORDINAL = 0;
    private static final int ON_WRITE_ERROR_ORDINAL = 1;

    static {
        TcpConnectedSocketObserver_Internal.MANAGER = new org.chromium.network.mojom.TcpConnectedSocketObserver_Internal$1();
    }

    TcpConnectedSocketObserver_Internal() {
        super();
    }
}

