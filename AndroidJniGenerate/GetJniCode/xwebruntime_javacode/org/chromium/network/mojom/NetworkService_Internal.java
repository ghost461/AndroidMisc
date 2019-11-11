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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class NetworkService_Internal {
    final class org.chromium.network.mojom.NetworkService_Internal$1 extends Manager {
        org.chromium.network.mojom.NetworkService_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public NetworkService[] buildArray(int arg1) {
            return new NetworkService[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.NetworkService_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.NetworkService_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NetworkService)arg2));
        }

        public org.chromium.network.mojom.NetworkService_Internal$Stub buildStub(Core arg2, NetworkService arg3) {
            return new org.chromium.network.mojom.NetworkService_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::NetworkService";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NetworkServiceCreateNetworkContextParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest context;
        public NetworkContextParams params;

        static {
            NetworkServiceCreateNetworkContextParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NetworkServiceCreateNetworkContextParams.DEFAULT_STRUCT_INFO = NetworkServiceCreateNetworkContextParams.VERSION_ARRAY[0];
        }

        public NetworkServiceCreateNetworkContextParams() {
            this(0);
        }

        private NetworkServiceCreateNetworkContextParams(int arg2) {
            super(24, arg2);
        }

        public static NetworkServiceCreateNetworkContextParams decode(Decoder arg3) {
            NetworkServiceCreateNetworkContextParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceCreateNetworkContextParams(arg3.readAndValidateDataHeader(NetworkServiceCreateNetworkContextParams.VERSION_ARRAY).elementsOrVersion);
                v1.context = arg3.readInterfaceRequest(8, false);
                v1.params = NetworkContextParams.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceCreateNetworkContextParams deserialize(ByteBuffer arg2) {
            return NetworkServiceCreateNetworkContextParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceCreateNetworkContextParams deserialize(Message arg1) {
            return NetworkServiceCreateNetworkContextParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkServiceCreateNetworkContextParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.context, 8, false);
            arg4.encode(this.params, 16, false);
        }
    }

    final class NetworkServiceDisableQuicParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceDisableQuicParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceDisableQuicParams.DEFAULT_STRUCT_INFO = NetworkServiceDisableQuicParams.VERSION_ARRAY[0];
        }

        public NetworkServiceDisableQuicParams() {
            this(0);
        }

        private NetworkServiceDisableQuicParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceDisableQuicParams decode(Decoder arg2) {
            NetworkServiceDisableQuicParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceDisableQuicParams(arg2.readAndValidateDataHeader(NetworkServiceDisableQuicParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceDisableQuicParams deserialize(ByteBuffer arg2) {
            return NetworkServiceDisableQuicParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceDisableQuicParams deserialize(Message arg1) {
            return NetworkServiceDisableQuicParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceDisableQuicParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class NetworkServiceGetNetworkChangeManagerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest networkChangeManager;

        static {
            NetworkServiceGetNetworkChangeManagerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceGetNetworkChangeManagerParams.DEFAULT_STRUCT_INFO = NetworkServiceGetNetworkChangeManagerParams.VERSION_ARRAY[0];
        }

        public NetworkServiceGetNetworkChangeManagerParams() {
            this(0);
        }

        private NetworkServiceGetNetworkChangeManagerParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceGetNetworkChangeManagerParams decode(Decoder arg3) {
            NetworkServiceGetNetworkChangeManagerParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceGetNetworkChangeManagerParams(arg3.readAndValidateDataHeader(NetworkServiceGetNetworkChangeManagerParams.VERSION_ARRAY).elementsOrVersion);
                v1.networkChangeManager = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceGetNetworkChangeManagerParams deserialize(ByteBuffer arg2) {
            return NetworkServiceGetNetworkChangeManagerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceGetNetworkChangeManagerParams deserialize(Message arg1) {
            return NetworkServiceGetNetworkChangeManagerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NetworkServiceGetNetworkChangeManagerParams.DEFAULT_STRUCT_INFO).encode(this.networkChangeManager, 8, false);
        }
    }

    final class NetworkServiceSetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NetworkServiceClient client;

        static {
            NetworkServiceSetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceSetClientParams.DEFAULT_STRUCT_INFO = NetworkServiceSetClientParams.VERSION_ARRAY[0];
        }

        public NetworkServiceSetClientParams() {
            this(0);
        }

        private NetworkServiceSetClientParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceSetClientParams decode(Decoder arg4) {
            NetworkServiceSetClientParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new NetworkServiceSetClientParams(arg4.readAndValidateDataHeader(NetworkServiceSetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg4.readServiceInterface(8, false, NetworkServiceClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceSetClientParams deserialize(ByteBuffer arg2) {
            return NetworkServiceSetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceSetClientParams deserialize(Message arg1) {
            return NetworkServiceSetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(NetworkServiceSetClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false, NetworkServiceClient.MANAGER);
        }
    }

    final class NetworkServiceSetRawHeadersAccessParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean allow;
        public int processId;

        static {
            NetworkServiceSetRawHeadersAccessParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceSetRawHeadersAccessParams.DEFAULT_STRUCT_INFO = NetworkServiceSetRawHeadersAccessParams.VERSION_ARRAY[0];
        }

        public NetworkServiceSetRawHeadersAccessParams() {
            this(0);
        }

        private NetworkServiceSetRawHeadersAccessParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceSetRawHeadersAccessParams decode(Decoder arg3) {
            NetworkServiceSetRawHeadersAccessParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceSetRawHeadersAccessParams(arg3.readAndValidateDataHeader(NetworkServiceSetRawHeadersAccessParams.VERSION_ARRAY).elementsOrVersion);
                v1.processId = arg3.readInt(8);
                v1.allow = arg3.readBoolean(12, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceSetRawHeadersAccessParams deserialize(ByteBuffer arg2) {
            return NetworkServiceSetRawHeadersAccessParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceSetRawHeadersAccessParams deserialize(Message arg1) {
            return NetworkServiceSetRawHeadersAccessParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkServiceSetRawHeadersAccessParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.processId, 8);
            arg4.encode(this.allow, 12, 0);
        }
    }

    final class org.chromium.network.mojom.NetworkService_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.NetworkService$Proxy {
        org.chromium.network.mojom.NetworkService_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createNetworkContext(InterfaceRequest arg4, NetworkContextParams arg5) {
            NetworkServiceCreateNetworkContextParams v0 = new NetworkServiceCreateNetworkContextParams();
            v0.context = arg4;
            v0.params = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void disableQuic() {
            this.getProxyHandler().getMessageReceiver().accept(new NetworkServiceDisableQuicParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void getNetworkChangeManager(InterfaceRequest arg5) {
            NetworkServiceGetNetworkChangeManagerParams v0 = new NetworkServiceGetNetworkChangeManagerParams();
            v0.networkChangeManager = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void setClient(NetworkServiceClient arg5) {
            NetworkServiceSetClientParams v0 = new NetworkServiceSetClientParams();
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void setRawHeadersAccess(int arg4, boolean arg5) {
            NetworkServiceSetRawHeadersAccessParams v0 = new NetworkServiceSetRawHeadersAccessParams();
            v0.processId = arg4;
            v0.allow = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class org.chromium.network.mojom.NetworkService_Internal$Stub extends Stub {
        org.chromium.network.mojom.NetworkService_Internal$Stub(Core arg1, NetworkService arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_43;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_37;
                    }
                    case 1: {
                        goto label_30;
                    }
                    case 2: {
                        goto label_25;
                    }
                    case 3: {
                        goto label_18;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                NetworkServiceSetRawHeadersAccessParams v5_2 = NetworkServiceSetRawHeadersAccessParams.deserialize(v5_1.getPayload());
                this.getImpl().setRawHeadersAccess(v5_2.processId, v5_2.allow);
                return 1;
            label_37:
                this.getImpl().setClient(NetworkServiceSetClientParams.deserialize(v5_1.getPayload()).client);
                return 1;
            label_25:
                NetworkServiceDisableQuicParams.deserialize(v5_1.getPayload());
                this.getImpl().disableQuic();
                return 1;
            label_12:
                this.getImpl().getNetworkChangeManager(NetworkServiceGetNetworkChangeManagerParams.deserialize(v5_1.getPayload()).networkChangeManager);
                return 1;
            label_30:
                NetworkServiceCreateNetworkContextParams v5_3 = NetworkServiceCreateNetworkContextParams.deserialize(v5_1.getPayload());
                this.getImpl().createNetworkContext(v5_3.context, v5_3.params);
                return 1;
            label_43:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NetworkService_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NetworkService_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_NETWORK_CONTEXT_ORDINAL = 1;
    private static final int DISABLE_QUIC_ORDINAL = 2;
    private static final int GET_NETWORK_CHANGE_MANAGER_ORDINAL = 4;
    public static final Manager MANAGER = null;
    private static final int SET_CLIENT_ORDINAL = 0;
    private static final int SET_RAW_HEADERS_ACCESS_ORDINAL = 3;

    static {
        NetworkService_Internal.MANAGER = new org.chromium.network.mojom.NetworkService_Internal$1();
    }

    NetworkService_Internal() {
        super();
    }
}

