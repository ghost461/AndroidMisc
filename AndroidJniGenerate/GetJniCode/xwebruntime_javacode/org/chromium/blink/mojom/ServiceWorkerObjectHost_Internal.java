package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ServiceWorkerObjectHost_Internal {
    final class org.chromium.blink.mojom.ServiceWorkerObjectHost_Internal$1 extends Manager {
        org.chromium.blink.mojom.ServiceWorkerObjectHost_Internal$1() {
            super();
        }

        public ServiceWorkerObjectHost[] buildArray(int arg1) {
            return new ServiceWorkerObjectHost[arg1];
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new Proxy(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public Stub buildStub(Core arg2, ServiceWorkerObjectHost arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceWorkerObjectHost)arg2));
        }

        public String getName() {
            return "blink::mojom::ServiceWorkerObjectHost";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ServiceWorkerObjectHost$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void postMessageToServiceWorker(TransferableMessage arg5) {
            ServiceWorkerObjectHostPostMessageToServiceWorkerParams v0 = new ServiceWorkerObjectHostPostMessageToServiceWorkerParams();
            v0.message = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void terminateForTesting(TerminateForTestingResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ServiceWorkerObjectHostTerminateForTestingParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new ServiceWorkerObjectHostTerminateForTestingResponseParamsForwardToCallback(arg8));
        }
    }

    final class ServiceWorkerObjectHostPostMessageToServiceWorkerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TransferableMessage message;

        static {
            ServiceWorkerObjectHostPostMessageToServiceWorkerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerObjectHostPostMessageToServiceWorkerParams.DEFAULT_STRUCT_INFO = ServiceWorkerObjectHostPostMessageToServiceWorkerParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerObjectHostPostMessageToServiceWorkerParams() {
            this(0);
        }

        private ServiceWorkerObjectHostPostMessageToServiceWorkerParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerObjectHostPostMessageToServiceWorkerParams decode(Decoder arg3) {
            ServiceWorkerObjectHostPostMessageToServiceWorkerParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerObjectHostPostMessageToServiceWorkerParams(arg3.readAndValidateDataHeader(ServiceWorkerObjectHostPostMessageToServiceWorkerParams.VERSION_ARRAY).elementsOrVersion);
                v1.message = TransferableMessage.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerObjectHostPostMessageToServiceWorkerParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerObjectHostPostMessageToServiceWorkerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerObjectHostPostMessageToServiceWorkerParams deserialize(Message arg1) {
            return ServiceWorkerObjectHostPostMessageToServiceWorkerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerObjectHostPostMessageToServiceWorkerParams.DEFAULT_STRUCT_INFO).encode(this.message, 8, false);
        }
    }

    final class ServiceWorkerObjectHostTerminateForTestingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerObjectHostTerminateForTestingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerObjectHostTerminateForTestingParams.DEFAULT_STRUCT_INFO = ServiceWorkerObjectHostTerminateForTestingParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerObjectHostTerminateForTestingParams() {
            this(0);
        }

        private ServiceWorkerObjectHostTerminateForTestingParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerObjectHostTerminateForTestingParams decode(Decoder arg2) {
            ServiceWorkerObjectHostTerminateForTestingParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerObjectHostTerminateForTestingParams(arg2.readAndValidateDataHeader(ServiceWorkerObjectHostTerminateForTestingParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerObjectHostTerminateForTestingParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerObjectHostTerminateForTestingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerObjectHostTerminateForTestingParams deserialize(Message arg1) {
            return ServiceWorkerObjectHostTerminateForTestingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerObjectHostTerminateForTestingParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerObjectHostTerminateForTestingResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerObjectHostTerminateForTestingResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerObjectHostTerminateForTestingResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerObjectHostTerminateForTestingResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerObjectHostTerminateForTestingResponseParams() {
            this(0);
        }

        private ServiceWorkerObjectHostTerminateForTestingResponseParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerObjectHostTerminateForTestingResponseParams decode(Decoder arg2) {
            ServiceWorkerObjectHostTerminateForTestingResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerObjectHostTerminateForTestingResponseParams(arg2.readAndValidateDataHeader(ServiceWorkerObjectHostTerminateForTestingResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerObjectHostTerminateForTestingResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerObjectHostTerminateForTestingResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerObjectHostTerminateForTestingResponseParams deserialize(Message arg1) {
            return ServiceWorkerObjectHostTerminateForTestingResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerObjectHostTerminateForTestingResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class ServiceWorkerObjectHostTerminateForTestingResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final TerminateForTestingResponse mCallback;

        ServiceWorkerObjectHostTerminateForTestingResponseParamsForwardToCallback(TerminateForTestingResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerObjectHostTerminateForTestingResponseParamsProxyToResponder implements TerminateForTestingResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerObjectHostTerminateForTestingResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new ServiceWorkerObjectHostTerminateForTestingResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ServiceWorkerObjectHost arg2) {
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

                    this.getImpl().postMessageToServiceWorker(ServiceWorkerObjectHostPostMessageToServiceWorkerParams.deserialize(v4_1.getPayload()).message);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceWorkerObjectHost_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                int v3 = v1.getType();
                if(v3 != -1) {
                    if(v3 != 1) {
                        return 0;
                    }

                    ServiceWorkerObjectHostTerminateForTestingParams.deserialize(v8_1.getPayload());
                    this.getImpl().terminateForTesting(new ServiceWorkerObjectHostTerminateForTestingResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceWorkerObjectHost_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int POST_MESSAGE_TO_SERVICE_WORKER_ORDINAL = 0;
    private static final int TERMINATE_FOR_TESTING_ORDINAL = 1;

    static {
        ServiceWorkerObjectHost_Internal.MANAGER = new org.chromium.blink.mojom.ServiceWorkerObjectHost_Internal$1();
    }

    ServiceWorkerObjectHost_Internal() {
        super();
    }
}

