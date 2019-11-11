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

class ProxyConfigPollerClient_Internal {
    final class org.chromium.network.mojom.ProxyConfigPollerClient_Internal$1 extends Manager {
        org.chromium.network.mojom.ProxyConfigPollerClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ProxyConfigPollerClient[] buildArray(int arg1) {
            return new ProxyConfigPollerClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProxyConfigPollerClient)arg2));
        }

        public org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Stub buildStub(Core arg2, ProxyConfigPollerClient arg3) {
            return new org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::ProxyConfigPollerClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.ProxyConfigPollerClient$Proxy {
        org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onLazyProxyConfigPoll() {
            this.getProxyHandler().getMessageReceiver().accept(new ProxyConfigPollerClientOnLazyProxyConfigPollParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ProxyConfigPollerClientOnLazyProxyConfigPollParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ProxyConfigPollerClientOnLazyProxyConfigPollParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ProxyConfigPollerClientOnLazyProxyConfigPollParams.DEFAULT_STRUCT_INFO = ProxyConfigPollerClientOnLazyProxyConfigPollParams.VERSION_ARRAY[0];
        }

        public ProxyConfigPollerClientOnLazyProxyConfigPollParams() {
            this(0);
        }

        private ProxyConfigPollerClientOnLazyProxyConfigPollParams(int arg2) {
            super(8, arg2);
        }

        public static ProxyConfigPollerClientOnLazyProxyConfigPollParams decode(Decoder arg2) {
            ProxyConfigPollerClientOnLazyProxyConfigPollParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ProxyConfigPollerClientOnLazyProxyConfigPollParams(arg2.readAndValidateDataHeader(ProxyConfigPollerClientOnLazyProxyConfigPollParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ProxyConfigPollerClientOnLazyProxyConfigPollParams deserialize(ByteBuffer arg2) {
            return ProxyConfigPollerClientOnLazyProxyConfigPollParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyConfigPollerClientOnLazyProxyConfigPollParams deserialize(Message arg1) {
            return ProxyConfigPollerClientOnLazyProxyConfigPollParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ProxyConfigPollerClientOnLazyProxyConfigPollParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Stub extends Stub {
        org.chromium.network.mojom.ProxyConfigPollerClient_Internal$Stub(Core arg1, ProxyConfigPollerClient arg2) {
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

                    ProxyConfigPollerClientOnLazyProxyConfigPollParams.deserialize(v4_1.getPayload());
                    this.getImpl().onLazyProxyConfigPoll();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProxyConfigPollerClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProxyConfigPollerClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int ON_LAZY_PROXY_CONFIG_POLL_ORDINAL;

    static {
        ProxyConfigPollerClient_Internal.MANAGER = new org.chromium.network.mojom.ProxyConfigPollerClient_Internal$1();
    }

    ProxyConfigPollerClient_Internal() {
        super();
    }
}

