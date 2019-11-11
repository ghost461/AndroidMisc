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

class ProxyResolverRequestClient_Internal {
    final class org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$1 extends Manager {
        org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ProxyResolverRequestClient[] buildArray(int arg1) {
            return new ProxyResolverRequestClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProxyResolverRequestClient)arg2));
        }

        public org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Stub buildStub(Core arg2, ProxyResolverRequestClient arg3) {
            return new org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "proxy_resolver::mojom::ProxyResolverRequestClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Proxy extends AbstractProxy implements org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient$Proxy {
        org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void alert(String arg5) {
            ProxyResolverRequestClientAlertParams v0 = new ProxyResolverRequestClientAlertParams();
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onError(int arg4, String arg5) {
            ProxyResolverRequestClientOnErrorParams v0 = new ProxyResolverRequestClientOnErrorParams();
            v0.lineNumber = arg4;
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void reportResult(int arg4, ProxyInfo arg5) {
            ProxyResolverRequestClientReportResultParams v0 = new ProxyResolverRequestClientReportResultParams();
            v0.error = arg4;
            v0.proxyInfo = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void resolveDns(HostResolverRequestInfo arg4, HostResolverRequestClient arg5) {
            ProxyResolverRequestClientResolveDnsParams v0 = new ProxyResolverRequestClientResolveDnsParams();
            v0.requestInfo = arg4;
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class ProxyResolverRequestClientAlertParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String error;

        static {
            ProxyResolverRequestClientAlertParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ProxyResolverRequestClientAlertParams.DEFAULT_STRUCT_INFO = ProxyResolverRequestClientAlertParams.VERSION_ARRAY[0];
        }

        public ProxyResolverRequestClientAlertParams() {
            this(0);
        }

        private ProxyResolverRequestClientAlertParams(int arg2) {
            super(16, arg2);
        }

        public static ProxyResolverRequestClientAlertParams decode(Decoder arg3) {
            ProxyResolverRequestClientAlertParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProxyResolverRequestClientAlertParams(arg3.readAndValidateDataHeader(ProxyResolverRequestClientAlertParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverRequestClientAlertParams deserialize(ByteBuffer arg2) {
            return ProxyResolverRequestClientAlertParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverRequestClientAlertParams deserialize(Message arg1) {
            return ProxyResolverRequestClientAlertParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ProxyResolverRequestClientAlertParams.DEFAULT_STRUCT_INFO).encode(this.error, 8, false);
        }
    }

    final class ProxyResolverRequestClientOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String error;
        public int lineNumber;

        static {
            ProxyResolverRequestClientOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProxyResolverRequestClientOnErrorParams.DEFAULT_STRUCT_INFO = ProxyResolverRequestClientOnErrorParams.VERSION_ARRAY[0];
        }

        public ProxyResolverRequestClientOnErrorParams() {
            this(0);
        }

        private ProxyResolverRequestClientOnErrorParams(int arg2) {
            super(24, arg2);
        }

        public static ProxyResolverRequestClientOnErrorParams decode(Decoder arg3) {
            ProxyResolverRequestClientOnErrorParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProxyResolverRequestClientOnErrorParams(arg3.readAndValidateDataHeader(ProxyResolverRequestClientOnErrorParams.VERSION_ARRAY).elementsOrVersion);
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

        public static ProxyResolverRequestClientOnErrorParams deserialize(ByteBuffer arg2) {
            return ProxyResolverRequestClientOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverRequestClientOnErrorParams deserialize(Message arg1) {
            return ProxyResolverRequestClientOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ProxyResolverRequestClientOnErrorParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.lineNumber, 8);
            arg4.encode(this.error, 16, false);
        }
    }

    final class ProxyResolverRequestClientReportResultParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public ProxyInfo proxyInfo;

        static {
            ProxyResolverRequestClientReportResultParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProxyResolverRequestClientReportResultParams.DEFAULT_STRUCT_INFO = ProxyResolverRequestClientReportResultParams.VERSION_ARRAY[0];
        }

        public ProxyResolverRequestClientReportResultParams() {
            this(0);
        }

        private ProxyResolverRequestClientReportResultParams(int arg2) {
            super(24, arg2);
        }

        public static ProxyResolverRequestClientReportResultParams decode(Decoder arg3) {
            ProxyResolverRequestClientReportResultParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ProxyResolverRequestClientReportResultParams(arg3.readAndValidateDataHeader(ProxyResolverRequestClientReportResultParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                v1.proxyInfo = ProxyInfo.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ProxyResolverRequestClientReportResultParams deserialize(ByteBuffer arg2) {
            return ProxyResolverRequestClientReportResultParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverRequestClientReportResultParams deserialize(Message arg1) {
            return ProxyResolverRequestClientReportResultParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ProxyResolverRequestClientReportResultParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.proxyInfo, 16, false);
        }
    }

    final class ProxyResolverRequestClientResolveDnsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public HostResolverRequestClient client;
        public HostResolverRequestInfo requestInfo;

        static {
            ProxyResolverRequestClientResolveDnsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ProxyResolverRequestClientResolveDnsParams.DEFAULT_STRUCT_INFO = ProxyResolverRequestClientResolveDnsParams.VERSION_ARRAY[0];
        }

        public ProxyResolverRequestClientResolveDnsParams() {
            this(0);
        }

        private ProxyResolverRequestClientResolveDnsParams(int arg2) {
            super(24, arg2);
        }

        public static ProxyResolverRequestClientResolveDnsParams decode(Decoder arg4) {
            ProxyResolverRequestClientResolveDnsParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ProxyResolverRequestClientResolveDnsParams(arg4.readAndValidateDataHeader(ProxyResolverRequestClientResolveDnsParams.VERSION_ARRAY).elementsOrVersion);
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

        public static ProxyResolverRequestClientResolveDnsParams deserialize(ByteBuffer arg2) {
            return ProxyResolverRequestClientResolveDnsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProxyResolverRequestClientResolveDnsParams deserialize(Message arg1) {
            return ProxyResolverRequestClientResolveDnsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ProxyResolverRequestClientResolveDnsParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.requestInfo, 8, false);
            arg5.encode(this.client, 16, false, HostResolverRequestClient.MANAGER);
        }
    }

    final class org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Stub extends Stub {
        org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$Stub(Core arg1, ProxyResolverRequestClient arg2) {
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
                    goto label_39;
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
                ProxyResolverRequestClientOnErrorParams v5_2 = ProxyResolverRequestClientOnErrorParams.deserialize(v5_1.getPayload());
                this.getImpl().onError(v5_2.lineNumber, v5_2.error);
                return 1;
            label_26:
                this.getImpl().alert(ProxyResolverRequestClientAlertParams.deserialize(v5_1.getPayload()).error);
                return 1;
            label_12:
                ProxyResolverRequestClientResolveDnsParams v5_3 = ProxyResolverRequestClientResolveDnsParams.deserialize(v5_1.getPayload());
                this.getImpl().resolveDns(v5_3.requestInfo, v5_3.client);
                return 1;
            label_32:
                ProxyResolverRequestClientReportResultParams v5_4 = ProxyResolverRequestClientReportResultParams.deserialize(v5_1.getPayload());
                this.getImpl().reportResult(v5_4.error, v5_4.proxyInfo);
                return 1;
            label_39:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProxyResolverRequestClient_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProxyResolverRequestClient_Internal.MANAGER, v4_1, arg5);
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
        ProxyResolverRequestClient_Internal.MANAGER = new org.chromium.proxy_resolver.mojom.ProxyResolverRequestClient_Internal$1();
    }

    ProxyResolverRequestClient_Internal() {
        super();
    }
}

