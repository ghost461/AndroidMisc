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
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.url.mojom.Url;

class ProxyResolver_Internal {
    final class org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$1 extends Manager {
        org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ProxyResolver[] buildArray(int arg1) {
            return new ProxyResolver[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProxyResolver)arg2));
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Stub buildStub(Core arg2, ProxyResolver arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "proxy_resolver::mojom::ProxyResolver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Proxy extends AbstractProxy implements org.chromium.proxy_resolver.mojom.ProxyResolver$Proxy {
        org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getProxyForUrl(Url arg4, ProxyResolverRequestClient arg5) {
            ProxyResolverGetProxyForUrlParams v0 = new ProxyResolverGetProxyForUrlParams();
            v0.url = arg4;
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ProxyResolverGetProxyForUrlParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public ProxyResolverRequestClient client;
        public Url url;

        static {
            ProxyResolverGetProxyForUrlParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProxyResolverGetProxyForUrlParams.DEFAULT_STRUCT_INFO = ProxyResolverGetProxyForUrlParams.VERSION_ARRAY[0];
        }

        public ProxyResolverGetProxyForUrlParams() {
            this(0);
        }

        private ProxyResolverGetProxyForUrlParams(int arg2) {
            super(24, arg2);
        }

        public static ProxyResolverGetProxyForUrlParams decode(Decoder arg4) {
            ProxyResolverGetProxyForUrlParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ProxyResolverGetProxyForUrlParams(arg4.readAndValidateDataHeader(ProxyResolverGetProxyForUrlParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg4.readPointer(8, false));
                v1.client = arg4.readServiceInterface(16, false, ProxyResolverRequestClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverGetProxyForUrlParams deserialize(ByteBuffer arg2) {
            return ProxyResolverGetProxyForUrlParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverGetProxyForUrlParams deserialize(Message arg1) {
            return ProxyResolverGetProxyForUrlParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ProxyResolverGetProxyForUrlParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.url, 8, false);
            arg5.encode(this.client, 16, false, ProxyResolverRequestClient.MANAGER);
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Stub extends Stub {
        org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$Stub(Core arg1, ProxyResolver arg2) {
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

                    ProxyResolverGetProxyForUrlParams v4_2 = ProxyResolverGetProxyForUrlParams.deserialize(v4_1.getPayload());
                    this.getImpl().getProxyForUrl(v4_2.url, v4_2.client);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProxyResolver_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProxyResolver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int GET_PROXY_FOR_URL_ORDINAL;
    public static final Manager MANAGER;

    static {
        ProxyResolver_Internal.MANAGER = new org.chromium.proxy_resolver.mojom.ProxyResolver_Internal$1();
    }

    ProxyResolver_Internal() {
        super();
    }
}

