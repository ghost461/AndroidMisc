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

class ServiceWorkerRegistrationObject_Internal {
    final class org.chromium.blink.mojom.ServiceWorkerRegistrationObject_Internal$1 extends Manager {
        org.chromium.blink.mojom.ServiceWorkerRegistrationObject_Internal$1() {
            super();
        }

        public ServiceWorkerRegistrationObject[] buildArray(int arg1) {
            return new ServiceWorkerRegistrationObject[arg1];
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

        public Stub buildStub(Core arg2, ServiceWorkerRegistrationObject arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceWorkerRegistrationObject)arg2));
        }

        public String getName() {
            return "blink::mojom::ServiceWorkerRegistrationObject";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ServiceWorkerRegistrationObject$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void setVersionAttributes(int arg2, ServiceWorkerObjectInfo arg3, ServiceWorkerObjectInfo arg4, ServiceWorkerObjectInfo arg5) {
            ServiceWorkerRegistrationObjectSetVersionAttributesParams v0 = new ServiceWorkerRegistrationObjectSetVersionAttributesParams();
            v0.changedMask = arg2;
            v0.installing = arg3;
            v0.waiting = arg4;
            v0.active = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void updateFound() {
            this.getProxyHandler().getMessageReceiver().accept(new ServiceWorkerRegistrationObjectUpdateFoundParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class ServiceWorkerRegistrationObjectSetVersionAttributesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerObjectInfo active;
        public int changedMask;
        public ServiceWorkerObjectInfo installing;
        public ServiceWorkerObjectInfo waiting;

        static {
            ServiceWorkerRegistrationObjectSetVersionAttributesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            ServiceWorkerRegistrationObjectSetVersionAttributesParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectSetVersionAttributesParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectSetVersionAttributesParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectSetVersionAttributesParams(int arg2) {
            super(40, arg2);
        }

        public static ServiceWorkerRegistrationObjectSetVersionAttributesParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectSetVersionAttributesParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectSetVersionAttributesParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectSetVersionAttributesParams.VERSION_ARRAY).elementsOrVersion);
                v1.changedMask = arg3.readInt(8);
                v1.installing = ServiceWorkerObjectInfo.decode(arg3.readPointer(16, true));
                v1.waiting = ServiceWorkerObjectInfo.decode(arg3.readPointer(24, true));
                v1.active = ServiceWorkerObjectInfo.decode(arg3.readPointer(0x20, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectSetVersionAttributesParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectSetVersionAttributesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectSetVersionAttributesParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectSetVersionAttributesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectSetVersionAttributesParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.changedMask, 8);
            arg4.encode(this.installing, 16, true);
            arg4.encode(this.waiting, 24, true);
            arg4.encode(this.active, 0x20, true);
        }
    }

    final class ServiceWorkerRegistrationObjectUpdateFoundParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerRegistrationObjectUpdateFoundParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerRegistrationObjectUpdateFoundParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectUpdateFoundParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectUpdateFoundParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectUpdateFoundParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerRegistrationObjectUpdateFoundParams decode(Decoder arg2) {
            ServiceWorkerRegistrationObjectUpdateFoundParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectUpdateFoundParams(arg2.readAndValidateDataHeader(ServiceWorkerRegistrationObjectUpdateFoundParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectUpdateFoundParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectUpdateFoundParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectUpdateFoundParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectUpdateFoundParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectUpdateFoundParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ServiceWorkerRegistrationObject arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_26;
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
                ServiceWorkerRegistrationObjectSetVersionAttributesParams v7_2 = ServiceWorkerRegistrationObjectSetVersionAttributesParams.deserialize(v7_1.getPayload());
                this.getImpl().setVersionAttributes(v7_2.changedMask, v7_2.installing, v7_2.waiting, v7_2.active);
                return 1;
            label_12:
                ServiceWorkerRegistrationObjectUpdateFoundParams.deserialize(v7_1.getPayload());
                this.getImpl().updateFound();
                return 1;
            label_26:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceWorkerRegistrationObject_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceWorkerRegistrationObject_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int SET_VERSION_ATTRIBUTES_ORDINAL = 0;
    private static final int UPDATE_FOUND_ORDINAL = 1;

    static {
        ServiceWorkerRegistrationObject_Internal.MANAGER = new org.chromium.blink.mojom.ServiceWorkerRegistrationObject_Internal$1();
    }

    ServiceWorkerRegistrationObject_Internal() {
        super();
    }
}

