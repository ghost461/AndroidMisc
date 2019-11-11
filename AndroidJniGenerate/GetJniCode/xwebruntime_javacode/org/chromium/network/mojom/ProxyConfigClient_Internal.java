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

class ProxyConfigClient_Internal {
    final class org.chromium.network.mojom.ProxyConfigClient_Internal$1 extends Manager {
        org.chromium.network.mojom.ProxyConfigClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ProxyConfigClient[] buildArray(int arg1) {
            return new ProxyConfigClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.ProxyConfigClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.ProxyConfigClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProxyConfigClient)arg2));
        }

        public org.chromium.network.mojom.ProxyConfigClient_Internal$Stub buildStub(Core arg2, ProxyConfigClient arg3) {
            return new org.chromium.network.mojom.ProxyConfigClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::ProxyConfigClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.ProxyConfigClient_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.ProxyConfigClient$Proxy {
        org.chromium.network.mojom.ProxyConfigClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onProxyConfigUpdated(ProxyConfigWithAnnotation arg5) {
            ProxyConfigClientOnProxyConfigUpdatedParams v0 = new ProxyConfigClientOnProxyConfigUpdatedParams();
            v0.proxyConfig = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ProxyConfigClientOnProxyConfigUpdatedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ProxyConfigWithAnnotation proxyConfig;

        static {
            ProxyConfigClientOnProxyConfigUpdatedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ProxyConfigClientOnProxyConfigUpdatedParams.DEFAULT_STRUCT_INFO = ProxyConfigClientOnProxyConfigUpdatedParams.VERSION_ARRAY[0];
        }

        public ProxyConfigClientOnProxyConfigUpdatedParams() {
            this(0);
        }

        private ProxyConfigClientOnProxyConfigUpdatedParams(int arg2) {
            super(16, arg2);
        }

        public static ProxyConfigClientOnProxyConfigUpdatedParams decode(Decoder arg3) {
            ProxyConfigClientOnProxyConfigUpdatedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProxyConfigClientOnProxyConfigUpdatedParams(arg3.readAndValidateDataHeader(ProxyConfigClientOnProxyConfigUpdatedParams.VERSION_ARRAY).elementsOrVersion);
                v1.proxyConfig = ProxyConfigWithAnnotation.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProxyConfigClientOnProxyConfigUpdatedParams deserialize(ByteBuffer arg2) {
            return ProxyConfigClientOnProxyConfigUpdatedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyConfigClientOnProxyConfigUpdatedParams deserialize(Message arg1) {
            return ProxyConfigClientOnProxyConfigUpdatedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ProxyConfigClientOnProxyConfigUpdatedParams.DEFAULT_STRUCT_INFO).encode(this.proxyConfig, 8, false);
        }
    }

    final class org.chromium.network.mojom.ProxyConfigClient_Internal$Stub extends Stub {
        org.chromium.network.mojom.ProxyConfigClient_Internal$Stub(Core arg1, ProxyConfigClient arg2) {
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

                    this.getImpl().onProxyConfigUpdated(ProxyConfigClientOnProxyConfigUpdatedParams.deserialize(v4_1.getPayload()).proxyConfig);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProxyConfigClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProxyConfigClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int ON_PROXY_CONFIG_UPDATED_ORDINAL;

    static {
        ProxyConfigClient_Internal.MANAGER = new org.chromium.network.mojom.ProxyConfigClient_Internal$1();
    }

    ProxyConfigClient_Internal() {
        super();
    }
}

