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

class UrlLoaderFactory_Internal {
    final class org.chromium.network.mojom.UrlLoaderFactory_Internal$1 extends Manager {
        org.chromium.network.mojom.UrlLoaderFactory_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public UrlLoaderFactory[] buildArray(int arg1) {
            return new UrlLoaderFactory[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.UrlLoaderFactory_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.UrlLoaderFactory_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((UrlLoaderFactory)arg2));
        }

        public org.chromium.network.mojom.UrlLoaderFactory_Internal$Stub buildStub(Core arg2, UrlLoaderFactory arg3) {
            return new org.chromium.network.mojom.UrlLoaderFactory_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::URLLoaderFactory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.UrlLoaderFactory_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.UrlLoaderFactory$Proxy {
        org.chromium.network.mojom.UrlLoaderFactory_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void clone(InterfaceRequest arg5) {
            UrlLoaderFactoryCloneParams v0 = new UrlLoaderFactoryCloneParams();
            v0.factory = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void createLoaderAndStart(InterfaceRequest arg2, int arg3, int arg4, int arg5, UrlRequest arg6, UrlLoaderClient arg7, MutableNetworkTrafficAnnotationTag arg8) {
            UrlLoaderFactoryCreateLoaderAndStartParams v0 = new UrlLoaderFactoryCreateLoaderAndStartParams();
            v0.loader = arg2;
            v0.routingId = arg3;
            v0.requestId = arg4;
            v0.options = arg5;
            v0.request = arg6;
            v0.client = arg7;
            v0.trafficAnnotation = arg8;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.network.mojom.UrlLoaderFactory_Internal$Stub extends Stub {
        org.chromium.network.mojom.UrlLoaderFactory_Internal$Stub(Core arg1, UrlLoaderFactory arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg12) {
            try {
                ServiceMessage v12_1 = arg12.asServiceMessage();
                MessageHeader v1 = v12_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_31;
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
                UrlLoaderFactoryCreateLoaderAndStartParams v12_2 = UrlLoaderFactoryCreateLoaderAndStartParams.deserialize(v12_1.getPayload());
                this.getImpl().createLoaderAndStart(v12_2.loader, v12_2.routingId, v12_2.requestId, v12_2.options, v12_2.request, v12_2.client, v12_2.trafficAnnotation);
                return 1;
            label_12:
                this.getImpl().clone(UrlLoaderFactoryCloneParams.deserialize(v12_1.getPayload()).factory);
                return 1;
            label_31:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(UrlLoaderFactory_Internal.MANAGER, v12_1);
            }
            catch(DeserializationException v12) {
                System.err.println(v12.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), UrlLoaderFactory_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class UrlLoaderFactoryCloneParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest factory;

        static {
            UrlLoaderFactoryCloneParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UrlLoaderFactoryCloneParams.DEFAULT_STRUCT_INFO = UrlLoaderFactoryCloneParams.VERSION_ARRAY[0];
        }

        public UrlLoaderFactoryCloneParams() {
            this(0);
        }

        private UrlLoaderFactoryCloneParams(int arg2) {
            super(16, arg2);
        }

        public static UrlLoaderFactoryCloneParams decode(Decoder arg3) {
            UrlLoaderFactoryCloneParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UrlLoaderFactoryCloneParams(arg3.readAndValidateDataHeader(UrlLoaderFactoryCloneParams.VERSION_ARRAY).elementsOrVersion);
                v1.factory = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderFactoryCloneParams deserialize(ByteBuffer arg2) {
            return UrlLoaderFactoryCloneParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderFactoryCloneParams deserialize(Message arg1) {
            return UrlLoaderFactoryCloneParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(UrlLoaderFactoryCloneParams.DEFAULT_STRUCT_INFO).encode(this.factory, 8, false);
        }
    }

    final class UrlLoaderFactoryCreateLoaderAndStartParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public UrlLoaderClient client;
        public InterfaceRequest loader;
        public int options;
        public UrlRequest request;
        public int requestId;
        public int routingId;
        public MutableNetworkTrafficAnnotationTag trafficAnnotation;

        static {
            UrlLoaderFactoryCreateLoaderAndStartParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            UrlLoaderFactoryCreateLoaderAndStartParams.DEFAULT_STRUCT_INFO = UrlLoaderFactoryCreateLoaderAndStartParams.VERSION_ARRAY[0];
        }

        public UrlLoaderFactoryCreateLoaderAndStartParams() {
            this(0);
        }

        private UrlLoaderFactoryCreateLoaderAndStartParams(int arg2) {
            super(0x30, arg2);
        }

        public static UrlLoaderFactoryCreateLoaderAndStartParams decode(Decoder arg4) {
            UrlLoaderFactoryCreateLoaderAndStartParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new UrlLoaderFactoryCreateLoaderAndStartParams(arg4.readAndValidateDataHeader(UrlLoaderFactoryCreateLoaderAndStartParams.VERSION_ARRAY).elementsOrVersion);
                v1.loader = arg4.readInterfaceRequest(8, false);
                v1.routingId = arg4.readInt(12);
                v1.requestId = arg4.readInt(16);
                v1.options = arg4.readInt(20);
                v1.request = UrlRequest.decode(arg4.readPointer(24, false));
                v1.client = arg4.readServiceInterface(0x20, false, UrlLoaderClient.MANAGER);
                v1.trafficAnnotation = MutableNetworkTrafficAnnotationTag.decode(arg4.readPointer(40, false));
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderFactoryCreateLoaderAndStartParams deserialize(ByteBuffer arg2) {
            return UrlLoaderFactoryCreateLoaderAndStartParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderFactoryCreateLoaderAndStartParams deserialize(Message arg1) {
            return UrlLoaderFactoryCreateLoaderAndStartParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(UrlLoaderFactoryCreateLoaderAndStartParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.loader, 8, false);
            arg5.encode(this.routingId, 12);
            arg5.encode(this.requestId, 16);
            arg5.encode(this.options, 20);
            arg5.encode(this.request, 24, false);
            arg5.encode(this.client, 0x20, false, UrlLoaderClient.MANAGER);
            arg5.encode(this.trafficAnnotation, 40, false);
        }
    }

    private static final int CLONE_ORDINAL = 1;
    private static final int CREATE_LOADER_AND_START_ORDINAL;
    public static final Manager MANAGER;

    static {
        UrlLoaderFactory_Internal.MANAGER = new org.chromium.network.mojom.UrlLoaderFactory_Internal$1();
    }

    UrlLoaderFactory_Internal() {
        super();
    }
}

