package org.chromium.proxy_resolver.mojom;

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

class ProxyResolverFactory_Internal {
    final class org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$1 extends Manager {
        org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ProxyResolverFactory[] buildArray(int arg1) {
            return new ProxyResolverFactory[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProxyResolverFactory)arg2));
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Stub buildStub(Core arg2, ProxyResolverFactory arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "proxy_resolver::mojom::ProxyResolverFactory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Proxy extends AbstractProxy implements org.chromium.proxy_resolver.mojom.ProxyResolverFactory$Proxy {
        org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createResolver(String arg3, InterfaceRequest arg4, ProxyResolverFactoryRequestClient arg5) {
            ProxyResolverFactoryCreateResolverParams v0 = new ProxyResolverFactoryCreateResolverParams();
            v0.pacScript = arg3;
            v0.resolver = arg4;
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ProxyResolverFactoryCreateResolverParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public ProxyResolverFactoryRequestClient client;
        public String pacScript;
        public InterfaceRequest resolver;

        static {
            ProxyResolverFactoryCreateResolverParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ProxyResolverFactoryCreateResolverParams.DEFAULT_STRUCT_INFO = ProxyResolverFactoryCreateResolverParams.VERSION_ARRAY[0];
        }

        public ProxyResolverFactoryCreateResolverParams() {
            this(0);
        }

        private ProxyResolverFactoryCreateResolverParams(int arg2) {
            super(0x20, arg2);
        }

        public static ProxyResolverFactoryCreateResolverParams decode(Decoder arg4) {
            ProxyResolverFactoryCreateResolverParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ProxyResolverFactoryCreateResolverParams(arg4.readAndValidateDataHeader(ProxyResolverFactoryCreateResolverParams.VERSION_ARRAY).elementsOrVersion);
                v1.pacScript = arg4.readString(8, false);
                v1.resolver = arg4.readInterfaceRequest(16, false);
                v1.client = arg4.readServiceInterface(20, false, ProxyResolverFactoryRequestClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverFactoryCreateResolverParams deserialize(ByteBuffer arg2) {
            return ProxyResolverFactoryCreateResolverParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverFactoryCreateResolverParams deserialize(Message arg1) {
            return ProxyResolverFactoryCreateResolverParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ProxyResolverFactoryCreateResolverParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.pacScript, 8, false);
            arg5.encode(this.resolver, 16, false);
            arg5.encode(this.client, 20, false, ProxyResolverFactoryRequestClient.MANAGER);
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Stub extends Stub {
        org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$Stub(Core arg1, ProxyResolverFactory arg2) {
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
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        return 0;
                    }

                    ProxyResolverFactoryCreateResolverParams v5_2 = ProxyResolverFactoryCreateResolverParams.deserialize(v5_1.getPayload());
                    this.getImpl().createResolver(v5_2.pacScript, v5_2.resolver, v5_2.client);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProxyResolverFactory_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProxyResolverFactory_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_RESOLVER_ORDINAL;
    public static final Manager MANAGER;

    static {
        ProxyResolverFactory_Internal.MANAGER = new org.chromium.proxy_resolver.mojom.ProxyResolverFactory_Internal$1();
    }

    ProxyResolverFactory_Internal() {
        super();
    }
}

