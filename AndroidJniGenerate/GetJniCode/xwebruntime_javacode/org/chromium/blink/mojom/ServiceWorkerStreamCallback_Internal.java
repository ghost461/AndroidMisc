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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ServiceWorkerStreamCallback_Internal {
    final class org.chromium.blink.mojom.ServiceWorkerStreamCallback_Internal$1 extends Manager {
        org.chromium.blink.mojom.ServiceWorkerStreamCallback_Internal$1() {
            super();
        }

        public ServiceWorkerStreamCallback[] buildArray(int arg1) {
            return new ServiceWorkerStreamCallback[arg1];
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

        public Stub buildStub(Core arg2, ServiceWorkerStreamCallback arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceWorkerStreamCallback)arg2));
        }

        public String getName() {
            return "blink::mojom::ServiceWorkerStreamCallback";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ServiceWorkerStreamCallback$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onAborted() {
            this.getProxyHandler().getMessageReceiver().accept(new ServiceWorkerStreamCallbackOnAbortedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onCompleted() {
            this.getProxyHandler().getMessageReceiver().accept(new ServiceWorkerStreamCallbackOnCompletedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ServiceWorkerStreamCallbackOnAbortedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerStreamCallbackOnAbortedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerStreamCallbackOnAbortedParams.DEFAULT_STRUCT_INFO = ServiceWorkerStreamCallbackOnAbortedParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerStreamCallbackOnAbortedParams() {
            this(0);
        }

        private ServiceWorkerStreamCallbackOnAbortedParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerStreamCallbackOnAbortedParams decode(Decoder arg2) {
            ServiceWorkerStreamCallbackOnAbortedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerStreamCallbackOnAbortedParams(arg2.readAndValidateDataHeader(ServiceWorkerStreamCallbackOnAbortedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerStreamCallbackOnAbortedParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerStreamCallbackOnAbortedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerStreamCallbackOnAbortedParams deserialize(Message arg1) {
            return ServiceWorkerStreamCallbackOnAbortedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerStreamCallbackOnAbortedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerStreamCallbackOnCompletedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerStreamCallbackOnCompletedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerStreamCallbackOnCompletedParams.DEFAULT_STRUCT_INFO = ServiceWorkerStreamCallbackOnCompletedParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerStreamCallbackOnCompletedParams() {
            this(0);
        }

        private ServiceWorkerStreamCallbackOnCompletedParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerStreamCallbackOnCompletedParams decode(Decoder arg2) {
            ServiceWorkerStreamCallbackOnCompletedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerStreamCallbackOnCompletedParams(arg2.readAndValidateDataHeader(ServiceWorkerStreamCallbackOnCompletedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerStreamCallbackOnCompletedParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerStreamCallbackOnCompletedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerStreamCallbackOnCompletedParams deserialize(Message arg1) {
            return ServiceWorkerStreamCallbackOnCompletedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerStreamCallbackOnCompletedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ServiceWorkerStreamCallback arg2) {
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
                    goto label_22;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_17;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                ServiceWorkerStreamCallbackOnCompletedParams.deserialize(v4_1.getPayload());
                this.getImpl().onCompleted();
                return 1;
            label_12:
                ServiceWorkerStreamCallbackOnAbortedParams.deserialize(v4_1.getPayload());
                this.getImpl().onAborted();
                return 1;
            label_22:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceWorkerStreamCallback_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceWorkerStreamCallback_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_ABORTED_ORDINAL = 1;
    private static final int ON_COMPLETED_ORDINAL;

    static {
        ServiceWorkerStreamCallback_Internal.MANAGER = new org.chromium.blink.mojom.ServiceWorkerStreamCallback_Internal$1();
    }

    ServiceWorkerStreamCallback_Internal() {
        super();
    }
}

