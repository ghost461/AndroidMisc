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
import org.chromium.net.interfaces.HostResolverRequestClient;
import org.chromium.net.interfaces.HostResolverRequestInfo;

class ProxyResolverFactoryRequestClient_Internal {
    final class org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$1 extends Manager {
        org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ProxyResolverFactoryRequestClient[] buildArray(int arg1) {
            return new ProxyResolverFactoryRequestClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProxyResolverFactoryRequestClient)arg2));
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Stub buildStub(Core arg2, ProxyResolverFactoryRequestClient arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "proxy_resolver::mojom::ProxyResolverFactoryRequestClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Proxy extends AbstractProxy implements org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient$Proxy {
        org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void alert(String arg5) {
            ProxyResolverFactoryRequestClientAlertParams v0 = new ProxyResolverFactoryRequestClientAlertParams();
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onError(int arg4, String arg5) {
            ProxyResolverFactoryRequestClientOnErrorParams v0 = new ProxyResolverFactoryRequestClientOnErrorParams();
            v0.lineNumber = arg4;
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void reportResult(int arg5) {
            ProxyResolverFactoryRequestClientReportResultParams v0 = new ProxyResolverFactoryRequestClientReportResultParams();
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void resolveDns(HostResolverRequestInfo arg4, HostResolverRequestClient arg5) {
            ProxyResolverFactoryRequestClientResolveDnsParams v0 = new ProxyResolverFactoryRequestClientResolveDnsParams();
            v0.requestInfo = arg4;
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class ProxyResolverFactoryRequestClientAlertParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String error;

        static {
            ProxyResolverFactoryRequestClientAlertParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ProxyResolverFactoryRequestClientAlertParams.DEFAULT_STRUCT_INFO = ProxyResolverFactoryRequestClientAlertParams.VERSION_ARRAY[0];
        }

        public ProxyResolverFactoryRequestClientAlertParams() {
            this(0);
        }

        private ProxyResolverFactoryRequestClientAlertParams(int arg2) {
            super(16, arg2);
        }

        public static ProxyResolverFactoryRequestClientAlertParams decode(Decoder arg3) {
            ProxyResolverFactoryRequestClientAlertParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProxyResolverFactoryRequestClientAlertParams(arg3.readAndValidateDataHeader(ProxyResolverFactoryRequestClientAlertParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverFactoryRequestClientAlertParams deserialize(ByteBuffer arg2) {
            return ProxyResolverFactoryRequestClientAlertParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverFactoryRequestClientAlertParams deserialize(Message arg1) {
            return ProxyResolverFactoryRequestClientAlertParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ProxyResolverFactoryRequestClientAlertParams.DEFAULT_STRUCT_INFO).encode(this.error, 8, false);
        }
    }

    final class ProxyResolverFactoryRequestClientOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String error;
        public int lineNumber;

        static {
            ProxyResolverFactoryRequestClientOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProxyResolverFactoryRequestClientOnErrorParams.DEFAULT_STRUCT_INFO = ProxyResolverFactoryRequestClientOnErrorParams.VERSION_ARRAY[0];
        }

        public ProxyResolverFactoryRequestClientOnErrorParams() {
            this(0);
        }

        private ProxyResolverFactoryRequestClientOnErrorParams(int arg2) {
            super(24, arg2);
        }

        public static ProxyResolverFactoryRequestClientOnErrorParams decode(Decoder arg3) {
            ProxyResolverFactoryRequestClientOnErrorParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProxyResolverFactoryRequestClientOnErrorParams(arg3.readAndValidateDataHeader(ProxyResolverFactoryRequestClientOnErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.lineNumber = arg3.readInt(8);
                v1.error = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverFactoryRequestClientOnErrorParams deserialize(ByteBuffer arg2) {
            return ProxyResolverFactoryRequestClientOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverFactoryRequestClientOnErrorParams deserialize(Message arg1) {
            return ProxyResolverFactoryRequestClientOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ProxyResolverFactoryRequestClientOnErrorParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.lineNumber, 8);
            arg4.encode(this.error, 16, false);
        }
    }

    final class ProxyResolverFactoryRequestClientReportResultParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;

        static {
            ProxyResolverFactoryRequestClientReportResultParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ProxyResolverFactoryRequestClientReportResultParams.DEFAULT_STRUCT_INFO = ProxyResolverFactoryRequestClientReportResultParams.VERSION_ARRAY[0];
        }

        public ProxyResolverFactoryRequestClientReportResultParams() {
            this(0);
        }

        private ProxyResolverFactoryRequestClientReportResultParams(int arg2) {
            super(16, arg2);
        }

        public static ProxyResolverFactoryRequestClientReportResultParams decode(Decoder arg2) {
            ProxyResolverFactoryRequestClientReportResultParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ProxyResolverFactoryRequestClientReportResultParams(arg2.readAndValidateDataHeader(ProxyResolverFactoryRequestClientReportResultParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverFactoryRequestClientReportResultParams deserialize(ByteBuffer arg2) {
            return ProxyResolverFactoryRequestClientReportResultParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverFactoryRequestClientReportResultParams deserialize(Message arg1) {
            return ProxyResolverFactoryRequestClientReportResultParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ProxyResolverFactoryRequestClientReportResultParams.DEFAULT_STRUCT_INFO).encode(this.error, 8);
        }
    }

    final class ProxyResolverFactoryRequestClientResolveDnsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public HostResolverRequestClient client;
        public HostResolverRequestInfo requestInfo;

        static {
            ProxyResolverFactoryRequestClientResolveDnsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProxyResolverFactoryRequestClientResolveDnsParams.DEFAULT_STRUCT_INFO = ProxyResolverFactoryRequestClientResolveDnsParams.VERSION_ARRAY[0];
        }

        public ProxyResolverFactoryRequestClientResolveDnsParams() {
            this(0);
        }

        private ProxyResolverFactoryRequestClientResolveDnsParams(int arg2) {
            super(24, arg2);
        }

        public static ProxyResolverFactoryRequestClientResolveDnsParams decode(Decoder arg4) {
            ProxyResolverFactoryRequestClientResolveDnsParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ProxyResolverFactoryRequestClientResolveDnsParams(arg4.readAndValidateDataHeader(ProxyResolverFactoryRequestClientResolveDnsParams.VERSION_ARRAY).elementsOrVersion);
                v1.requestInfo = HostResolverRequestInfo.decode(arg4.readPointer(8, false));
                v1.client = arg4.readServiceInterface(16, false, HostResolverRequestClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverFactoryRequestClientResolveDnsParams deserialize(ByteBuffer arg2) {
            return ProxyResolverFactoryRequestClientResolveDnsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverFactoryRequestClientResolveDnsParams deserialize(Message arg1) {
            return ProxyResolverFactoryRequestClientResolveDnsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ProxyResolverFactoryRequestClientResolveDnsParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.requestInfo, 8, false);
            arg5.encode(this.client, 16, false, HostResolverRequestClient.MANAGER);
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Stub extends Stub {
        org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$Stub(Core arg1, ProxyResolverFactoryRequestClient arg2) {
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
                    goto label_38;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_32;
                    }
                    case 1: {
                        goto label_26;
                    }
                    case 2: {
                        goto label_19;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_19:
                ProxyResolverFactoryRequestClientOnErrorParams v5_2 = ProxyResolverFactoryRequestClientOnErrorParams.deserialize(v5_1.getPayload());
                this.getImpl().onError(v5_2.lineNumber, v5_2.error);
                return 1;
            label_26:
                this.getImpl().alert(ProxyResolverFactoryRequestClientAlertParams.deserialize(v5_1.getPayload()).error);
                return 1;
            label_12:
                ProxyResolverFactoryRequestClientResolveDnsParams v5_3 = ProxyResolverFactoryRequestClientResolveDnsParams.deserialize(v5_1.getPayload());
                this.getImpl().resolveDns(v5_3.requestInfo, v5_3.client);
                return 1;
            label_32:
                this.getImpl().reportResult(ProxyResolverFactoryRequestClientReportResultParams.deserialize(v5_1.getPayload()).error);
                return 1;
            label_38:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProxyResolverFactoryRequestClient_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProxyResolverFactoryRequestClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int ALERT_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int ON_ERROR_ORDINAL = 2;
    private static final int REPORT_RESULT_ORDINAL = 0;
    private static final int RESOLVE_DNS_ORDINAL = 3;

    static {
        ProxyResolverFactoryRequestClient_Internal.MANAGER = new org.chromium.proxy_resolver.mojom.ProxyResolverFactoryRequestClient_Internal$1();
    }

    ProxyResolverFactoryRequestClient_Internal() {
        super();
    }
}

