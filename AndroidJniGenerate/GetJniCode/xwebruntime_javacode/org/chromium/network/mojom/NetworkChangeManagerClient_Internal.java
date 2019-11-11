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

class NetworkChangeManagerClient_Internal {
    final class org.chromium.network.mojom.NetworkChangeManagerClient_Internal$1 extends Manager {
        org.chromium.network.mojom.NetworkChangeManagerClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public NetworkChangeManagerClient[] buildArray(int arg1) {
            return new NetworkChangeManagerClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NetworkChangeManagerClient)arg2));
        }

        public org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Stub buildStub(Core arg2, NetworkChangeManagerClient arg3) {
            return new org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::NetworkChangeManagerClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NetworkChangeManagerClientOnInitialConnectionTypeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int type;

        static {
            NetworkChangeManagerClientOnInitialConnectionTypeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkChangeManagerClientOnInitialConnectionTypeParams.DEFAULT_STRUCT_INFO = NetworkChangeManagerClientOnInitialConnectionTypeParams.VERSION_ARRAY[0];
        }

        public NetworkChangeManagerClientOnInitialConnectionTypeParams() {
            this(0);
        }

        private NetworkChangeManagerClientOnInitialConnectionTypeParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkChangeManagerClientOnInitialConnectionTypeParams decode(Decoder arg2) {
            NetworkChangeManagerClientOnInitialConnectionTypeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkChangeManagerClientOnInitialConnectionTypeParams(arg2.readAndValidateDataHeader(NetworkChangeManagerClientOnInitialConnectionTypeParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg2.readInt(8);
                ConnectionType.validate(v1.type);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkChangeManagerClientOnInitialConnectionTypeParams deserialize(ByteBuffer arg2) {
            return NetworkChangeManagerClientOnInitialConnectionTypeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkChangeManagerClientOnInitialConnectionTypeParams deserialize(Message arg1) {
            return NetworkChangeManagerClientOnInitialConnectionTypeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NetworkChangeManagerClientOnInitialConnectionTypeParams.DEFAULT_STRUCT_INFO).encode(this.type, 8);
        }
    }

    final class NetworkChangeManagerClientOnNetworkChangedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int type;

        static {
            NetworkChangeManagerClientOnNetworkChangedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkChangeManagerClientOnNetworkChangedParams.DEFAULT_STRUCT_INFO = NetworkChangeManagerClientOnNetworkChangedParams.VERSION_ARRAY[0];
        }

        public NetworkChangeManagerClientOnNetworkChangedParams() {
            this(0);
        }

        private NetworkChangeManagerClientOnNetworkChangedParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkChangeManagerClientOnNetworkChangedParams decode(Decoder arg2) {
            NetworkChangeManagerClientOnNetworkChangedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkChangeManagerClientOnNetworkChangedParams(arg2.readAndValidateDataHeader(NetworkChangeManagerClientOnNetworkChangedParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg2.readInt(8);
                ConnectionType.validate(v1.type);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkChangeManagerClientOnNetworkChangedParams deserialize(ByteBuffer arg2) {
            return NetworkChangeManagerClientOnNetworkChangedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkChangeManagerClientOnNetworkChangedParams deserialize(Message arg1) {
            return NetworkChangeManagerClientOnNetworkChangedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NetworkChangeManagerClientOnNetworkChangedParams.DEFAULT_STRUCT_INFO).encode(this.type, 8);
        }
    }

    final class org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.NetworkChangeManagerClient$Proxy {
        org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onInitialConnectionType(int arg5) {
            NetworkChangeManagerClientOnInitialConnectionTypeParams v0 = new NetworkChangeManagerClientOnInitialConnectionTypeParams();
            v0.type = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onNetworkChanged(int arg5) {
            NetworkChangeManagerClientOnNetworkChangedParams v0 = new NetworkChangeManagerClientOnNetworkChangedParams();
            v0.type = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Stub extends Stub {
        org.chromium.network.mojom.NetworkChangeManagerClient_Internal$Stub(Core arg1, NetworkChangeManagerClient arg2) {
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
                this.getImpl().onInitialConnectionType(NetworkChangeManagerClientOnInitialConnectionTypeParams.deserialize(v4_1.getPayload()).type);
                return 1;
            label_12:
                this.getImpl().onNetworkChanged(NetworkChangeManagerClientOnNetworkChangedParams.deserialize(v4_1.getPayload()).type);
                return 1;
            label_24:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NetworkChangeManagerClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NetworkChangeManagerClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_INITIAL_CONNECTION_TYPE_ORDINAL = 0;
    private static final int ON_NETWORK_CHANGED_ORDINAL = 1;

    static {
        NetworkChangeManagerClient_Internal.MANAGER = new org.chromium.network.mojom.NetworkChangeManagerClient_Internal$1();
    }

    NetworkChangeManagerClient_Internal() {
        super();
    }
}

